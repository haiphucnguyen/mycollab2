package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.core.arguments.Order;
import com.vaadin.ui.ComboBox;

public class ItemOrderComboBox extends ComboBox {
	private static final long serialVersionUID = 1L;

	public ItemOrderComboBox() {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT);
		this.setNullSelectionAllowed(false);
		this.addItem(Order.ASCENDING);
		this.setItemCaption(Order.ASCENDING, "Ascending");

		this.addItem(Order.DESCENDING);
		this.setItemCaption(Order.DESCENDING, "Descending");
		this.select(Order.ASCENDING);
	}
}
