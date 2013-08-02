package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public interface TemplateSearchableView<S extends SearchCriteria> extends View {
	void doDefaultSearch();

	void doSearch(S searchCriteria);
}
