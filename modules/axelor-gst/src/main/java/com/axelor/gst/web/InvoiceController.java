package com.axelor.gst.web;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.gst.service.InvoiceService;
import com.axelor.gst.service.SequenceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class InvoiceController {

	@Inject
	SequenceService seq;
	
	@Inject
	InvoiceRepository irpo;

	public void calculatenetamount(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetamount = Beans.get(InvoiceService.class).calculatenetamount(invoice);

		response.setValue("netAmount", calculatenetamount);

	}

	public void calculatenetigst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetigst = Beans.get(InvoiceService.class).calculatenetigst(invoice);

		response.setValue("netIGST", calculatenetigst);

	}

	public void calculatenetcgst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetcgst = Beans.get(InvoiceService.class).calculatenetcgst(invoice);

		response.setValue("netCSGT", calculatenetcgst);

	}

	public void calculatenetsgst(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetsgst = Beans.get(InvoiceService.class).calculatenetsgst(invoice);

		response.setValue("netSGST", calculatenetsgst);

	}

	public void calculatenetgamount(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);

		BigDecimal calculatenetgamount = Beans.get(InvoiceService.class).calculatenetgamount(invoice);

		response.setValue("grossAmount", calculatenetgamount);

	}

	public void generateref(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);
		
		// Invoice invoices = irpo.all().fetchOne().
		
//		System.out.println(invoices);
		
		Long id = invoice.getId();
		
		String generateref;
		
		if (id != null) {
			generateref = invoice.getReference();
		} else {
			generateref = Beans.get(InvoiceService.class).generateref(invoice, seq);
			
			if(generateref == null) {
				response.setError("enter the model name");
			}
		}
		
		invoice.setReference(generateref);
		response.setValue("reference", generateref);
	}

}
