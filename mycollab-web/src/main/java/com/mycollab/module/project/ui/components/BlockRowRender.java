package com.mycollab.module.project.ui.components;

import com.mycollab.vaadin.web.ui.WebUIConstants;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
public class BlockRowRender extends MVerticalLayout {
    public BlockRowRender() {
        withMargin(false).withFullWidth().addStyleName(WebUIConstants.BORDER_LIST_ROW);
    }

    public void selfRemoved() {

    }
}
