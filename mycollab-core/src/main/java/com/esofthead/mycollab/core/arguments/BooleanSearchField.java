package com.esofthead.mycollab.core.arguments;

public class BooleanSearchField extends SearchField {
	private boolean value;
	
	
	
	public BooleanSearchField(String oper, boolean value) {
		this.operation = oper;
		this.value = value;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}

}
