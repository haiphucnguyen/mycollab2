package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object ProjectContentEvent {
    class GotoDashboard(source: Any) : ApplicationEvent(source, null)
}