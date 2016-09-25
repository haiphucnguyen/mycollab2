package com.mycollab.module.project.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
object TicketEvent {
  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
}
