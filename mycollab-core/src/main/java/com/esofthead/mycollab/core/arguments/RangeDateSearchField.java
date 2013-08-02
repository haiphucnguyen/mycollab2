package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class RangeDateSearchField extends RangeDateTimeSearchField {

	public RangeDateSearchField(Date from, Date to) {
		super(DateTimeUtils.convertDate(from), DateTimeUtils.convertDate(to));
	}
	
	public RangeDateSearchField(String oper, Date from, Date to) {
		super(oper, DateTimeUtils.convertDate(from), DateTimeUtils.convertDate(to));
	}
}
