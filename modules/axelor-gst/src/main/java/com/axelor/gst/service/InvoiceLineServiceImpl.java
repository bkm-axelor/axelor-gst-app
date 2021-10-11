package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.persist.Transactional;

public class InvoiceLineServiceImpl implements InvoiceLineService {

	
	@Override
	public Invoice netamount(Invoice invoiceline, ActionRequest request, ActionResponse response) {

		System.out.println("netamount is called");

		List<InvoiceLine> invoiceItems = invoiceline.getInvoiceItems();

		for (InvoiceLine invoice : invoiceItems) {
			BigDecimal netAmount = invoice.getNetAmount();
			Integer quantity = invoice.getQuantity();
			BigDecimal costPrice = invoice.getProduct().getCostPrice();

			BigDecimal qtyvalueOf = BigDecimal.valueOf(quantity);

			netAmount = qtyvalueOf.multiply(costPrice);

			invoice.setNetAmount(netAmount);

			response.setValue("netAmount", netAmount);

			System.out.println(netAmount);
		}

		// invoiceline.setInvoiceItems(invoiceItems);
		return invoiceline;
	}

	@Override
	public BigDecimal gstrate(Invoice invoiceline) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BigDecimal getamount(BigDecimal price, Integer quantity) {

		BigDecimal qtyvalueOf = BigDecimal.valueOf(quantity);

		if (price != null & qtyvalueOf != null) {
			BigDecimal netAmount = qtyvalueOf.multiply(price);
			return netAmount;
		}
		else {
			return null;
		}
		
	}

	@Override
	public String gethsbn(InvoiceLine invoiceline) {
		String hsbn = invoiceline.getProduct().getHsbn();
		return hsbn;
	}

	@Override
	public BigDecimal getgst(InvoiceLine invoiceline) {
		BigDecimal gstRate = invoiceline.getProduct().getGstRate();
		return gstRate;
	}

	@Override
	public BigDecimal getprice(InvoiceLine invoiceline) {
		BigDecimal costPrice = invoiceline.getProduct().getCostPrice();
		return costPrice;
	}

	@Override
	public String getprodctname(InvoiceLine invoiceline) {
		String prodctname = invoiceline.getProduct().getName();
		String code = invoiceline.getProduct().getCode();

		String fullname = code.concat(prodctname);
		
		return fullname;
	}
	
	@Transactional
	public void getnetamount(InvoiceLine invoiceline,Invoice invoice,ActionResponse response) {
		
		String invoicestate = invoice.getInvoiceAddress().getState().getName();
		String companystate = invoice.getCompany().getAddress().getState().getName();

		BigDecimal price = invoiceline.getPrice();
		Integer quantity = invoiceline.getQuantity();
		BigDecimal gstRate = invoiceline.getGstRate();

		BigDecimal res;
		BigDecimal total = new BigDecimal(0.00);

		try {

			BigDecimal newinvoice = Beans.get(InvoiceLineService.class).getamount(price, quantity);

			if (newinvoice != null) {

				response.setValue("netAmount", newinvoice);
				
				total = total.add(newinvoice);

				if (invoicestate.isEmpty() || companystate.isEmpty()) {

					response.setError("Enter the address of company or party");

				} else {
					BigDecimal multiply = gstRate.multiply(newinvoice);

					if (invoicestate.equals(companystate)) {

						BigDecimal divider = new BigDecimal(2.00);

						res = multiply.divide(divider);
						
						response.setValue("sgst", res);
						response.setValue("cgst", res);

						total = total.add(res);

					} else {
						response.setValue("igst", multiply);
						total = total.add(multiply);
					}
				}

				response.setValue("grossAmount", total);

			}

		}catch (NullPointerException e) {

			response.setError("Check there is some null value");
		}catch (Exception e) {

			response.setError("Error");
		}
	}

}
