package com.esofthead.mycollab.core.arguments;

public class BooleanSearchField extends SearchField {

	public static String IS = "is";

	private boolean value;

	private String comparision;
	
	public BooleanSearchField() {
		this(false);
	}

	public BooleanSearchField(boolean value) {
		this(SearchField.AND, value);
	}

	public BooleanSearchField(String oper, boolean value) {
		this(oper, BooleanSearchField.IS, value);
	}

	public BooleanSearchField(String oper, String comparision, boolean value) {
		this.operation = oper;
		this.comparision = comparision;
		this.value = value;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

	public String getComparision() {
		return comparision;
	}

	public void setComparision(String comparision) {
		this.comparision = comparision;
	}
}
