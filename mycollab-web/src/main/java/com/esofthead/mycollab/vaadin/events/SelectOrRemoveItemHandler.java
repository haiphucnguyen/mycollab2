package com.esofthead.mycollab.vaadin.events;

public interface SelectOrRemoveItemHandler<T> {

	void onSelect(T item);
	
	void onRemove(T item);
}
