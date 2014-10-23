package com.esofthead.mycollab.vaadin.ui.form.field;

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
public class I18nFormViewField extends CustomField<String> {
	private static final long serialVersionUID = 1L;

	private String key;
	@SuppressWarnings("rawtypes")
	private Class<? extends Enum> enumClass;

	@SuppressWarnings("rawtypes")
	public I18nFormViewField(final String key, Class<? extends Enum> enumCls) {
		this.key = key;
		this.enumClass = enumCls;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		final Label label = new Label();
		label.setWidth("100%");
		label.setContentMode(ContentMode.TEXT);

		if (org.apache.commons.lang3.StringUtils.isNotBlank(key)) {
			try {
				String value = AppContext.getMessage(enumClass, key);
				label.setValue(value);
			} catch (Exception e) {
				label.setValue("");
			}
		} else {
			label.setValue("");
		}

		return label;
	}
}
