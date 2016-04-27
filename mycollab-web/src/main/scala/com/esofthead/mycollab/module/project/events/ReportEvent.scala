package com.esofthead.mycollab.module.project.events

import com.esofthead.mycollab.eventmanager.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.3.0
  */
object ReportEvent {
  class GotoConsole(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoTimesheetReport(source: AnyRef) extends ApplicationEvent(source, null) {}
}
