package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.vaadin.ui.EmailLink;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
public class FormEmailLinkViewField extends CustomField<String> {

	private static final long serialVersionUID = 1L;

	private String email;

	public FormEmailLinkViewField(final String email) {
		this.email = email;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		final EmailLink emailLink = new EmailLink(email);
		emailLink.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
		return emailLink;
	}
}
