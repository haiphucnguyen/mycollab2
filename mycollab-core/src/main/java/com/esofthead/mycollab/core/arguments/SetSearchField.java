package com.esofthead.mycollab.core.arguments;

public class SetSearchField<T> extends SearchField {
	public T[] values;
	
	public SetSearchField() {
		
	}
	
	public SetSearchField(String oper, T[] values) {
		this.values = values;
		this.operation = oper;
	}

	public Object[] getValues() {
		return values;
	}

	public void setValues(T[] values) {
		this.values = values;
	}
}
