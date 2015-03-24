package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object CustomizeUIEvent {

  class UpdateFeaturesList(source: AnyRef) extends ApplicationEvent(source, null) {}
}
