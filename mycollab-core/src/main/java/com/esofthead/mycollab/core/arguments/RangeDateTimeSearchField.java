package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class RangeDateTimeSearchField extends SearchField {
	protected Date from;

	protected Date to;
	
	public RangeDateTimeSearchField() {
		this(SearchField.AND, new Date(), new Date());
	}

	public RangeDateTimeSearchField(Date from, Date to) {
		this(SearchField.AND, from, to);
	}

	public RangeDateTimeSearchField(String oper, Date from, Date to) {
		this.operation = oper;
		this.from = from;
		this.to = to;
	}
	
	public RangeDateTimeSearchField getLast7Days() {
		this.from = new Date(new Date().getTime() - 7*24*60*60*1000);
		this.to = new Date();
		return new RangeDateTimeSearchField(this.from, this.to);
	}
	
	public RangeDateTimeSearchField getNext7Days() {
		this.from = new Date();
		this.to = new Date(new Date().getTime() + 7*24*60*60*1000);
		return new RangeDateTimeSearchField(this.from, this.to);
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
