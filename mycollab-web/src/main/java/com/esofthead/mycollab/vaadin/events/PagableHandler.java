package com.esofthead.mycollab.vaadin.events;

public interface PagableHandler {
	void move(int newPageNumber);
	
	void displayItemChange(int numOfItems);
}
