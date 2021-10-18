package com.axelor.gst.web;

import com.axelor.gst.db.Party;
import com.axelor.gst.service.PartyService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class PartyController {
	
	public void setRefrence(ActionRequest request, ActionResponse response) {
		Party party = request.getContext().asType(Party.class);
		if(party.getId() != null) {
			Beans.get(PartyService.class).setReference(party, response);
		}
	}

}
