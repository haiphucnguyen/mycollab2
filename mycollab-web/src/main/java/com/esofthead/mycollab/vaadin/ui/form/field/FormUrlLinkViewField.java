package com.esofthead.mycollab.vaadin.ui.form.field;

import org.apache.commons.lang3.StringUtils;

import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UrlLink;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.3
 *
 */
public class FormUrlLinkViewField extends CustomField<String> {

	private static final long serialVersionUID = 1L;

	private String url;

	public FormUrlLinkViewField(String url) {
		this.url = url;
	}

	@Override
	public Class<String> getType() {
		return String.class;
	}

	@Override
	protected Component initContent() {
		if (StringUtils.isBlank(url)) {
			Label lbl = new Label("&nbsp;");
			lbl.setContentMode(ContentMode.HTML);
			lbl.setWidth("100%");
			return lbl;
		} else {
			final Link link = new UrlLink(url);
			link.setWidth(UIConstants.DEFAULT_CONTROL_WIDTH);
			return link;
		}
	}
}
