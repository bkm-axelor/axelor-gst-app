package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public interface InvoiceLineService {

	public Invoice netamount(Invoice invoiceline,ActionRequest request,ActionResponse response);

	public BigDecimal getAmount(BigDecimal price, Integer quantity);

	public String getHsbn(InvoiceLine invoiceline);

	public BigDecimal getGst(InvoiceLine invoiceline);

	public BigDecimal getPrice(InvoiceLine invoiceline);

	public String getProdctName(InvoiceLine invoiceline);
	
	public void getnetamount(InvoiceLine invoiceline,Invoice invoice,ActionResponse response);

}
