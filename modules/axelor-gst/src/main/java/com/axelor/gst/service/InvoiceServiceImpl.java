package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class InvoiceServiceImpl implements InvoiceService {

	@Inject
	SequenceService sequenceService;
	@Inject
	InvoiceRepository invoiceRepository;
	@Inject
	InvoiceLineServiceImpl invoiceLineServiceImpl;
	
	@Override
	public BigDecimal calculateNetAmount(Invoice invoice) {

		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netAmount = invoiceLine.getNetAmount();
			totalamount = totalamount.add(netAmount);

		}
		return totalamount;
	}

	@Override
	public BigDecimal calculateNetIgst(Invoice invoice) {
		BigDecimal totalamount = BigDecimal.ZERO;
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netigst = invoiceLine.getIgst();
			totalamount = totalamount.add(netigst);

		}
		return totalamount;
	}

	@Override
	public BigDecimal calculateNetCgst(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netcgst = invoiceLine.getCgst();
			totalamount = totalamount.add(netcgst);

		}
		return totalamount;
	}

	@Override
	public BigDecimal calculateNetSgst(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netsgst = invoiceLine.getSgst();
			totalamount = totalamount.add(netsgst);

		}
		return totalamount;
	}

	@Override
	public BigDecimal calculateNetGrossAmount(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netgamount = invoiceLine.getGrossAmount();
			totalamount = totalamount.add(netgamount);

		}
		return totalamount;
	}

	@Override
	public void getNetAmount(Invoice invoice, ActionResponse response) {

		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();

		for (InvoiceLine invoiceLine : invoiceItems) {

			BigDecimal gstRate = invoiceLine.getGstRate().divide(BigDecimal.valueOf(100));

			String invoicestate = invoice.getInvoiceAddress().getState().getName();
			String companystate = invoice.getCompany().getAddress().getState().getName();
			
			BigDecimal amount = invoice.getNetAmount();
			
			if(amount != null) {
				amount = invoiceLineServiceImpl.getAmount(invoiceLine.getPrice(),invoiceLine.getQuantity());
			}

			BigDecimal total = BigDecimal.ZERO;
			total = total.add(amount);

			BigDecimal multiply = gstRate.multiply(amount);

			if (!invoicestate.isEmpty() || !companystate.isEmpty()) {
				if (invoicestate.equals(companystate)) {
					BigDecimal calculatedSgst = invoiceLineServiceImpl.calculatedSgst(multiply);

					invoiceLine.setCgst(calculatedSgst);
					invoiceLine.setSgst(calculatedSgst);
					invoiceLine.setIgst(BigDecimal.ZERO);

					total = total.add(calculatedSgst);
				} else {
					invoiceLine.setCgst(BigDecimal.ZERO);
					invoiceLine.setSgst(BigDecimal.ZERO);
					invoiceLine.setIgst(multiply);

					total = total.add(multiply);
				}
				invoiceLine.setGrossAmount(total);
			}
		}
		response.setValue("invoiceItems", invoiceItems);

	}

	@Transactional
	@Override
	public void setReference(Invoice invoice, ActionResponse response) {
		String refrence = sequenceService.getNextNumber("Invoice");
		invoice.setReference(refrence);
		response.setValue("reference", refrence);
		if (invoice.getId() != null) {
			invoice.setStatus("Validated");
			invoiceRepository.refresh(invoice);
			response.setReload(true);
		}
	}

	@Override
	public void setReferenceRemove(Invoice invoice, ActionResponse response) {
		response.setValue("reference", "");
		invoice.setReference("");
		if (invoice.getId() != null) {
			invoice.setStatus("Cancelled");
			invoiceRepository.refresh(invoice);
			response.setReload(true);
		}
	}

	@Override
	public InvoiceLine setNewInvoice(InvoiceLine invoiceLine) {
		Product product = invoiceLine.getProduct();

		if (product.getGstRate() != new BigDecimal("0")) {
			invoiceLine.setGstRate(product.getGstRate());
		}
		if (product.getHsbn() != null) {
			invoiceLine.setHsbn(product.getHsbn());
		}
		if (product.getSalePrice() != null) {
			invoiceLine.setPrice(product.getSalePrice());
		}
		if (invoiceLine.getQuantity() == 0) {
			invoiceLine.setQuantity(1);
		}
		if (invoiceLine.getPrice() != null) {
			BigDecimal amount = invoiceLineServiceImpl.getAmount(invoiceLine.getPrice(),invoiceLine.getQuantity());
			invoiceLine.setNetAmount(amount);
		}
		invoiceLine.setItem(product.getName() + "" + product.getCode());

		System.err.println(invoiceLine);
		return invoiceLine;
	}

}
