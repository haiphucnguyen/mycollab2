package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Field;
import com.vaadin.ui.Form;

public class GenericForm extends Form {
	private static final long serialVersionUID = 1L;

	public static String SAVE_ACTION = "Save";

	public static String SAVE_AND_NEW_ACTION = "Save & New";

	public static String EDIT_ACTION = "Edit";

	public static String CANCEL_ACTION = "Cancel";

	public static String DELETE_ACTION = "Delete";

	public static String CLONE_ACTION = "Clone";

	private IFormLayoutFactory factory;

	public GenericForm() {
		super();
	}

	public void setFormLayoutFactory(IFormLayoutFactory factory) {
		this.factory = factory;
		this.setLayout(factory.getLayout());
	}

	@Override
	protected void attachField(Object propertyId, Field field) {
		factory.attachField(propertyId, field);
	}
}
