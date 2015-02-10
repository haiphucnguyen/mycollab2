package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class FontIconLabel extends Label {
    public FontIconLabel(FontAwesome icon) {
        super(icon.getHtml(), ContentMode.HTML);
    }
}
