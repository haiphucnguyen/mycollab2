package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public interface IFormLayoutFactory {
	Layout getLayout();

	void attachField(Object propertyId, Field field);
}
