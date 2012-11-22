package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Form;

public abstract class DefaultFormLayoutFactory implements IFormLayoutFactory {
	protected Form form;

	public DefaultFormLayoutFactory(Form form) {
		this.form = form;
	}
}
