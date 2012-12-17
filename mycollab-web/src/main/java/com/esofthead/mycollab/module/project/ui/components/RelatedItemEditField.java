package com.esofthead.mycollab.module.project.ui.components;

import org.vaadin.addon.customfield.CustomField;

import com.esofthead.mycollab.vaadin.ui.FieldSelection;
import com.vaadin.ui.Label;

public class RelatedItemEditField extends CustomField implements FieldSelection {
	private static final long serialVersionUID = 1L;
	
	public RelatedItemEditField() {
		this.setCompositionRoot(new Label("Related"));
	}

	@Override
	public void fireValueChange(Object data) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

}
