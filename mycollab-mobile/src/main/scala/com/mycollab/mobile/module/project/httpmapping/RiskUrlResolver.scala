package com.mycollab.mobile.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.project.events.ProjectEvent
import com.mycollab.mobile.module.project.view.parameters.{ProjectScreenData, RiskScreenData}
import com.mycollab.module.project.domain.SimpleRisk
import com.mycollab.module.project.service.RiskService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
class RiskUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val risk = new SimpleRisk
      risk.setProjectid(projectId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new RiskScreenData.Add(risk))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val riskId = token.getInt
      val riskService = AppContextUtil.getSpringBean(classOf[RiskService])
      val risk = riskService.findById(riskId, AppUI.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new RiskScreenData.Edit(risk))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val riskId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new RiskScreenData.Read(riskId))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
}
