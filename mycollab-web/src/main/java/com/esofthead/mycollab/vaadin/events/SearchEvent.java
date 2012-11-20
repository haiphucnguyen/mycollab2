package com.esofthead.mycollab.vaadin.events;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class SearchEvent<S extends SearchCriteria> {
	private Object source;

	private S criteria;

	public SearchEvent(Object source, S criteria) {
		this.source = source;
		this.criteria = criteria;
	}

	public Object getSource() {
		return source;
	}

	public void setSource(Object source) {
		this.source = source;
	}

	public S getCriteria() {
		return criteria;
	}

	public void setCriteria(S criteria) {
		this.criteria = criteria;
	}
}
