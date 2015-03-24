package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object TaskListEvent {

  class GotoGanttChartView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoAdd(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoEdit(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoRead(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class ReoderTaskList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class SaveReoderTaskList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

  class GotoTaskListScreen(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
