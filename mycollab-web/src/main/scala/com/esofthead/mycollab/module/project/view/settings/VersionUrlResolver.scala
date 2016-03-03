package com.esofthead.mycollab.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.NumberSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, VersionScreenData}
import com.esofthead.mycollab.module.tracker.domain.Version
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria
import com.esofthead.mycollab.module.tracker.service.VersionService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class VersionUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val versionSearchCriteria = new VersionSearchCriteria
      versionSearchCriteria.setProjectId(new NumberSearchField(projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new VersionScreenData.Search(versionSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val versionId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new VersionScreenData.Read(versionId))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val versionId = token.getInt
      val versionService = ApplicationContextUtil.getSpringBean(classOf[VersionService])
      val version = versionService.findById(versionId, AppContext.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new VersionScreenData.Edit(version))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new VersionScreenData.Add(new Version))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
