package com.mycollab.vaadin.ui;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author MyCollab Ltd.
 * @since 5.0.1
 */
public class HeaderWithFontAwesome extends CssLayout {
    private Label wrappedLbl;
    private FontAwesome iconFont;
    private String title;

    private HeaderWithFontAwesome(FontAwesome iconFont, String title, String primaryStyle) {
        super();
        wrappedLbl = new Label();
        wrappedLbl.setContentMode(ContentMode.HTML);
        wrappedLbl.setStyleName(primaryStyle);
        wrappedLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        this.iconFont = iconFont;
        updateTitle(title);
        this.addComponent(wrappedLbl);
    }

    public static final HeaderWithFontAwesome h2(FontAwesome iconFont, String title) {
        return new HeaderWithFontAwesome(iconFont, title, ValoTheme.LABEL_H2);
    }

    public static final HeaderWithFontAwesome h3(FontAwesome iconFont, String title) {
        return new HeaderWithFontAwesome(iconFont, title, ValoTheme.LABEL_H3);
    }

    public void updateTitle(String value) {
        this.title = value;
        wrappedLbl.setValue(iconFont.getHtml() + " " + value);
    }

    public void appendToTitle(String value) {
        wrappedLbl.setValue(iconFont.getHtml() + " " + title + " " + value);
    }
}
