package com.esofthead.mycollab.vaadin.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class HeaderWithFontAwesome  extends Label {
    private FontAwesome iconFont;
    private String title;

    public HeaderWithFontAwesome(FontAwesome iconFont, String title) {
        super();
        this.setContentMode(ContentMode.HTML);
        this.iconFont = iconFont;
        this.setStyleName("hdr-text");
        updateTitle(title);
    }

    public void updateTitle(String value) {
        this.title = value;
        this.setValue(iconFont.getHtml() + " " + value);
    }

    public void appendToTitle(String value) {
        this.setValue(iconFont.getHtml() + " " + title + " " + value);
    }
}
