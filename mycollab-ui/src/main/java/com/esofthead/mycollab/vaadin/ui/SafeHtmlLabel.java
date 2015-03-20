package com.esofthead.mycollab.vaadin.ui;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
public class SafeHtmlLabel extends Label {
    public SafeHtmlLabel(String value) {
        super(StringUtils.formatRichText(value), ContentMode.HTML);
    }
}
