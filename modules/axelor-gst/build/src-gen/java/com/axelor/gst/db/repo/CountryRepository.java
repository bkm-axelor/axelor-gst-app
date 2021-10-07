package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.Country;

public class CountryRepository extends JpaRepository<Country> {

	public CountryRepository() {
		super(Country.class);
	}

	public Country findByName(String name) {
		return Query.of(Country.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

