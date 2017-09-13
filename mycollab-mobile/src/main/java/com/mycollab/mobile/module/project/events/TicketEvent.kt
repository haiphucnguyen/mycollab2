package com.mycollab.mobile.module.project.events

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class TicketEvent {
    class GotoDashboard(source: Any, data: ProjectTicketSearchCriteria) : ApplicationEvent(source, data) {}
}