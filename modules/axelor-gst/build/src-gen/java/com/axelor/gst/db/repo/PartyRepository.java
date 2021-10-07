package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.Party;

public class PartyRepository extends JpaRepository<Party> {

	public PartyRepository() {
		super(Party.class);
	}

	public Party findByName(String name) {
		return Query.of(Party.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

