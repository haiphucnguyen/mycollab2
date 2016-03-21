package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.project.domain.ProjectGenericItem

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object ProjectEvent {

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoMyProject(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoTagListView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoFavoriteView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class SelectFavoriteItem(source: AnyRef, data: ProjectGenericItem) extends ApplicationEvent(source, data) {}

  class TimeLoggingChangedEvent(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoCalendarView(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoGanttChart(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoReportConsole(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
