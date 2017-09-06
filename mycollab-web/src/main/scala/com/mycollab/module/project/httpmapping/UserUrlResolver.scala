package com.mycollab.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.db.arguments.{NumberSearchField, SetSearchField}
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.ProjectMemberStatusConstants
import com.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria
import com.mycollab.module.project.event.ProjectEvent
import com.mycollab.module.project.service.ProjectMemberService
import com.mycollab.module.project.view.parameters.{ProjectMemberScreenData, ProjectScreenData}
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.AppUI
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class UserUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("list", new ListUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  
  private class ListUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val memberSearchCriteria = new ProjectMemberSearchCriteria
      memberSearchCriteria.setProjectId(new NumberSearchField(projectId))
      memberSearchCriteria.setStatuses(new SetSearchField[String](ProjectMemberStatusConstants.ACTIVE, ProjectMemberStatusConstants.NOT_ACCESS_YET))
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Search(memberSearchCriteria))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val memberName = token.getString
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Read(memberName))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new ProjectMemberScreenData.InviteProjectMembers)
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val memberId = token.getInt
      val projectMemberService = AppContextUtil.getSpringBean(classOf[ProjectMemberService])
      val member = projectMemberService.findById(memberId, AppUI.getAccountId)
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectMemberScreenData.Add(member))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}
