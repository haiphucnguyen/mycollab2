package com.esofthead.mycollab.module.crm.ui.components;

import java.util.ArrayList;
import java.util.List;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.vaadin.events.HasSearchHandlers;
import com.esofthead.mycollab.vaadin.events.SearchHandler;
import com.vaadin.ui.CustomComponent;

@SuppressWarnings("serial")
public class GenericSearchPanel<S extends SearchCriteria> extends
		CustomComponent implements HasSearchHandlers<S> {

	private List<SearchHandler<S>> handers;

	@Override
	public void addSearchHandler(SearchHandler<S> handler) {
		if (handers == null) {
			handers = new ArrayList<SearchHandler<S>>();
		}
		handers.add(handler);
	}

	protected void notifySearchHandler(S criteria) {
		if (handers != null) {
			for (SearchHandler<S> handler : handers) {
				handler.onSearch(criteria);
			}
		}
	}

}
