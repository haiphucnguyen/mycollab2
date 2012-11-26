package com.esofthead.mycollab.vaadin.mvp;

public interface IFormAddView<T> extends View{
	void addNewItem();

	void editItem(T item);
}
