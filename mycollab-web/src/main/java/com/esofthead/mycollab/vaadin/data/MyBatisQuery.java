package com.esofthead.mycollab.vaadin.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.vaadin.addons.lazyquerycontainer.Query;

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.persistence.IPagableService;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;

@SuppressWarnings("serial")
public class MyBatisQuery<S extends SearchCriteria> implements Query,
		Serializable {

	private IPagableService<S> pagableService;
	private S searchCriteria;
	private MyBatisQueryDefinition<S> queryDefinition;

	public MyBatisQuery(MyBatisQueryDefinition<S> queryDefinition,
			S searchCriteria) {
		this.pagableService = queryDefinition.getService();
		this.searchCriteria = searchCriteria;
		this.queryDefinition = queryDefinition;
	}

	@Override
	public Item constructItem() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean deleteAllItems() {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Item> loadItems(int startIndex, int count) {
		List beanItems = pagableService.findPagableListByCriteria(
				searchCriteria, startIndex, count);
		final List<Item> items = new ArrayList<Item>();
		for (final Object entity : beanItems) {
			items.add(new BeanItem<Object>(entity));
		}

		return items;
	}

	@Override
	public void saveItems(final List<Item> addedItems,
			final List<Item> modifiedItems, final List<Item> removedItems) {
		throw new UnsupportedOperationException();

	}

	@Override
	public int size() {
		return pagableService.getTotalCount(searchCriteria);
	}

}
