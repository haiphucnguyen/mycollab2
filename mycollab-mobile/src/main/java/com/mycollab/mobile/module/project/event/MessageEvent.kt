package com.mycollab.mobile.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object MessageEvent {
    class GotoAdd(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)

    class GotoRead(source: Any, data: Any?) : ApplicationEvent(source, data)
}