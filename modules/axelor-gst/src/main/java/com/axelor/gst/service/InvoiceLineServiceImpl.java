package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.persist.Transactional;

public class InvoiceLineServiceImpl implements InvoiceLineService {

	@Override
	public Invoice netamount(Invoice invoiceline, ActionRequest request, ActionResponse response) {

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

		return invoiceline;
	}

	@Override
	public String getHsbn(InvoiceLine invoiceline) {
		String hsbn = invoiceline.getProduct().getHsbn();
		return hsbn;
	}

	@Override
	public BigDecimal getGst(InvoiceLine invoiceline) {
		BigDecimal gstRate = invoiceline.getProduct().getGstRate();
		return gstRate;
	}

	@Override
	public BigDecimal getPrice(InvoiceLine invoiceline) {
	BigDecimal salePrice = invoiceline.getProduct().getSalePrice();
		return salePrice;
	}

	@Override
	public String getProdctName(InvoiceLine invoiceline) {
		String prodctname = invoiceline.getProduct().getName();
		String code = invoiceline.getProduct().getCode();

		String fullname = code.concat(prodctname);

		return fullname;
	}

	@Override
	public BigDecimal getAmount(BigDecimal price, Integer quantity) {

		BigDecimal qtyvalueOf = BigDecimal.valueOf(quantity);

		if (price != null & qtyvalueOf != null) {
			BigDecimal netAmount = qtyvalueOf.multiply(price);
			return netAmount;
		} else {
			return null;
		}

	}

	@Transactional
	public void getnetamount(InvoiceLine invoiceline, Invoice invoice, ActionResponse response) {

		String invoicestate = invoice.getInvoiceAddress().getState().getName();
		String companystate = invoice.getCompany().getAddress().getState().getName();

		BigDecimal price = invoiceline.getPrice();
		Integer quantity = invoiceline.getQuantity();
		BigDecimal gstRate = invoiceline.getGstRate().divide(BigDecimal.valueOf(100));

		BigDecimal res;
		BigDecimal total = BigDecimal.ZERO;

		try {

			BigDecimal newinvoice = getAmount(price, quantity);

			if (newinvoice != null) {

				response.setValue("netAmount", newinvoice);

				total = total.add(newinvoice);

				if (invoicestate.isEmpty() || companystate.isEmpty()) {
					response.setNotify("Enter the address of company or party");

				} else {
					BigDecimal multiply = gstRate.multiply(newinvoice);

					if (invoicestate.equals(companystate)) {

						BigDecimal divider = new BigDecimal(2.00);

						res = multiply.divide(divider);

						response.setValue("sgst", res);
						response.setValue("cgst", res);
						response.setValue("igst", BigDecimal.ZERO);
						total = total.add(res);

					} else {
						response.setValue("igst", multiply);
						response.setValue("sgst", BigDecimal.ZERO);
						response.setValue("cgst", BigDecimal.ZERO);
						total = total.add(multiply);
					}
				}

				response.setValue("grossAmount", total);

			}

		} catch (NullPointerException e) {
			response.setNotify("Check there is some null value");
		} catch (Exception e) {
			e.printStackTrace(System.out);
			response.setNotify("Check there some error in console");
		}
	}
	
	
	public BigDecimal calculatedSgst(BigDecimal multiply) {
		BigDecimal divider = new BigDecimal(2.00);
		
		BigDecimal res = multiply.divide(divider);
		
		return res;
	}
	
}
