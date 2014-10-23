package com.esofthead.mycollab.vaadin.ui.form.field;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.RichTextArea;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
public class RichTextEditField extends CustomField<String> {
	private static final long serialVersionUID = 1L;

	private RichTextArea textArea = new RichTextArea();

	@Override
	protected Component initContent() {
		textArea.setWidth("100%");
		return textArea;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void setPropertyDataSource(Property newDataSource) {
		String value = (String) newDataSource.getValue();
		if (value != null) {
			textArea.setValue(value);
		} else {
			textArea.setValue("");
		}
		super.setPropertyDataSource(newDataSource);
	}

	@Override
	public Class<? extends String> getType() {
		return String.class;
	}

}
