package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors
import com.esofthead.mycollab.module.project.view.task.gantt.GanttItemWrapper

/**
 * @author MyCollab Ltd
 * @since 5.1.3
 */
object GanttEvent {
    class ClearGanttItemsNeedUpdate(source: AnyRef) extends ApplicationEvent(source, null) {}

    class UpdateGanttItemDates(source: AnyRef, data: GanttItemWrapper) extends ApplicationEvent(source, data) {}

    class AddGanttItemUpdateToQueue(source: AnyRef, data: AssignWithPredecessors) extends ApplicationEvent(source, data) {}
}
