package com.esofthead.mycollab.core.arguments.criterion;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class SingleValueCriterion extends Criterion {
	private static final long serialVersionUID = 1L;
	
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
}
