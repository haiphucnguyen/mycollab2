package com.mycollab.module.project.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.event.ReportEvent

/**
  * @author MyCollab Ltd
  * @since 5.3.0
  */
class TimesheetUrlResolver extends ProjectUrlResolver {

  override protected def handlePage(params: String*): Unit = {
    EventBusFactory.getInstance().post(new ReportEvent.GotoTimesheetReport(this))
  }
}
