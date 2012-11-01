package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.ComboBox;

@SuppressWarnings("serial")
public class ValueComboBox extends ComboBox {
	public ValueComboBox() {

	}

	public void loadData(String[] values) {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		for (int i = 0; i < values.length; i++) {
			this.addItem(values[i]);
		}
	}
}
