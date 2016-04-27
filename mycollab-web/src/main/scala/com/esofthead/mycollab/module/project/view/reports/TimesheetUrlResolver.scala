package com.esofthead.mycollab.module.project.view.reports

import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ReportEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver

/**
  * @author MyCollab Ltd
  * @since 5.3.0
  */
class TimesheetUrlResolver extends ProjectUrlResolver {

  override protected def handlePage(params: String*): Unit = {
    EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this))
  }
}
