package com.mycollab.module.project.view.ticket;

import com.mycollab.vaadin.ui.UIUtils;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author MyCollab Ltd
 * @since 5.4.4
 */
public class TicketEditWindow extends MWindow {
    TicketEditWindow(String type, Integer typeId) {
        withModal(false).withWidth("900px").withHeight(UIUtils.getBrowserHeight() + "px")
                .withPosition(UIUtils.getBrowserWidth() - 900, 0);
        addBlurListener(blurEvent -> close());
    }
}
