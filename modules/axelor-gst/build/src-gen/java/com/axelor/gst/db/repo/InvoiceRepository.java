package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gst.db.Invoice;

public class InvoiceRepository extends JpaRepository<Invoice> {

	public InvoiceRepository() {
		super(Invoice.class);
	}

}

