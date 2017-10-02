package com.mycollab.module.project.event

import com.mycollab.vaadin.event.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object TicketEvent {
    class GotoDashboard(source: Any, val data: Any?) : ApplicationEvent(source)

    class NewTicketAdded(source: Any, val typeVal: String, val typeIdVal: Int) : ApplicationEvent(source)

    class SearchRequest(source: Any, data: ProjectTicketSearchCriteria) : ApplicationEvent(source)

    class HasTicketPropertyChanged(source: Any, data: String) : ApplicationEvent(source)
}