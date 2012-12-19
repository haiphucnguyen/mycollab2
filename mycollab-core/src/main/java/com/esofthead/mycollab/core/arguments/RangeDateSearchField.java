package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class RangeDateSearchField extends SearchField {
	private Date from;

	private Date to;

	public RangeDateSearchField(Date from, Date to) {
		this(SearchField.AND, from, to);
	}

	public RangeDateSearchField(String oper, Date from, Date to) {
		this.operation = oper;
		this.from = from;
		this.to = to;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}
}
