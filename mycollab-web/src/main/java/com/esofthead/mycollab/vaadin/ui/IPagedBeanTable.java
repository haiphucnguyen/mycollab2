package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableService;

public interface IPagedBeanTable<SearchService extends ISearchableService<S>, S extends SearchCriteria, T> {
	void setSearchCriteria(S searchCriteria);
}
