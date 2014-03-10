package com.esofthead.mycollab.core.arguments;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class BetweenValuesSearchField extends SearchField {
	private static final long serialVersionUID = 1L;

	private String expression;

	private Object value;

	private Object secondValue;

	public BetweenValuesSearchField(String oper, String expression, Object value,
			Object secondValue) {
		this.operation = oper;
		this.expression = expression;
		this.value = value;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Object secondValue) {
		this.secondValue = secondValue;
	}
}
