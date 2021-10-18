package com.axelor.gst.service;

import com.axelor.gst.db.Party;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class PartyServiceImpl implements PartyService {

	@Inject
	SequenceService sequenceService;
	
	@Override
	public void setReference(Party party, ActionResponse response) {
		String refrence = sequenceService.getNextNumber("Party");
		party.setReference(refrence);
		response.setValue("reference", refrence);
	}

}
