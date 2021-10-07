package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.Contact;

public class ContactRepository extends JpaRepository<Contact> {

	public ContactRepository() {
		super(Contact.class);
	}

	public Contact findByName(String name) {
		return Query.of(Contact.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

