package com.mycollab.module.project.view;

import com.mycollab.module.project.domain.FollowingTicket;
import com.mycollab.module.project.domain.criteria.FollowingTicketSearchCriteria;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.web.ui.InitializingView;
import com.mycollab.vaadin.web.ui.table.AbstractPagedBeanTable;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface FollowingTicketView extends InitializingView {
    void displayTickets();

    HasSearchHandlers<FollowingTicketSearchCriteria> getSearchHandlers();

    AbstractPagedBeanTable<FollowingTicketSearchCriteria, FollowingTicket> getPagedBeanTable();
}
