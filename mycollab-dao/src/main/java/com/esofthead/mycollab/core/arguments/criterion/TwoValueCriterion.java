package com.esofthead.mycollab.core.arguments.criterion;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class TwoValueCriterion extends Criterion {
	private static final long serialVersionUID = 1L;

	private Object value;
	
	private Object secondValue;

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
