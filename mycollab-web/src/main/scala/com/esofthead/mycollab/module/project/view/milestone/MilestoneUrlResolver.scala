package com.esofthead.mycollab.module.project.view.milestone

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.arguments.SetSearchField
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.domain.SimpleMilestone
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.service.MilestoneService
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{MilestoneScreenData, ProjectScreenData}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class MilestoneUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)

  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val milestoneSearchCriteria = new MilestoneSearchCriteria
      milestoneSearchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MilestoneScreenData.Search(milestoneSearchCriteria))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MilestoneScreenData.Add(new SimpleMilestone))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val milestoneId = token.getInt
      val milestoneService = ApplicationContextUtil.getSpringBean(classOf[MilestoneService])
      val milestone = milestoneService.findById(milestoneId, AppContext.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new MilestoneScreenData.Edit(milestone))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = new UrlTokenizer(params(0))
      val projectId = token.getInt
      val milestoneId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new MilestoneScreenData.Read(milestoneId))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
