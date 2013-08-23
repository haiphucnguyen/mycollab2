package com.esofthead.mycollab.core.arguments;

import java.util.Date;

public class DateTimeSearchField extends SearchField {

	public static String LESSTHAN = "<";
	public static String LESSTHANEQUAL = "<=";
	public static String GREATERTHAN = ">";
	public static String GREATERTHANEQUAL = ">=";
	public static String EQUAL = "=";
	public static String NOTEQUAL = "<>";

	private Date value;
	private String comparision;

	public DateTimeSearchField() {
		this(AND, null);
	}

	public DateTimeSearchField(String oper, Date value) {
		this(oper, DateTimeSearchField.LESSTHAN, value);
	}

	public DateTimeSearchField(String oper, String comparision, Date value) {
		this.operation = oper;
		this.value = value;
		this.comparision = comparision;
	}

	public Date getValue() {
		return value;
	}

	public void setValue(Date value) {
		this.value = value;
	}

	public String getComparision() {
		return comparision;
	}

	public void setComparision(String comparision) {
		this.comparision = comparision;
	}

}
