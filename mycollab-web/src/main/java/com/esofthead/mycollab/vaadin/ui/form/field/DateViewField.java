package com.esofthead.mycollab.vaadin.ui.form.field;

import java.util.Date;

import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
@SuppressWarnings("rawtypes")
public class DateViewField extends CustomField {
	private static final long serialVersionUID = 1L;

	private Date date;

	public DateViewField(final Date date) {
		this.date = date;
	}

	@Override
	public Class<?> getType() {
		return Object.class;
	}

	@Override
	protected Component initContent() {
		final Label l = new Label();
		l.setWidth("100%");
		if (date == null) {
			l.setValue("&nbsp;");
			l.setContentMode(ContentMode.HTML);
		} else {
			l.setValue(AppContext.formatDate(date));
		}
		return l;
	}
}
