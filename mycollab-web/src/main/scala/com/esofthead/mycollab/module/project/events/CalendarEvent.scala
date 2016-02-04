package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.1
  */
object CalendarEvent {

  class GotoCalendarView(source: AnyRef, data: AnyRef) extends ApplicationEvent(source, data) {}

}
