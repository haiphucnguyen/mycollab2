package com.mycollab.mobile.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.project.events.ProjectEvent
import com.mycollab.mobile.module.project.view.parameters.ProjectScreenData
import com.mycollab.mobile.module.project.view.parameters.TicketScreenData.GotoDashboard
import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
class TicketUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("dashboard", new ListUrlResolver)
  
  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val searchCriteria = new ProjectTicketSearchCriteria
      searchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new GotoDashboard(searchCriteria))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
}
