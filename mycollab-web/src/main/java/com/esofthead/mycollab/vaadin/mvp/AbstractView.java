package com.esofthead.mycollab.vaadin.mvp;

import java.io.Serializable;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout implements View,
		Serializable {

	public static String SAVE_ACTION = "Save";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";

	@Override
	public ComponentContainer getWidget() {
		return this;
	}
}
