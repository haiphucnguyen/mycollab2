package com.esofthead.mycollab.vaadin.ui;

import java.util.Collection;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.service.ISearchableService;
import com.esofthead.mycollab.vaadin.events.HasPagableHandlers;
import com.esofthead.mycollab.vaadin.events.HasSelectableItemHandlers;

public interface IPagedBeanTable<SearchService extends ISearchableService<S>, S extends SearchCriteria, T>
		extends HasSelectableItemHandlers<T>, HasPagableHandlers {
	
	void setSearchCriteria(S searchCriteria);

	Collection<T> getCurrentDataList();
}
