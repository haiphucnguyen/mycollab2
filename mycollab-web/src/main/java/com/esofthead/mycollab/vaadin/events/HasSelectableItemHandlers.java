package com.esofthead.mycollab.vaadin.events;

public interface HasSelectableItemHandlers<T> {
	void addSelectableItemHandler(SelectableItemHandler<T> handler);
	
	int currentViewCount();
	
	int totalItemsCount();
}
