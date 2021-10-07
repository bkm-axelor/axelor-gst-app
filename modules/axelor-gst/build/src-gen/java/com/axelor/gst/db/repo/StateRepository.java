package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.db.Query;
import com.axelor.gst.db.State;

public class StateRepository extends JpaRepository<State> {

	public StateRepository() {
		super(State.class);
	}

	public State findByName(String name) {
		return Query.of(State.class)
				.filter("self.name = :name")
				.bind("name", name)
				.fetchOne();
	}

}

