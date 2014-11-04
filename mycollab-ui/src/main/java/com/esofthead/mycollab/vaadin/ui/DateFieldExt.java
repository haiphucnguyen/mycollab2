package com.esofthead.mycollab.vaadin.ui;

import java.util.Date;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.DateField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.4
 *
 */
public class DateFieldExt extends DateField {
	private static final long serialVersionUID = 1L;

	public DateFieldExt() {
		this("");
	}

	public DateFieldExt(String caption) {
		super(caption);
		this.setTimeZone(AppContext.getTimezone());
	}

	public DateFieldExt(String caption, Date value) {
		super(caption, value);
		this.setTimeZone(AppContext.getTimezone());
	}
}
