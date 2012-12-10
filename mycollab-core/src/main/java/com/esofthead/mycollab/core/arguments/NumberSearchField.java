package com.esofthead.mycollab.core.arguments;

public class NumberSearchField extends SearchField {
	private Number value;
	
	public NumberSearchField(String oper, Number value) {
		this.operation = oper;
		this.value = value;
	}

	public Number getValue() {
		return value;
	}

	public void setValue(Number value) {
		this.value = value;
	}
}
