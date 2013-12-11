package com.esofthead.mycollab.vaadin.ui;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.ui.Field;

public abstract class DefaultFormLayoutFactory implements IFormLayoutFactory {
	private static final long serialVersionUID = 1L;

	private Map<String, Field> fields = new HashMap<String, Field>();

	@Override
	public void attachField(Object propertyId, Field field) {
		// TODO Auto-generated method stub

	}

}
