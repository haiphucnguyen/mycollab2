package com.esofthead.mycollab.core.arguments;

public class SetSearchField extends SearchField {
	public Object[] values;
	
	public SetSearchField() {
		
	}
	
	public SetSearchField(String oper, Object[] values) {
		this.values = values;
		this.operation = oper;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}
}
