package com.esofthead.mycollab.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.NumberSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ComponentScreenData, ProjectScreenData}
import com.esofthead.mycollab.module.tracker.domain.Component
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria
import com.esofthead.mycollab.module.tracker.service.ComponentService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ComponentUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val componentSearchCriteria = new ComponentSearchCriteria
      componentSearchCriteria.setProjectid(new NumberSearchField(projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new ComponentScreenData.Search(componentSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new ComponentScreenData.Add(new Component))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val componentId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new ComponentScreenData.Read(componentId))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val componentId = token.getInt
      val componentService = ApplicationContextUtil.getSpringBean(classOf[ComponentService])
      val component = componentService.findById(componentId, AppContext.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ComponentScreenData.Edit(component))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
