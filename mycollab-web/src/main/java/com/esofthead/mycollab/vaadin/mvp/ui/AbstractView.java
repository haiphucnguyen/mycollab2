package com.esofthead.mycollab.vaadin.mvp.ui;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.vaadin.mvp.eventbus.EventBus;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public abstract class AbstractView extends VerticalLayout implements View,
		Serializable {

	public static String SAVE_ACTION = "Save";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";

	@Autowired
	protected EventBus eventBus;

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
