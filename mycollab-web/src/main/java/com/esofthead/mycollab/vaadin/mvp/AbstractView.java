package com.esofthead.mycollab.vaadin.mvp;

import java.io.Serializable;

import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractView<P> extends VerticalLayout implements View,
		Serializable {

	public static String SAVE_ACTION = "Save";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";

	protected boolean isInitialized = false;

	public AbstractView() {
		super();
	}

	public void initLayout() {
		if (!isInitialized) {
			initializeLayout();
			isInitialized = true;
		}
	}

	protected abstract void initializeLayout();

}
