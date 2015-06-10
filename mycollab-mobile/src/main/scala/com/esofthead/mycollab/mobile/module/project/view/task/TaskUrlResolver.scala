package com.esofthead.mycollab.mobile.module.project.view.task

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.{MyCollabException, ResourceNotFoundException}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent
import com.esofthead.mycollab.mobile.module.project.view.parameters.{TaskScreenData, ProjectScreenData}
import com.esofthead.mycollab.module.project.ProjectLinkParams
import com.esofthead.mycollab.module.project.domain.SimpleTask
import com.esofthead.mycollab.module.project.service.ProjectTaskService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class TaskUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("list", new ListUrlResolver)
    this.addSubResolver("preview", new ReadUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("add", new AddUrlResolver)

    private class ListUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Int = token.getInt
            val listId: Int = token.getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskScreenData.List(listId))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class ReadUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            var projectId: Int = 0
            var taskId: Int = 0
            if (ProjectLinkParams.isValidParam(params(0))) {
                val prjShortName: String = ProjectLinkParams.getProjectShortName(params(0))
                val itemKey: Int = ProjectLinkParams.getItemKey(params(0))
                val taskService: ProjectTaskService = ApplicationContextUtil.getSpringBean(classOf[ProjectTaskService])
                val task: SimpleTask = taskService.findByProjectAndTaskKey(itemKey, prjShortName, AppContext.getAccountId)
                if (task != null) {
                    projectId = task.getProjectid
                    taskId = task.getId
                }
                else {
                    throw new ResourceNotFoundException("Can not find task with itemKey " + itemKey + " and project " + prjShortName)
                }
            }
            else {
                val tokenizer: UrlTokenizer = new UrlTokenizer(params(0))
                projectId = tokenizer.getInt
                taskId = tokenizer.getInt
            }
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskScreenData.Read(taskId))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class EditUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            var task: SimpleTask = null
            val taskService: ProjectTaskService = ApplicationContextUtil.getSpringBean(classOf[ProjectTaskService])
            if (ProjectLinkParams.isValidParam(params(0))) {
                val prjShortName: String = ProjectLinkParams.getProjectShortName(params(0))
                val itemKey: Int = ProjectLinkParams.getItemKey(params(0))
                task = taskService.findByProjectAndTaskKey(itemKey, prjShortName, AppContext.getAccountId)
            }
            else {
                throw new MyCollabException("Can not find task link " + params(0))
            }
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(task.getProjectid), new TaskScreenData.Edit(task))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class AddUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val token: UrlTokenizer = new UrlTokenizer(params(0))
            val projectId: Int = token.getInt
            val taskListId: Int = token.getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskScreenData.Add(taskListId))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

}
