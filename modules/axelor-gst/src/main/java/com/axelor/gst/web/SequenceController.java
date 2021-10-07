package com.axelor.gst.web;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.service.SequenceService;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class SequenceController {
	
	//this method is called on onsave of sequence-form
	public void nextnum(ActionRequest request, ActionResponse response) {
		Sequence sequence = request.getContext().asType(Sequence.class);
		Sequence seq = null;
		String nextNum = Beans.get(SequenceService.class).generateNextNbr(sequence);
		//response.setValue();
		
	}

}
