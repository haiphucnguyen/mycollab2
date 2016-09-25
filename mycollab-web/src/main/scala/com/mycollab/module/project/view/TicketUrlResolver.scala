package com.mycollab.module.project.view

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.events.ProjectEvent
import com.mycollab.module.project.view.parameters.{ProjectScreenData, TicketScreenData}
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
class TicketUrlResolver extends ProjectUrlResolver {
  this.defaultUrlResolver = new DashboardUrlResolver
  
  private class DashboardUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val tokenizer = UrlTokenizer(params(0))
      val projectId = tokenizer.getInt
      val query = tokenizer.getQuery
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TicketScreenData.GotoDashboard(query))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}
