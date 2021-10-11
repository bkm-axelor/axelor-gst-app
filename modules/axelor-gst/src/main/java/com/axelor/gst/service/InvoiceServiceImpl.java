package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;

public class InvoiceServiceImpl implements InvoiceService {

	@Override
	public BigDecimal calculatenetamount(Invoice invoice) {
		
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();
		
		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netAmount = invoiceLine.getNetAmount();
			totalamount = totalamount.add(netAmount);
			
		}
		return totalamount;
	}

	@Override
	public BigDecimal calculatenetigst(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();
		
		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netigst = invoiceLine.getIgst();
			totalamount = totalamount.add(netigst);
			
		}
		return totalamount;
	}

	@Override
	public BigDecimal calculatenetcgst(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();
		
		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netcgst = invoiceLine.getCgst();
			totalamount = totalamount.add(netcgst);
			
		}
		return totalamount;
	}

	@Override
	public BigDecimal calculatenetsgst(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();
		
		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netsgst = invoiceLine.getSgst();
			totalamount = totalamount.add(netsgst);
			
		}
		return totalamount;
	}

	@Override
	public BigDecimal calculatenetgamount(Invoice invoice) {
		BigDecimal totalamount = new BigDecimal(0.00);
		List<InvoiceLine> invoiceItems = invoice.getInvoiceItems();
		
		for (InvoiceLine invoiceLine : invoiceItems) {
			BigDecimal netgamount = invoiceLine.getGrossAmount();
			totalamount = totalamount.add(netgamount);
			
		}
		return totalamount;
	}

	@Override
	public String generateref(Invoice invoice, SequenceService seq) {
		String nextNbrAndIncrement = seq.getNextNbrAndIncrement("Invoice");
		System.out.println(nextNbrAndIncrement);
		return nextNbrAndIncrement;
	}

}
