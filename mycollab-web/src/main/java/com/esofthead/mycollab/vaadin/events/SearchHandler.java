package com.esofthead.mycollab.vaadin.events;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface SearchHandler<S extends SearchCriteria> {
	void onSearch(S criteria);
}
