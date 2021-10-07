package com.axelor.gst.db.repo;

import com.axelor.db.JpaRepository;
import com.axelor.gst.db.Sequence;

public class SequenceRepository extends JpaRepository<Sequence> {

	public SequenceRepository() {
		super(Sequence.class);
	}

}

