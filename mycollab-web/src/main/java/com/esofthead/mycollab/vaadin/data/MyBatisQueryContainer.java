package com.esofthead.mycollab.vaadin.data;

import org.vaadin.addons.lazyquerycontainer.LazyQueryContainer;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

@SuppressWarnings("serial")
public class MyBatisQueryContainer<T extends Object> extends LazyQueryContainer {

	public MyBatisQueryContainer(QueryDefinition queryDefinition,
			QueryFactory queryFactory) {
		super(queryDefinition, queryFactory);
	}

}
