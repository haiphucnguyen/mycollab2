package com.esofthead.mycollab.vaadin.ui;

import com.hp.gagawa.java.elements.A;
import com.vaadin.server.Page;
import org.apache.commons.lang3.StringUtils;
import org.vaadin.maddon.button.MButton;

/**
 * @author MyCollab Ltd.
 * @since 5.0.5
 */
public class ButtonLink extends MButton {
    public ButtonLink(final String caption, final String href) {
        this.addStyleName("link");
        this.setCaptionAsHtml(true);
        A captionLink = new A(href).appendText(caption);
        this.withCaption(captionLink.write());
    }

    public ButtonLink withEnabled(boolean enabled) {
        this.setEnabled(enabled);
        return this;
    }
}
