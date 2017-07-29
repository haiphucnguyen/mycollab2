package com.mycollab.module.project.httpmapping

import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.event.ReportEvent

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
class ReportUrlResolver extends ProjectUrlResolver {
  this.defaultUrlResolver = new DefaultUrlResolver
  this.addSubResolver("standup", new StandupUrlResolver)
  this.addSubResolver("timesheet", new TimesheetUrlResolver)
  this.addSubResolver("weeklytiming", new WeeklyTimingUrlResolver)
  this.addSubResolver("usersworkload", new UsersWorkloadUrlResolver)

  class DefaultUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this))
    }
  }

  /**
    * @param params
    */
  override protected def handlePage(params: String*): Unit = {
    EventBusFactory.getInstance().post(new ReportEvent.GotoConsole(this))
  }

  class WeeklyTimingUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*): Unit = {
      EventBusFactory.getInstance().post(new ReportEvent.GotoWeeklyTimingReport(this))
    }
  }

  class UsersWorkloadUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*): Unit = {
      EventBusFactory.getInstance().post(new ReportEvent.GotoUserWorkloadReport(this))
    }
  }

}
