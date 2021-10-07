package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gst.db.Address;

public class AddressRepository extends JpaRepository<Address> {

	public AddressRepository() {
		super(Address.class);
	}

}

