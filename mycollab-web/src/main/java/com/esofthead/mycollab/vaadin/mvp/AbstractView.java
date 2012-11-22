package com.esofthead.mycollab.vaadin.mvp;

import java.io.Serializable;

import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout implements View,
		Serializable {

	public static String SAVE_ACTION = "Save";
	
	public static String SAVE_AND_NEW_ACTION = "Save & New";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";
	
	public static String DELETE_ACTION = "Delete";
	
	public static String CLONE_ACTION = "Clone";

	@Override
	public ComponentContainer getWidget() {
		return this;
	}
}
