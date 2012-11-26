package com.esofthead.mycollab.vaadin.mvp;

public abstract class FormAddView<T> extends AbstractView implements IFormAddView<T> {
	private static final long serialVersionUID = 1L;
	
	protected T beanItem;
	
	public static int ADD_MODE = 0;
	
	public static int EDIT_MODE = 1;
	
	protected int formMode;

	protected abstract void onNewItem();
	
	protected abstract void onEditItem(T item);

	@Override
	public void addNewItem() {
		formMode = ADD_MODE;
		onNewItem();
	}

	@Override
	public void editItem(T item) {
		formMode = EDIT_MODE;
		onEditItem(item);
	}
}
