package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Field;

public class DefaultBeanFieldGroupFieldFactory<B> extends
		AbstractBeanFieldGroupFieldFactory<B> {
	private static final long serialVersionUID = 1L;

	public DefaultBeanFieldGroupFieldFactory(GenericBeanForm<B> form) {
		super(form);
	}

	@Override
	protected Field onCreateField(Object propertyId) {
		return null;
	}

}
