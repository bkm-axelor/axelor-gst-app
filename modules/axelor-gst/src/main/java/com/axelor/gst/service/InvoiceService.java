package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.rpc.ActionResponse;

public interface InvoiceService {

	public BigDecimal calculateNetAmount(Invoice invoice);

	public BigDecimal calculateNetIgst(Invoice invoice);

	public BigDecimal calculateNetCgst(Invoice invoice);

	public BigDecimal calculateNetSgst(Invoice invoice);

	public BigDecimal calculateNetGrossAmount(Invoice invoice);

	public void getNetAmount(Invoice invoice, ActionResponse response);

	public void setReference(Invoice invoice, ActionResponse response);

	public void setReferenceRemove(Invoice invoice, ActionResponse response);

	public InvoiceLine setNewInvoice(InvoiceLine invoiceLine);

}
