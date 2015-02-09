package com.esofthead.mycollab.module.crm.ui.components;

import com.esofthead.mycollab.module.crm.ui.CrmAssetsManager;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd.
 * @since 5.0.0
 */
public class CrmViewHeader extends Label{
    public CrmViewHeader(String resId, String title) {
        super(CrmAssetsManager.getAsset(resId).getHtml() + " " + title, ContentMode.HTML);
        this.setStyleName("hdr-text");
    }
}
