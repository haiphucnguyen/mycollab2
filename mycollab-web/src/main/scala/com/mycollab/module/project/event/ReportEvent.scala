package com.mycollab.module.project.event

import com.mycollab.events.ApplicationEvent

/**
  * @author MyCollab Ltd
  * @since 5.3.0
  */
object ReportEvent {
  class GotoConsole(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoTimesheetReport(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoWeeklyTimingReport(source: AnyRef) extends ApplicationEvent(source, null) {}

  class GotoUserWorkloadReport(source: AnyRef) extends ApplicationEvent(source, null) {}
}
