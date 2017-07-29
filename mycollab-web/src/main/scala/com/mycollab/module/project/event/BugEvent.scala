package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.tracker.domain.criteria.BugSearchCriteria

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object BugEvent {
  
  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
  class SearchRequest(source: AnyRef, data: BugSearchCriteria) extends ApplicationEvent(source, data) {}
  
  class BugChanged(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}
  
}
