package com.axelor.gst.web;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.service.InvoiceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.persist.Transactional;

public class InvoiceLineController {

	/* @Transactional */
	public void getnetamount(ActionRequest request, ActionResponse response) {

		Invoice invoice = request.getContext().asType(Invoice.class);
		BigDecimal bigDecimal = new BigDecimal(0.00);
		try {

			Invoice newinvoice = Beans.get(InvoiceService.class).netamount(invoice, request, response);

			List<InvoiceLine> invoiceItems = newinvoice.getInvoiceItems();

			System.out.println(invoiceItems);

			for (InvoiceLine invoices : invoiceItems) {
				
				// System.out.println(invoices.getNetAmount());

				 BigDecimal netAmount = invoices.getNetAmount(); 

				//invoices.setNetAmount(netAmount);

				 bigDecimal = bigDecimal.add(netAmount); 

				//response.setValue("netamount", netAmount);

				/* System.out.println(netAmount); */
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

			response.setError("Check there is some null value");
		}
		
		 System.out.println(bigDecimal); 
		response.setValue("netAmount", bigDecimal);

	}
}
