package com.esofthead.mycollab.vaadin.ui;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;

public class BeanTable<T> extends PagedTable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public T getBeanByIndex(Object itemId) {
		Container container = this.getContainerDataSource();
		BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}
}
