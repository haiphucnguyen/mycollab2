package com.esofthead.mycollab.core.arguments;

import java.util.Arrays;
import java.util.Collection;

public class SetSearchField<T> extends SearchField {
	public T[] values;

	public SetSearchField() {
		this(AND, (Collection) Arrays.asList());
	}

	@SuppressWarnings("unchecked")
	public SetSearchField(String oper, Collection<T> values) {
		this(oper, (T[]) values.toArray());
	}

	public SetSearchField(T... values) {
		this(SearchField.AND, values);
	}

	public SetSearchField(String oper, T... values) {
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
