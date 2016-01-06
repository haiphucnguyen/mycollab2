package com.esofthead.mycollab.mobile.ui;

import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIUtils;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import org.vaadin.viritin.layouts.MHorizontalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
public class Section extends MHorizontalLayout {
    public Section(FontAwesome icon, Component comp) {
        this.setStyleName("section");
        this.with(new ELabel(icon.getHtml(), ContentMode.HTML).withWidthUndefined(), comp).expand(comp);
        this.setWidth(UIUtils.getBrowserWidthInPixels());
    }
}
