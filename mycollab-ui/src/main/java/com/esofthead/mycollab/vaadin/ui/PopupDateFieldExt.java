package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.PopupDateField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.4
 *
 */
public class PopupDateFieldExt extends PopupDateField {
	private static final long serialVersionUID = 1L;

	public PopupDateFieldExt() {
		super();
		this.setTimeZone(AppContext.getTimezone());
	}
}
