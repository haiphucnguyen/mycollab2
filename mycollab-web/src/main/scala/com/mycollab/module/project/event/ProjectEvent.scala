package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.ProjectGenericItem

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMyProject(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoTagListView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoFavoriteView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class SelectFavoriteItem(source: AnyRef, data: ProjectGenericItem) extends ApplicationEvent(source, data) {}

  class TimeLoggingChangedEvent(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoCalendarView(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoGanttChart(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoUserDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
