package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
 * @author MyCollab Ltd
 * @since 6.0.0
 */
object CustomizeUIEvent {
    class UpdateFeaturesList(source: Any) : ApplicationEvent(source)
}