package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria

import scala.beans.BeanProperty

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
object TicketEvent {
  
  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class NewTicketAdded(source: AnyRef, @BeanProperty val typeVal: String, @BeanProperty val typeIdVal: Integer) extends
    ApplicationEvent(source, null) {}
  
  class SearchRequest(source: AnyRef, data: ProjectTicketSearchCriteria) extends ApplicationEvent(source, data) {}
  
  class HasTicketPropertyChanged(source: AnyRef, data: String) extends ApplicationEvent(source, data) {}
  
}
