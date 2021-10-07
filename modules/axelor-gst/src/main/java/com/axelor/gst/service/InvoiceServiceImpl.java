package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.List;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class InvoiceServiceImpl  implements InvoiceService {
	
	
	/*
	 * @Override public BigDecimal netamount(InvoiceLine invoiceline) {
	 * 
	 * System.out.println("netamount is called"); Integer quantity =
	 * invoiceline.getQuantity(); BigDecimal price = invoiceline.getPrice();
	 * 
	 * BigDecimal netAmount = invoiceline.getNetAmount();
	 * 
	 * BigDecimal qtyvalueOf = BigDecimal.valueOf(quantity) ;
	 * 
	 * netAmount = qtyvalueOf.multiply(price);
	 * 
	 * return netAmount; }
	 * 
	 * @Override public BigDecimal gstrate(InvoiceLine invoiceline) { BigDecimal
	 * gstRate = invoiceline.getProduct().getGstRate();
	 * 
	 * return gstRate; }
	 */

	@Override
	public Invoice netamount(Invoice invoiceline,ActionRequest request,ActionResponse response) {
		
		System.out.println("netamount is called");
		
		List<InvoiceLine> invoiceItems = invoiceline.getInvoiceItems();
		
		for(InvoiceLine invoice : invoiceItems )
		{
			BigDecimal netAmount = invoice.getNetAmount();
			Integer quantity = invoice.getQuantity();
			BigDecimal costPrice = invoice.getProduct().getCostPrice();
			
			BigDecimal qtyvalueOf = BigDecimal.valueOf(quantity) ;
			
			netAmount = qtyvalueOf.multiply(costPrice);
			
			invoice.setNetAmount(netAmount);
			
			response.setValue("netAmount", netAmount);
			
			System.out.println(netAmount);
		}
		
		//invoiceline.setInvoiceItems(invoiceItems);
		return invoiceline;
	}

	@Override
	public BigDecimal gstrate(Invoice invoiceline) {
		// TODO Auto-generated method stub
		return null;
	}

}
