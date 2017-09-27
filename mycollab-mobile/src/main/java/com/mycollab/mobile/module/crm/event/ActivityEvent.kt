package com.mycollab.mobile.module.crm.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ActivityEvent {
    class TaskAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class TaskEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class TaskRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class MeetingAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class MeetingEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class MeetingRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class CallAdd(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class CallEdit(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class CallRead(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data) 

    class GotoRelatedItems(source: Any, data: Any?) : ApplicationEvent(source, data) 
}