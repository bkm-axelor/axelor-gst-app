package com.axelor.gst.service;

import com.axelor.gst.db.Party;
import com.axelor.rpc.ActionResponse;

public interface PartyService {

	public void setReference(Party party, ActionResponse response);
}
