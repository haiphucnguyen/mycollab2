package com.esofthead.mycollab.module.project.view.message

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.SetSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.criteria.MessageSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{MessageScreenData, ProjectScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class MessageUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val messageSearchCriteria = new MessageSearchCriteria
      messageSearchCriteria.setProjectids(new SetSearchField[Integer](projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MessageScreenData.Search(messageSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val messageId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MessageScreenData.Read(messageId))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
