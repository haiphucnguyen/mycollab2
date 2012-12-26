package com.esofthead.mycollab.vaadin.mvp;

import com.esofthead.mycollab.vaadin.ui.PrintPreview;

public interface IPreviewView<T> extends View, PrintPreview.PrintListener {
	void previewItem(T item);
	
	T getItem();
}
