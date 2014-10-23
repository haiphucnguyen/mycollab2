package com.esofthead.mycollab.vaadin.ui.form.field;

import org.apache.commons.lang3.StringUtils;

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
public class DefaultViewField extends CustomField<String> {

	private String value;
	private ContentMode contentMode;

	private static final long serialVersionUID = 1L;

	public DefaultViewField(final String value) {
		this(value, ContentMode.TEXT);
	}

	public DefaultViewField(final String value, final ContentMode contentMode) {
		this.value = value;
		this.contentMode = contentMode;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		final Label label = new Label();
		label.setWidth("100%");
		label.setContentMode(contentMode);

		if (StringUtils.isNotBlank(value)) {
			label.setValue(value);
		} else {
			label.setValue("");
		}

		return label;
	}
}
