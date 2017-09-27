package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class CalendarEvent {
    class SearchRequest(source: Any, data: ProjectTicketSearchCriteria) : ApplicationEvent(source, data)
}