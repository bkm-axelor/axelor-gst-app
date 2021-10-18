package com.axelor.gst.service;

import com.axelor.gst.db.Sequence;

public interface SequenceService {
	
	public String generateNextNbr(Sequence seq);
	//when you call this method suppose for invoice validate btn onclick controller method you will pass "Invoice" as string model name
	public String autoIncrementSeq(Sequence sequence);
	
	public String getNextNumber(String modelName);

}
