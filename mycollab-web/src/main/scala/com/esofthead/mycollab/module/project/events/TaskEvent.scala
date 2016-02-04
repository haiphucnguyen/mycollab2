package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object TaskEvent {

  class SearchRequest(source: AnyRef, data: TaskSearchCriteria) extends ApplicationEvent(source, data) {};

  class HasTaskChange(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class NewTaskAdded(source: AnyRef, data: Integer) extends ApplicationEvent(source, data) {}

  class Search(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoKanbanView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
