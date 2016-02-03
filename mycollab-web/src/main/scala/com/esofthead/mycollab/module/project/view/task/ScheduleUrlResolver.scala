package com.esofthead.mycollab.module.project.view.task

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.{MyCollabException, ResourceNotFoundException}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.ProjectLinkParams
import com.esofthead.mycollab.module.project.domain.SimpleTask
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.service.ProjectTaskService
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData.GotoDashboard
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, TaskScreenData}
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class ScheduleUrlResolver extends ProjectUrlResolver {
  this.addSubResolver("dashboard", new DashboardUrlResolver)
  this.addSubResolver("preview", new ReadUrlResolver)
  this.addSubResolver("add", new AddUrlResolver)
  this.addSubResolver("edit", new EditUrlResolver)
  this.addSubResolver("kanban", new KanbanUrlResolver)
  this.defaultUrlResolver = new DashboardUrlResolver

  private class DashboardUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new GotoDashboard)
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class KanbanUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*): Unit = {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new TaskScreenData.GotoKanbanView)
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class ReadUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var projectId: Integer = 0
      var taskId: Integer = 0
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        val taskService = ApplicationContextUtil.getSpringBean(classOf[ProjectTaskService])
        val task = taskService.findByProjectAndTaskKey(itemKey, prjShortName, AppContext.getAccountId)
        if (task != null) {
          projectId = task.getProjectid
          taskId = task.getId
          val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
            new TaskScreenData.Read(taskId))
          EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
        else {
          throw new ResourceNotFoundException(String.format("Can not find task with itemKey %d and project %s", itemKey, prjShortName))
        }
      } else {
        throw new MyCollabException("Invalid url " + params(0));
      }
    }
  }

  private class EditUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      var task: SimpleTask = null
      val taskService = ApplicationContextUtil.getSpringBean(classOf[ProjectTaskService])
      if (ProjectLinkParams.isValidParam(params(0))) {
        val prjShortName = ProjectLinkParams.getProjectShortName(params(0))
        val itemKey = ProjectLinkParams.getItemKey(params(0))
        task = taskService.findByProjectAndTaskKey(itemKey, prjShortName, AppContext.getAccountId)
      }
      else {
        throw new MyCollabException("Can not find task link " + params(0))
      }
      val chain = new PageActionChain(new ProjectScreenData.Goto(task.getProjectid),
        new TaskScreenData.Edit(task))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

  private class AddUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
      val projectId = new UrlTokenizer(params(0)).getInt
      val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
        new TaskScreenData.Add(new SimpleTask))
      EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
  }

}
