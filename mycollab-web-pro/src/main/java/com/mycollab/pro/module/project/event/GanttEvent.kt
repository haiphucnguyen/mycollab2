package com.mycollab.pro.module.project.event

import com.mycollab.vaadin.event.ApplicationEvent
import com.mycollab.module.project.domain.TaskPredecessor
import com.mycollab.pro.module.project.view.assignments.gantt.GanttItemWrapper

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class GanttEvent {
    class ClearGanttItemsNeedUpdate(source: Any) : ApplicationEvent(source)

    class UpdateGanttItem(source: Any, val data: GanttItemWrapper) : ApplicationEvent(source)

    class DeleteGanttItemUpdateToQueue(source: Any, val data: GanttItemWrapper) : ApplicationEvent(source)

    class AddGanttItemUpdateToQueue(source: Any, val data: GanttItemWrapper) : ApplicationEvent(source)

    class ModifyPredecessors(source: GanttItemWrapper, val predecessors: List<TaskPredecessor>) : ApplicationEvent(source)
}