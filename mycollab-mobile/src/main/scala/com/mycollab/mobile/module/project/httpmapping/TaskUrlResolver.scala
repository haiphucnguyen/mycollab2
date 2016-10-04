package com.mycollab.mobile.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.core.{MyCollabException, ResourceNotFoundException}
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.mobile.module.project.events.ProjectEvent
import com.mycollab.mobile.module.project.view.parameters.{ProjectScreenData, TaskScreenData}
import com.mycollab.module.project.ProjectLinkParams
import com.mycollab.module.project.domain.SimpleTask
import com.mycollab.module.project.service.ProjectTaskService
import com.mycollab.spring.AppContextUtil
import com.mycollab.vaadin.MyCollabUI
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class TaskUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("preview", new ReadUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  
  private class ReadUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var projectId = 0
      var taskId = 0
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        val taskService = AppContextUtil.getSpringBean(classOf[ProjectTaskService])
        val task = taskService.findByProjectAndTaskKey(itemKey, prjShortName, MyCollabUI.getAccountId)
        if (task != null) {
          projectId = task.getProjectid
          taskId = task.getId
        }
        else {
          throw new ResourceNotFoundException("Can not find task with itemKey " + itemKey + " and project " + prjShortName)
        }
      }
      else {
        val tokenizer = UrlTokenizer(params(0))
        projectId = tokenizer.getInt
        taskId = tokenizer.getInt
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskScreenData.Read(taskId))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var task: SimpleTask = null
      val taskService = AppContextUtil.getSpringBean(classOf[ProjectTaskService])
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        task = taskService.findByProjectAndTaskKey(itemKey, prjShortName, MyCollabUI.getAccountId)
      }
      else {
        throw new MyCollabException("Can not find task link " + params(0))
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(task.getProjectid), new TaskScreenData.Edit(task))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val token = UrlTokenizer(params(0))
      val projectId = token.getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskScreenData.Add(new SimpleTask))
      EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }
  
}
