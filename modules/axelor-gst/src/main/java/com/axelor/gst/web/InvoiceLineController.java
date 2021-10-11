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

	

	public void gethsbn(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		String hsbn = Beans.get(InvoiceLineService.class).gethsbn(invoiceline);
		
		invoiceline.setHsbn(hsbn);
		response.setValue("hsbn", hsbn);

	}
	public void getprodctname(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		String fullname = Beans.get(InvoiceLineService.class).getprodctname(invoiceline);
		
		invoiceline.setItem(fullname);
		response.setValue("item", fullname);
	}
	public void getgst(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		BigDecimal gstRate = Beans.get(InvoiceLineService.class).getgst(invoiceline);
		
		invoiceline.setGstRate(gstRate);
		response.setValue("gstRate", gstRate);
	}
	public void getprice(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		
		BigDecimal costPrice = Beans.get(InvoiceLineService.class).getprice(invoiceline);
		
		invoiceline.setPrice(costPrice);
		response.setValue("price", costPrice);
	}
	@Transactional
	public void getamount(ActionRequest request, ActionResponse response) {

		InvoiceLine invoiceline = request.getContext().asType(InvoiceLine.class);
		Invoice invoice = request.getContext().getParent().asType(Invoice.class);

		Beans.get(InvoiceLineService.class).getnetamount(invoiceline, invoice, response);

	}
}
