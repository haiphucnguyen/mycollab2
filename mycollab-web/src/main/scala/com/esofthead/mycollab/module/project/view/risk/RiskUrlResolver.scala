package com.esofthead.mycollab.module.project.view.risk

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.NumberSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.SimpleRisk
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.service.RiskService
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, RiskScreenData}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class RiskUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId: Integer = new UrlTokenizer(params(0)).getInt
      val riskSearchCriteria: RiskSearchCriteria = new RiskSearchCriteria
      riskSearchCriteria.setProjectId(new NumberSearchField(projectId))
      val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new RiskScreenData.Search(riskSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token: UrlTokenizer = new UrlTokenizer(params(0))
      val projectId: Integer = token.getInt
      val riskId: Integer = token.getInt
      val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new RiskScreenData.Read(riskId))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId: Integer = new UrlTokenizer(params(0)).getInt
      val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new RiskScreenData.Add(new SimpleRisk))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token: UrlTokenizer = new UrlTokenizer(params(0))
      val projectId: Integer = token.getInt
      val riskId: Integer = token.getInt
      val riskService: RiskService = ApplicationContextUtil.getSpringBean(classOf[RiskService])
      val risk: SimpleRisk = riskService.findById(riskId, AppContext.getAccountId)
      val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new RiskScreenData.Edit(risk))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
