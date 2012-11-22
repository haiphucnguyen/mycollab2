package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Field;
import com.vaadin.ui.Form;

public class GenericForm extends Form {
	private static final long serialVersionUID = 1L;
	
	private IFormLayoutFactory factory;

	public GenericForm() {
		super();
	}
	
	public void setFormLayoutFactory(IFormLayoutFactory factory) {
		this.factory = factory;
	}

	@Override
	public void attach() {
		super.attach();
		this.setLayout(factory.getLayout());
	}

	@Override
	protected void attachField(Object propertyId, Field field) {
		factory.attachField(propertyId, field);
	}
}
