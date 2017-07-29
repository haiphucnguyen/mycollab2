package com.mycollab.module.project.view;

import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria;
import com.mycollab.vaadin.events.HasSearchHandlers;
import com.mycollab.vaadin.web.ui.InitializingView;

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
public interface ICalendarDashboardView extends InitializingView {
    void display();

    void queryAssignments(ProjectTicketSearchCriteria criteria);

    HasSearchHandlers<ProjectTicketSearchCriteria> getSearchHandlers();
}
