package com.esofthead.mycollab.vaadin.data;

import org.vaadin.addons.lazyquerycontainer.LazyQueryDefinition;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.ISearchableService;

@SuppressWarnings("serial")
public class MyBatisQueryDefinition<T extends SearchCriteria> extends
		LazyQueryDefinition {

	private ISearchableService<T> service;

	public MyBatisQueryDefinition(ISearchableService<T> service,
			boolean compositeItems, int batchSize) {
		super(compositeItems, batchSize);

		this.service = service;
	}

	public ISearchableService<T> getService() {
		return service;
	}
}
