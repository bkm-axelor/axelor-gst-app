package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.db.repo.SequenceRepository;
import com.google.inject.Inject;

public class SequenceServiceImpl implements SequenceService {
@Inject SequenceRepository seqRepo;
	
	@Override
	public String generateNextNbr(Sequence seq) {
		//add logic to concate prefix suffix here and return
		return null;
	}

	@Override
	public String getNextNbrAndIncrement(String modelName) {
		Sequence seq = seqRepo.all().filter("self.model.name = ?",modelName).fetchOne();
		
		//if seq null throw error from here else return seq.getNextNbr and increment NextUmber
		return null;
	}

}
