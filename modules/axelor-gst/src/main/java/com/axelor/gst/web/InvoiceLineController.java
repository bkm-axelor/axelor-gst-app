package com.axelor.gst.web;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.service.InvoiceLineService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.persist.Transactional;

public class InvoiceLineController {

	

	public void getHsbn(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		String hsbn = Beans.get(InvoiceLineService.class).getHsbn(invoiceline);
		
		invoiceline.setHsbn(hsbn);
		response.setValue("hsbn", hsbn);

	}
	public void getProdctName(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		String fullname = Beans.get(InvoiceLineService.class).getProdctName(invoiceline);
		
		invoiceline.setItem(fullname);
		response.setValue("item", fullname);
	}
	public void getGst(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		BigDecimal gstRate = Beans.get(InvoiceLineService.class).getGst(invoiceline);
		
		invoiceline.setGstRate(gstRate);
		response.setValue("gstRate", gstRate);
	}
	public void getPrice(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		BigDecimal salePrice = Beans.get(InvoiceLineService.class).getPrice(invoiceline);
		
		invoiceline.setPrice(salePrice);
		response.setValue("price", salePrice);
	}
	@Transactional
	public void getAmount(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		Invoice invoice = request.getContext().getParent().asType(Invoice.class);

		Beans.get(InvoiceLineService.class).getnetamount(invoiceline, invoice, response);

	}
}
