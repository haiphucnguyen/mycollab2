package com.mycollab.module.project.view

import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria
import com.mycollab.vaadin.events.HasSearchHandlers
import com.mycollab.vaadin.web.ui.InitializingView

/**
 * @author MyCollab Ltd
 * @since 5.2.1
 */
interface ICalendarDashboardView : InitializingView {
    fun display()

    fun queryAssignments(criteria: ProjectTicketSearchCriteria)

    val searchHandlers: HasSearchHandlers<ProjectTicketSearchCriteria>
}
