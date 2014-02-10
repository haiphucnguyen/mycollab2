package com.esofthead.mycollab.core.arguments;

import java.io.Serializable;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public abstract class Criterion implements Serializable {
	private static final long serialVersionUID = 1L;

	protected String operation;
	
	protected String expression;

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
}
