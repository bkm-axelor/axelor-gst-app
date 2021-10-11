package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public interface InvoiceLineService {

	public Invoice netamount(Invoice invoiceline,ActionRequest request,ActionResponse response);

	public BigDecimal gstrate(Invoice invoiceline);

	public BigDecimal getamount(BigDecimal price, Integer quantity);

	public String gethsbn(InvoiceLine invoiceline);

	public BigDecimal getgst(InvoiceLine invoiceline);

	public BigDecimal getprice(InvoiceLine invoiceline);

	public String getprodctname(InvoiceLine invoiceline);
	
	public void getnetamount(InvoiceLine invoiceline,Invoice invoice,ActionResponse response);

}
