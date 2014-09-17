package com.esofthead.mycollab.mobile.ui;

import java.util.List;

import com.esofthead.mycollab.vaadin.AppContext;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.0
 * 
 */
public class I18nValueComboBox extends ValueComboBox {

	private static final long serialVersionUID = 7466956429723924052L;

	public final void loadData(List<? extends Enum<?>> values) {
		this.setItemCaptionMode(ItemCaptionMode.EXPLICIT_DEFAULTS_ID);

		for (Enum<?> entry : values) {
			this.addItem(entry.name());
			this.setItemCaption(entry.name(), AppContext.getMessage(entry));
		}

		if (!this.isNullSelectionAllowed()) {
			this.select(this.getItemIds().iterator().next());
		}
	}
}
