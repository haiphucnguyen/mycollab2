package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
object TicketEvent {
  
  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class SearchRequest(source: AnyRef, data: ProjectTicketSearchCriteria) extends ApplicationEvent(source, data) {}
  
}
