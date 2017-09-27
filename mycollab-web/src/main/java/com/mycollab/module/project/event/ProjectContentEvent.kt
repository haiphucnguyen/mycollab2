package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
class ProjectContentEvent {
    class GotoDashboard(source: Any) : ApplicationEvent(source, null)
}