package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gst.db.InvoiceLine;

public class InvoiceLineRepository extends JpaRepository<InvoiceLine> {

	public InvoiceLineRepository() {
		super(InvoiceLine.class);
	}

}

