package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.ui.ComboBox;

public class KeyCaptionComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public KeyCaptionComboBox(boolean nullSelectionAllowed) {
		super();
		this.setItemCaptionMode(ITEM_CAPTION_MODE_EXPLICIT);
		this.setNullSelectionAllowed(nullSelectionAllowed);
	}

	public void addItem(Object itemId, String caption) {
		this.addItem(itemId);
		this.setItemCaption(itemId, caption);
	}

}
