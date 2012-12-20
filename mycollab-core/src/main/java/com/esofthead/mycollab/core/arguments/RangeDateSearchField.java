package com.esofthead.mycollab.core.arguments;

import java.util.Date;

import com.esofthead.mycollab.core.utils.DateTimeUtils;

public class RangeDateSearchField extends RangeDateTimeSearchField {
	
	public RangeDateSearchField() {
		super();
	}

	public RangeDateSearchField(Date from, Date to) {
		super(DateTimeUtils.convertDate(from), DateTimeUtils.convertDate(to));
	}
	
	public RangeDateSearchField(String oper, Date from, Date to) {
		super(oper, DateTimeUtils.convertDate(from), DateTimeUtils.convertDate(to));
	}
	
	public RangeDateSearchField getLast7Days() {
		from = new Date(new Date().getTime() - 7*24*60*60*1000);
		to = new Date();
		return new RangeDateSearchField(from, to);
	}
	
	public RangeDateSearchField getNext7Days() {
		from = new Date();
		to = new Date(new Date().getTime() + 7*24*60*60*1000);
		return new RangeDateSearchField(from, to);
	}

}
