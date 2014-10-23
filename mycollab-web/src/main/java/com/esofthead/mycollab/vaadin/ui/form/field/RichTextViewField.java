package com.esofthead.mycollab.vaadin.ui.form.field;

import com.esofthead.mycollab.core.utils.StringUtils;
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
public class RichTextViewField extends CustomField {
	private static final long serialVersionUID = 1L;

	private String url;

	public RichTextViewField(String url) {
		this.url = url;
	}

	@Override
	public Class<?> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		if (org.apache.commons.lang3.StringUtils.isBlank(url)) {
			Label lbl = new Label("&nbsp;");
			lbl.setContentMode(ContentMode.HTML);
			lbl.setWidth("100%");
			return lbl;
		} else {
			final Label link = new Label(StringUtils.formatExtraLink(url),
					ContentMode.HTML);
			return link;
		}
	}
}
