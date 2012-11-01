package com.esofthead.mycollab.vaadin.data;

import org.vaadin.addons.lazyquerycontainer.LazyQueryDefinition;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.IPagableService;

@SuppressWarnings("serial")
public class MyBatisQueryDefinition<T extends SearchCriteria> extends
		LazyQueryDefinition {

	private IPagableService<T> service;

	public MyBatisQueryDefinition(IPagableService<T> service,
			boolean compositeItems, int batchSize) {
		super(compositeItems, batchSize);

		this.service = service;
	}

	public IPagableService<T> getService() {
		return service;
	}
}
