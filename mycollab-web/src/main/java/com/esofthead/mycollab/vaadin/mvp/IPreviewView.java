package com.esofthead.mycollab.vaadin.mvp;

public interface IPreviewView<T> extends View {
	void previewItem(T item);
	
	T getItem();
}
