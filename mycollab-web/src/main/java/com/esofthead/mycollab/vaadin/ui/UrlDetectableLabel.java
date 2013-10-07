package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.vaadin.ui.Label;

public class UrlDetectableLabel extends Label {
	private static final long serialVersionUID = 1L;

	public UrlDetectableLabel(String value) {
		super(StringUtils.formatExtraLink(value), Label.CONTENT_XHTML);
	}
}
