package com.mycollab.module.project.view.ticket;

import com.mycollab.module.project.domain.ProjectTicket;
import com.mycollab.vaadin.ui.IBeanList;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;

/**
 * @author MyCollab Ltd
 * @since 7.0.2
 */
public class TicketRowRenderer implements IBeanList.RowDisplayHandler<ProjectTicket> {
    @Override
    public Component generateRow(IBeanList<ProjectTicket> host, ProjectTicket item, int rowIndex) {
        return new Label("A");
    }
}
