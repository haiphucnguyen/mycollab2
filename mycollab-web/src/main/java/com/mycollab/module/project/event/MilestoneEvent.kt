package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class MilestoneEvent {
    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRoadmap(source: Any) : ApplicationEvent(source, null) 

    class GotoKanban(source: Any) : ApplicationEvent(source, null) 

    class NewMilestoneAdded(source: Any, data: Int) : ApplicationEvent(source, data) 

    class MilestoneDeleted(source: Any, data: Int) : ApplicationEvent(source, data) 
}