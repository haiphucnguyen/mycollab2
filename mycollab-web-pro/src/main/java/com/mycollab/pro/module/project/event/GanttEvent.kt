package com.mycollab.pro.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.TaskPredecessor
import com.mycollab.pro.module.project.view.assignments.gantt.GanttItemWrapper

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class GanttEvent {
    class ClearGanttItemsNeedUpdate(source: Any) : ApplicationEvent(source, null) 

    class UpdateGanttItem(source: Any, data: GanttItemWrapper) : ApplicationEvent(source, data) 

    class DeleteGanttItemUpdateToQueue(source: Any, data: GanttItemWrapper) : ApplicationEvent(source, data) 

    class AddGanttItemUpdateToQueue(source: Any, data: GanttItemWrapper) : ApplicationEvent(source, data) 

    class ModifyPredecessors(source: GanttItemWrapper, predecessors: List<TaskPredecessor>) : ApplicationEvent(source, predecessors)
}