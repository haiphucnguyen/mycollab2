package com.esofthead.mycollab.core.arguments;

public class StringSearchField extends SearchField {
	private String value;

	public StringSearchField(String value) {
		this(SearchField.AND, value);
	}

	public StringSearchField(String oper, String value) {
		this.operation = oper;
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
