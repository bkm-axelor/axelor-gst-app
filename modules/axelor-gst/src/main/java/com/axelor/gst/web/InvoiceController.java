package com.axelor.gst.web;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.gst.service.InvoiceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class InvoiceController {

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
		
		System.out.println("hi2");
		
		if(!invoice.getInvoiceItems().isEmpty()) {
			System.out.println(invoice);
			
			Beans.get(InvoiceService.class).getNetAmount(invoice, response);
		}
	}

	// validate onclick method call
	public void validate(ActionRequest request, ActionResponse response) {
		Invoice invoice = request.getContext().asType(Invoice.class);
		if(invoice.getId() != null) {
			invoice = Beans.get(InvoiceRepository.class).find(invoice.getId());
			// calll seq service and save
			Beans.get(InvoiceService.class).setReference(invoice,response);
			//response.setReload(true);
			//response.setValue(fieldName, value);
		} else {
			Beans.get(InvoiceService.class).setReference(invoice,response);
		}
		
	}

}
