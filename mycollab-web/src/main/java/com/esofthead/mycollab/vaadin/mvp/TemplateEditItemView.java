package com.esofthead.mycollab.vaadin.mvp;

public interface TemplateEditItemView<T> extends View {
	void addNewItem();
	
	void editItem(T item);
}
