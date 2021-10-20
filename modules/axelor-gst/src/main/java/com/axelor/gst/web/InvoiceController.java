package com.axelor.gst.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.gst.db.repo.ProductRepository;
import com.axelor.gst.service.InvoiceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceController {

	@Inject
	public ProductRepository productRepository;

	public void calculateNetAmount(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetamount = Beans.get(InvoiceService.class).calculateNetAmount(invoice);

		response.setValue("netAmount", calculatenetamount);

	}

	public void calculateNetIgst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetigst = Beans.get(InvoiceService.class).calculateNetIgst(invoice);

		response.setValue("netIGST", calculatenetigst);

	}

	public void calculateNetCgst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetcgst = Beans.get(InvoiceService.class).calculateNetCgst(invoice);

		response.setValue("netCSGT", calculatenetcgst);

	}

	public void calculateNetSgst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetsgst = Beans.get(InvoiceService.class).calculateNetSgst(invoice);

		response.setValue("netSGST", calculatenetsgst);

	}

	public void calculateNetGrossAmount(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetgamount = Beans.get(InvoiceService.class).calculateNetGrossAmount(invoice);

		response.setValue("grossAmount", calculatenetgamount);

	}

	public void calculateInvoiceLine(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		if (!invoice.getInvoiceItems().isEmpty()) {
			Beans.get(InvoiceService.class).getNetAmount(invoice, response);
		}
	}

	// validate onclick method call
	public void validate(ActionRequest request, ActionResponse response) {
		Invoice invoice = request.getContext().asType(Invoice.class);
		if (invoice.getId() != null) {
			invoice = Beans.get(InvoiceRepository.class).find(invoice.getId());
			// calll seq service and save
			Beans.get(InvoiceService.class).setReference(invoice, response);
			// response.setReload(true);
			// response.setValue(fieldName, value);
		} else {
			Beans.get(InvoiceService.class).setReference(invoice, response);
		}
	}

	public void setRefenceOnCanclled(ActionRequest request, ActionResponse response) {
		Invoice invoice = request.getContext().asType(Invoice.class);
		if (invoice.getId() != null) {
			invoice = Beans.get(InvoiceRepository.class).find(invoice.getId());
			Beans.get(InvoiceService.class).setReferenceRemove(invoice, response);
		} else {
			Beans.get(InvoiceService.class).setReferenceRemove(invoice, response);
		}
	}

	@SuppressWarnings("unchecked")
	public void selectedProduct(ActionRequest request, ActionResponse response) {
		List<Integer> productId = new ArrayList<>();
		productId = (List<Integer>) request.getContext().get("_ids");

		try {
			System.err.println(productId);
			List<InvoiceLine> invoiceLines = new ArrayList<>();
			for (Integer product : productId) {
				Product product2 = productRepository.find(product.longValue());
				System.err.println(product2);
				InvoiceLine invoiceLine = new InvoiceLine();
				invoiceLine.setProduct(product2);
				System.out.println(invoiceLine);
				try {
					invoiceLine = Beans.get(InvoiceService.class).setNewInvoice(invoiceLine);
					invoiceLines.add(invoiceLine);
				} catch (Exception ex) {
					System.err.println(ex);
				}
			}
			response.setValue("invoiceItems", invoiceLines);
		} catch (Exception e) {
			System.err.println();
		}
	}
}
