package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface ListCommand<S extends SearchCriteria> {

	void doSearch(S searchCriteria);
}
