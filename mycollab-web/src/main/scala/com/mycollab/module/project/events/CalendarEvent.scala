package com.mycollab.module.project.events

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectAssignmentSearchCriteria

/**
  * @author MyCollab Ltd
  * @since 5.2.1
  */
object CalendarEvent {
  
  class SearchRequest(source: AnyRef, data: ProjectAssignmentSearchCriteria) extends ApplicationEvent(source, data) {};
}
