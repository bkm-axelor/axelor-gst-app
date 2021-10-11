package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;

public interface InvoiceService {
	
	public BigDecimal calculatenetamount(Invoice invoice);

	public BigDecimal calculatenetigst(Invoice invoice);

	public BigDecimal calculatenetcgst(Invoice invoice);

	public BigDecimal calculatenetsgst(Invoice invoice);

	public BigDecimal calculatenetgamount(Invoice invoice);

	public String generateref(Invoice invoice, SequenceService seq);


}
