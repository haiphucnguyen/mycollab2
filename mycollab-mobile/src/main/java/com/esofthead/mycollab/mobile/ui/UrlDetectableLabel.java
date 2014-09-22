package com.esofthead.mycollab.mobile.ui;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */
public class UrlDetectableLabel extends Label {
	private static final long serialVersionUID = -8041297963487354112L;

	public UrlDetectableLabel(String value) {
		super(formatExtraLink(value), ContentMode.HTML);
	}

	private static String formatExtraLink(String value) {
		if (value == null || "".equals(value)) {
			return "&nbsp;";
		}
		return value
				.replaceAll(
						"(?:https?|ftps?)://[\\w/%.-][/\\??\\w=?\\w?/%.-]?[/\\?&\\w=?\\w?/%.-]*",
						"<a href=\"$0\" target=\"_system\">$0</a>");
	}

}
