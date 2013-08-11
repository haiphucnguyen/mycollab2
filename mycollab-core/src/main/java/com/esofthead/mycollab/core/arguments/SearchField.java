package com.esofthead.mycollab.core.arguments;

import com.esofthead.mycollab.core.utils.BeanUtility;

public class SearchField {

	public static final String OR = "OR";
	public static final String AND = "AND";

	protected String operation = AND;

	public SearchField() {
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return BeanUtility.printBeanObj(this);
	}
}
