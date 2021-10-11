package com.axelor.gst.web;

import com.axelor.gst.db.Sequence;
import com.axelor.gst.service.SequenceService;
import com.axelor.inject.Beans;
import com.axelor.meta.db.MetaModel;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;

public class SequenceController {
	
	//this method is called on onsave of sequence-form 
	public void nextnum(ActionRequest request, ActionResponse response) {
		Sequence sequence = request.getContext().asType(Sequence.class);
		
		String nextNum = Beans.get(SequenceService.class).generateNextNbr(sequence);
		
		response.setValue("nextNumber", nextNum);
	}
	
//	public void getNextNbrAndIncrement(ActionRequest request, ActionResponse response) {
//		Sequence sequence = request.getContext().asType(Sequence.class);
//		
//		MetaModel model = sequence.getModel();
//		
//		String nextNum = Beans.get(SequenceService.class).getNextNbrAndIncrement(model.getName());
//		
//		sequence.setNextNumber(nextNum);
//		response.setValue("nextNumber", nextNum);
//	}

}
