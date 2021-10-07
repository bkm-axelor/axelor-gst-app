package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public interface InvoiceService {

	public Invoice netamount(Invoice invoiceline,ActionRequest request,ActionResponse response);

	public BigDecimal gstrate(Invoice invoiceline);

}
