package com.mycollab.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.core.{MyCollabException, ResourceNotFoundException}
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.ProjectLinkParams
import com.mycollab.module.project.event.ProjectEvent
import com.mycollab.module.project.view.parameters.{BugScreenData, ProjectScreenData}
import com.mycollab.module.tracker.domain.SimpleBug
import com.mycollab.module.tracker.service.BugService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.MyCollabUI
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class BugUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("preview", new PreviewUrlResolver)
  
  private class PreviewUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var projectId: Integer = 0
      var bugId: Integer = 0
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        val bugService = AppContextUtil.getSpringBean(classOf[BugService])
        val bug = bugService.findByProjectAndBugKey(itemKey, prjShortName, MyCollabUI.getAccountId)
        if (bug != null) {
          projectId = bug.getProjectid
          bugId = bug.getId
        }
        else {
          throw new ResourceNotFoundException("Can not get bug with bugkey %d and project short name %s".format(itemKey, prjShortName))
        }
      }
      else {
        throw new MyCollabException("Invalid bug link " + params(0))
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new BugScreenData.Read(bugId))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var bug: SimpleBug = null
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        val bugService = AppContextUtil.getSpringBean(classOf[BugService])
        bug = bugService.findByProjectAndBugKey(itemKey, prjShortName, MyCollabUI.getAccountId)
      }
      else {
        throw new MyCollabException("Invalid bug link: " + params(0))
      }
      if (bug == null) {
        throw new ResourceNotFoundException
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(bug.getProjectid), new BugScreenData.Edit(bug))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new BugScreenData.Add(new SimpleBug))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}
