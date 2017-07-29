package com.mycollab.mobile.module.project.events

import com.mycollab.events.ApplicationEvent

/**
  * @author MyColab Ltd
  * @since 5.4.3
  */
object TicketEvent {
  
  class GotoDashboard(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}
  
}
