package com.esofthead.mycollab.vaadin.mvp.ui;

public interface TemplateItemView<T> extends View {
	void addNewItem();
	
	void editItem(T item);
	
	void viewItem(T item);
}
