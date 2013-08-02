package com.esofthead.mycollab.vaadin.ui;

import org.vaadin.addon.customfield.CustomField;

import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class CheckboxField extends CustomField {
	
	private CheckBox chk;
	
	public CheckboxField() {
		chk = new CheckBox();
		chk.setImmediate(true);
		chk.setWriteThrough(true);
		this.setCompositionRoot(chk);
	}
	
	public CheckboxField(String caption) {
		chk = new CheckBox(caption);
		chk.setImmediate(true);
		chk.setWriteThrough(true);
		this.setCompositionRoot(chk);
	}
	
	public CheckBox getCheckBox() {
		return chk;
	}

	@Override
	public Class<?> getType() {
		return null;
	}

}
