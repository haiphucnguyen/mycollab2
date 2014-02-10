package com.esofthead.mycollab.core.arguments.criterion;

import java.util.List;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.0
 * 
 */
public class InListCriterion extends Criterion {
	private static final long serialVersionUID = 1L;

	private List<?> values;

	public List<?> getValues() {
		return values;
	}

	public void setValues(List<?> values) {
		this.values = values;
	}
}
