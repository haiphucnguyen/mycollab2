package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.data.Item;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextField;

public abstract class DefaultFormEditFieldFactory extends DefaultFieldFactory {
	private static final long serialVersionUID = 1L;

	@Override
	public Field createField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) {
		Field field = onCreateField(item, propertyId, uiContext);
		if (field == null) {
			field = super.createField(item, propertyId, uiContext);
			if (field instanceof TextField) {
				((TextField) field).setNullRepresentation("");
				((TextField) field).setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
				((TextField) field).setCaption(null);
			}
		}
		return field;
	}

	abstract protected Field onCreateField(Item item, Object propertyId,
			com.vaadin.ui.Component uiContext) ;
}
