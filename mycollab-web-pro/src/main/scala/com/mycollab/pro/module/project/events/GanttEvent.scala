package com.mycollab.pro.module.project.events

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.TaskPredecessor
import com.mycollab.pro.module.project.view.assignments.gantt.GanttItemWrapper

/**
  * @author MyCollab Ltd
  * @since 5.1.3
  */
object GanttEvent {

  class ClearGanttItemsNeedUpdate(source: AnyRef) extends ApplicationEvent(source, null) {}

  class UpdateGanttItem(source: AnyRef, data: GanttItemWrapper) extends ApplicationEvent(source, data) {}

  class DeleteGanttItemUpdateToQueue(source: AnyRef, data: GanttItemWrapper) extends ApplicationEvent(source, data) {}

  class AddGanttItemUpdateToQueue(source: AnyRef, data: GanttItemWrapper) extends ApplicationEvent(source, data) {}

  class ModifyPredecessors(source: GanttItemWrapper, predecessors: java.util.List[TaskPredecessor]) extends ApplicationEvent(source, predecessors) {}

}
