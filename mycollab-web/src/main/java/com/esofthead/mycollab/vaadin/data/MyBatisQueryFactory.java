package com.esofthead.mycollab.vaadin.data;

import java.io.Serializable;

import org.vaadin.addons.lazyquerycontainer.Query;
import org.vaadin.addons.lazyquerycontainer.QueryDefinition;
import org.vaadin.addons.lazyquerycontainer.QueryFactory;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class MyBatisQueryFactory<S extends SearchCriteria> implements
		QueryFactory, Serializable {

	private static final long serialVersionUID = 1L;
	/** The query definition. */
	private MyBatisQueryDefinition<S> myBatisQueryDefinition;

	private S searchCriteria;

	public MyBatisQueryFactory(S searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * Gets the definition of properties to be queried.
	 * 
	 * @param queryDefinition
	 *            The query definition.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setQueryDefinition(final QueryDefinition queryDefinition) {
		myBatisQueryDefinition = (MyBatisQueryDefinition<S>) queryDefinition;
	}

	/**
	 * Constructs a new query according to the given sort state.
	 * 
	 * @param sortPropertyIds
	 *            Properties participating in the sorting.
	 * @param sortStates
	 *            List of sort order for the properties. True corresponds
	 *            ascending and false descending.
	 * @return A new query constructed according to the given sort state.
	 */
	@Override
	public Query constructQuery(final Object[] sortPropertyIds,
			final boolean[] sortStates) {
		return new MyBatisQuery<S>(myBatisQueryDefinition, searchCriteria);
	}

}
