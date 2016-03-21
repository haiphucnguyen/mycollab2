package com.esofthead.mycollab.module.project.view.reports

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.2.10
  */
class ReportUrlResolver extends ProjectUrlResolver {
  this.defaultUrlResolver = new DefaultUrlResolver
  this.addSubResolver("standup", new StandupUrlResolver)

  class DefaultUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new ProjectScreenData.GotoReportConsole())
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
}
