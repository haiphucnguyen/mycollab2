package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

/**
  * @author MyCollab Ltd
  * @since 5.2.1
  */
object CalendarEvent {
  
  class SearchRequest(source: AnyRef, data: ProjectTicketSearchCriteria) extends ApplicationEvent(source, data) {};
}
