package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.ListSelect;

@SuppressWarnings("serial")
public class ValueListSelect extends ListSelect {
	public void loadData(String[] values) {
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT_DEFAULTS_ID);

		for (int i = 0; i < values.length; i++) {
			this.addItem(values[i]);
		}
		
		this.setRows(4);
		this.setMultiSelect(true);
	}
}
