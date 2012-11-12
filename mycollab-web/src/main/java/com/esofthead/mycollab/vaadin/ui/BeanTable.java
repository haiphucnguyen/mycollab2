package com.esofthead.mycollab.vaadin.ui;

import com.jensjansson.pagedtable.PagedTable;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItem;

public class BeanTable<T> extends PagedTable {
	private static final long serialVersionUID = 1L;

	public BeanTable() {
		super();
		this.addListener(new PagedTable.PageChangeListener() {
			@Override
			public void pageChanged(PagedTableChangeEvent event) {

				System.out.println("Page change: " + event.getCurrentPage()
						+ " " + event.getTotalAmountOfPages() + " ");
			}
		});
	}

	@SuppressWarnings("unchecked")
	public T getBeanByIndex(Object itemId) {
		Container container = this.getContainerDataSource();
		BeanItem<T> item = (BeanItem<T>) container.getItem(itemId);
		return (item == null) ? null : item.getBean();
	}
}
