package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class BugComponentEvent {
    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoEdit(source: Any, data: Any?) : ApplicationEvent(source, data)
}