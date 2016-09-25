package com.mycollab.module.project.events

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
object AssignmentEvent {
  
  class NewAssignmentAdd(source: AnyRef, @BeanProperty val typeVal: String, @BeanProperty val typeIdVal: Integer) extends
    ApplicationEvent(source, null) {}
  
  class SearchRequest(source: AnyRef, data: ProjectTicketSearchCriteria) extends ApplicationEvent(source, data) {};
}
