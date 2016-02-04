package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object BugEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: BugSearchCriteria) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoKanbanView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class SearchRequest(source: AnyRef, data: BugSearchCriteria) extends ApplicationEvent(source, data) {}

  class NewBugAdded(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}

}
