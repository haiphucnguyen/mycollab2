package com.esofthead.mycollab.core.arguments;

import java.util.Collection;

public class SetSearchField<T> extends SearchField {
	public T[] values;
	
	public SetSearchField() {
		
	}
	
	@SuppressWarnings("unchecked")
	public SetSearchField(String oper, Collection<T> values) {
		this.operation = oper;
		this.values = (T[])values.toArray();
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
