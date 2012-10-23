package com.esofthead.mycollab.core.arguments;

public class SearchField {
	public static String OR = "OR";

	public static String AND = "AND";

	protected String operation = AND;
	
	public SearchField() {
		
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}
}
