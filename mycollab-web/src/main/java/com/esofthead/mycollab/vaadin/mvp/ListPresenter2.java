package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface ListPresenter2<S extends SearchCriteria> extends
		ListPresenter<S> {
	void doDefaultSearch();
}
