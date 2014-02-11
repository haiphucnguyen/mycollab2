package com.esofthead.mycollab.core.arguments;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class NoValueSearchField extends ExtSearchField {

	private String expression;

	public NoValueSearchField(String oper, String expression) {
		this.operation = oper;
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
