package com.esofthead.mycollab.vaadin.events;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface HasSearchHandlers<S extends SearchCriteria> {
	void addSearchHandler(SearchHandler<S> handler);
}
