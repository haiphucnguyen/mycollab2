package com.esofthead.mycollab.module.project.view.bug

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.core.{MyCollabException, ResourceNotFoundException}
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.ProjectLinkParams
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{BugScreenData, ProjectScreenData}
import com.esofthead.mycollab.module.tracker.domain.SimpleBug
import com.esofthead.mycollab.module.tracker.service.BugService
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class BugUrlResolver extends ProjectUrlResolver {
    this.defaultUrlResolver = new DefaultUrlResolver
    this.addSubResolver("add", new AddUrlResolver)
    this.addSubResolver("edit", new EditUrlResolver)
    this.addSubResolver("preview", new PreviewUrlResolver)
    this.addSubResolver("component", new ComponentUrlResolver)
    this.addSubResolver("version", new VersionUrlResolver)

    private class DefaultUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new BugScreenData.GotoDashboard)
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class PreviewUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            var projectId: Integer = 0
            var bugId: Integer = 0
            if (ProjectLinkParams.isValidParam(params(0))) {
                val prjShortName: String = ProjectLinkParams.getProjectShortName(params(0))
                val itemKey: Integer = ProjectLinkParams.getItemKey(params(0))
                val bugService: BugService = ApplicationContextUtil.getSpringBean(classOf[BugService])
                val bug: SimpleBug = bugService.findByProjectAndBugKey(itemKey, prjShortName, AppContext.getAccountId)
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
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new BugScreenData.Read(bugId))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class EditUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            var bug: SimpleBug = null
            if (ProjectLinkParams.isValidParam(params(0))) {
                val prjShortName: String = ProjectLinkParams.getProjectShortName(params(0))
                val itemKey: Integer = ProjectLinkParams.getItemKey(params(0))
                val bugService: BugService = ApplicationContextUtil.getSpringBean(classOf[BugService])
                bug = bugService.findByProjectAndBugKey(itemKey, prjShortName, AppContext.getAccountId)
            }
            else {
                throw new MyCollabException("Invalid bug link: " + params(0))
            }
            if (bug == null) {
                throw new ResourceNotFoundException
            }
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(bug.getProjectid),
                new BugScreenData.Edit(bug))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class AddUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new BugScreenData.Add(new SimpleBug))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }
}
