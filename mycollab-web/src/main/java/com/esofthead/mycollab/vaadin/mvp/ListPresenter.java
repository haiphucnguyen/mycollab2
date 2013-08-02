package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface ListPresenter<S extends SearchCriteria> extends Presenter {

	void doSearch(S searchCriteria);
}
