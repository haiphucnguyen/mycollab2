package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Table;

public class BeanTable<T> extends Table {
	private static final long serialVersionUID = 1L;

	public BeanTable() {
		super();
	}

	@SuppressWarnings("unchecked")
	public T getBeanByIndex(Object itemId) {
		Container container = this.getContainerDataSource();
		BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}
}
