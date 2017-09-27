package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectNotificationEvent {
    class GotoList(source: Any, data: Any?) : ApplicationEvent(source, data)
}