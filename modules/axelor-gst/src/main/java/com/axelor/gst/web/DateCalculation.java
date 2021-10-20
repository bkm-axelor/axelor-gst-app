package com.axelor.gst.web;

import java.time.ZonedDateTime;
import java.util.Date;

import com.axelor.meta.CallMethod;

public class DateCalculation {

	 @CallMethod
	public String calculateMonth(Date dates){
		
		Date date = Date.from(ZonedDateTime.now().minusMonths(1).toInstant());
		
		System.out.println(dates);
		System.out.println(date);
		return "true";
	}
}
