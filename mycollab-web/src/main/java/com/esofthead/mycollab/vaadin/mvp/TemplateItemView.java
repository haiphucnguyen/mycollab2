package com.esofthead.mycollab.vaadin.mvp;

public interface TemplateItemView<T> extends View {
	void addNewItem();
	
	void editItem(T item);
}
