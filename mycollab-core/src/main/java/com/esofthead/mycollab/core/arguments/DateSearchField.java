package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class DateSearchField extends DateTimeSearchField {

	public DateSearchField(String oper, Date value) {
		super(oper, convertDate(value));
	}
	
	private static Date convertDate(Date value) {
		return null;
	}
}
