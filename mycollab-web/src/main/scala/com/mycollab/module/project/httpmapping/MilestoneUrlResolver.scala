package com.mycollab.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.db.arguments.SetSearchField
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.domain.SimpleMilestone
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria
import com.mycollab.module.project.event.ProjectEvent
import com.mycollab.module.project.service.MilestoneService
import com.mycollab.module.project.view.parameters.{MilestoneScreenData, ProjectScreenData}
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.MyCollabUI
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class MilestoneUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("kanban", new KanbanUrlResolver)
  
  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val milestoneSearchCriteria = new MilestoneSearchCriteria
      milestoneSearchCriteria.setProjectIds(new SetSearchField[Integer](projectId))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MilestoneScreenData.Search(milestoneSearchCriteria))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class KanbanUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new MilestoneScreenData.Kanban())
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new MilestoneScreenData.Add(new SimpleMilestone))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val milestoneId = token.getInt
      val milestoneService = AppContextUtil.getSpringBean(classOf[MilestoneService])
      val milestone = milestoneService.findById(milestoneId, MyCollabUI.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new MilestoneScreenData.Edit(milestone))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val milestoneId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new MilestoneScreenData.Read(milestoneId))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}
