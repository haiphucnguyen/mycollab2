package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class DateSearchField extends SearchField {
	private Date value;

	public DateSearchField() {

	}

	public DateSearchField(String oper, Date value) {
		this.operation = oper;
		this.value = value;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

}
