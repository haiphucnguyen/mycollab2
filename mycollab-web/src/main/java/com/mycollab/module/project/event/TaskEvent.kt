package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object TaskEvent {
    class SearchRequest(source: Any, data: TaskSearchCriteria) : ApplicationEvent(source)

    class NewTaskAdded(source: Any, data: Int) : ApplicationEvent(source)

    class TaskDeleted(source: Any, data: Int) : ApplicationEvent(source)

    class RemoveParentRelationship(source: Any, data: Int) : ApplicationEvent(source)

    class Search(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoAdd(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoEdit(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoRead(source: Any, val data: Any?) : ApplicationEvent(source)

    class GotoKanbanView(source: Any, val data: Any?) : ApplicationEvent(source)
}