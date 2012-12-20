package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class DateSearchField extends DateTimeSearchField {

	public DateSearchField(String oper, Date value) {
		super(oper, DateTimeUtils.convertDate(value));
	}
	
	public DateSearchField(String oper, String comparision, Date value) {
		super(oper, comparision, DateTimeUtils.convertDate(value));
	}
}
