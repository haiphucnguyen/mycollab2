package com.esofthead.mycollab.vaadin.resource.ui;

import com.vaadin.ui.CheckBox;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 * 
 */
public class CheckBoxDecor extends CheckBox {
	private static final long serialVersionUID = 1L;

	public CheckBoxDecor(String title, boolean value) {
		super(title, value);
	}

	public void setValueWithoutNotifyListeners(boolean value) {
		this.setInternalValue(value);
	}
}
