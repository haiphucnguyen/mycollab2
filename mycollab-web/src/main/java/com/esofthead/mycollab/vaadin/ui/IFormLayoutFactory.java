package com.esofthead.mycollab.vaadin.ui;

import java.io.Serializable;

import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;

public interface IFormLayoutFactory extends Serializable {
	Layout getLayout();

	void attachField(Object propertyId, Field field);
}
