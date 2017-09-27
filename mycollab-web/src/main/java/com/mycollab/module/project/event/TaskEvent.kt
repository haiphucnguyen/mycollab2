package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent
import com.mycollab.module.project.domain.criteria.TaskSearchCriteria

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object TaskEvent {
    class SearchRequest(source: Any, data: TaskSearchCriteria) : ApplicationEvent(source, data) 

    class NewTaskAdded(source: Any, data: Int) : ApplicationEvent(source, data) 

    class TaskDeleted(source: Any, data: Int) : ApplicationEvent(source, data) 

    class RemoveParentRelationship(source: Any, data: Int) : ApplicationEvent(source, data) 

    class Search(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoKanbanView(source: Any, data: Any?) : ApplicationEvent(source, data) 
}