package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.0.3
  */
object TimeTrackingEvent {

  class TimeLoggingEntryChange(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoList(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
