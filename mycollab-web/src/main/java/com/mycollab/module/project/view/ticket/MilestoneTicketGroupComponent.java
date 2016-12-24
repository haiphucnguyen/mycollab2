package com.mycollab.module.project.view.ticket;

import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.module.project.ui.components.IBlockContainer;
import com.mycollab.module.project.ui.components.IGroupComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.4.5
 */
public class MilestoneTicketGroupComponent extends MVerticalLayout implements IGroupComponent, IBlockContainer {
    private Label headerLbl;
    private CssLayout wrapBody;

    private String titleValue;

    MilestoneTicketGroupComponent(String titleValue) {
        this.titleValue = titleValue;
        this.setMargin(new MarginInfo(true, false, true, false));
        wrapBody = new CssLayout();
        wrapBody.setWidth("100%");
        wrapBody.addStyleName(WebThemes.BORDER_LIST);
        wrapBody.addStyleName("cursor_move");
        headerLbl = ELabel.h3("");
        this.with(headerLbl, wrapBody);
        refresh();
    }

    @Override
    public void refresh() {
        if (wrapBody.getComponentCount() > 0) {
            updateTitle();
        } else {
            ComponentContainer parent = (ComponentContainer) getParent();
            if (parent != null) {
                parent.removeComponent(this);
            }
        }
    }

    void insertTicket(ProjectTicket ticket) {
        wrapBody.addComponent(new TicketRowRenderer(ticket));
        updateTitle();
    }

    private void updateTitle() {
        headerLbl.setValue(String.format("%s (%d)", titleValue, wrapBody.getComponentCount()));
    }
}
