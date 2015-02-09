package com.esofthead.mycollab.module.project.ui.components;

import com.esofthead.mycollab.module.project.ui.AssetsManager;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class HeaderView extends Label {
    public HeaderView(String resId, String title) {
        super(AssetsManager.getAsset(resId).getHtml() + " " + title, ContentMode.HTML);
        this.setStyleName("hdr-text");
    }
}
