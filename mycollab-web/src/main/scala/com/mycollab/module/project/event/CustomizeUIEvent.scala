package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object CustomizeUIEvent {

  class UpdateFeaturesList(source: AnyRef) extends ApplicationEvent(source, null) {}

}
