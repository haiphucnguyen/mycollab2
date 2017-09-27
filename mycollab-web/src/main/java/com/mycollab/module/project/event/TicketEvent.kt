package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class TicketEvent {
    class GotoDashboard(source: Any, data: Any?) : ApplicationEvent(source, data)

    class NewTicketAdded(source: Any, val typeVal: String, val typeIdVal: Int) : ApplicationEvent(source, null)

    class SearchRequest(source: Any, data: ProjectTicketSearchCriteria) : ApplicationEvent(source, data)

    class HasTicketPropertyChanged(source: Any, data: String) : ApplicationEvent(source, data)
}