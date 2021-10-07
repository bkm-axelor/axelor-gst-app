package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.City;

public class CityRepository extends JpaRepository<City> {

	public CityRepository() {
		super(City.class);
	}

	public City findByName(String name) {
		return Query.of(City.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

