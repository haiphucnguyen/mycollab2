package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectNotificationEvent {
    class GotoList(source: Any, val data: Any?) : ApplicationEvent(source)
}