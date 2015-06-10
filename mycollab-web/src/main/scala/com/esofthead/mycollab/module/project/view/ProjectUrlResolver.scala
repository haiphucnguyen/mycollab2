package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.{FollowingTicketEvent, ProjectEvent, TimeTrackingEvent}
import com.esofthead.mycollab.module.project.service.ProjectService
import com.esofthead.mycollab.module.project.view.bug.BugUrlResolver
import com.esofthead.mycollab.module.project.view.file.ProjectFileUrlResolver
import com.esofthead.mycollab.module.project.view.message.MessageUrlResolver
import com.esofthead.mycollab.module.project.view.milestone.MilestoneUrlResolver
import com.esofthead.mycollab.module.project.view.page.PageUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData
import com.esofthead.mycollab.module.project.view.problem.ProblemUrlResolver
import com.esofthead.mycollab.module.project.view.risk.RiskUrlResolver
import com.esofthead.mycollab.module.project.view.settings.{RoleUrlResolver, SettingUrlResolver, UserUrlResolver}
import com.esofthead.mycollab.module.project.view.standup.StandupUrlResolver
import com.esofthead.mycollab.module.project.view.task.ScheduleUrlResolver
import com.esofthead.mycollab.module.project.view.time.TimeUrlResolver
import com.esofthead.mycollab.shell.events.ShellEvent
import com.esofthead.mycollab.spring.ApplicationContextUtil
import com.esofthead.mycollab.vaadin.AppContext
import com.esofthead.mycollab.vaadin.desktop.ui.ModuleHelper
import com.esofthead.mycollab.vaadin.mvp.{PageActionChain, UrlResolver}

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ProjectUrlResolver extends UrlResolver {
    def build: UrlResolver = {
        this.addSubResolver("dashboard", new ProjectPageUrlResolver)
        this.addSubResolver("tag", new ProjectTagUrlResolver)
        this.addSubResolver("message", new MessageUrlResolver)
        this.addSubResolver("milestone", new MilestoneUrlResolver)
        this.addSubResolver("task", new ScheduleUrlResolver)
        this.addSubResolver("bug", new BugUrlResolver)
        this.addSubResolver("page", new PageUrlResolver)
        this.addSubResolver("risk", new RiskUrlResolver)
        this.addSubResolver("problem", new ProblemUrlResolver)
        this.addSubResolver("standup", new StandupUrlResolver)
        this.addSubResolver("user", new UserUrlResolver)
        this.addSubResolver("role", new RoleUrlResolver)
        this.addSubResolver("setting", new SettingUrlResolver)
        this.addSubResolver("time", new TimeUrlResolver)
        this.addSubResolver("file", new ProjectFileUrlResolver)
        this.addSubResolver("following", new FollowingTicketsResolver)
        this.addSubResolver("timetracking", new TimeTrackingResolver)
        return this
    }

    override def handle(params: String*) {
        if (!ModuleHelper.isCurrentProjectModule) {
            EventBusFactory.getInstance.post(new ShellEvent.GotoProjectModule(this, params))
        }
        else {
            super.handle(params:_*)
        }
    }

    protected def defaultPageErrorHandler {
        EventBusFactory.getInstance.post(new ShellEvent.GotoProjectModule(this, null))
    }

    class ProjectTagUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Int = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new ProjectScreenData.GotoTagList(null))
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    class ProjectPageUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            if (params.length == 0) {
                EventBusFactory.getInstance.post(new ShellEvent.GotoProjectModule(this, null))
            } else {
                val projectId: Int = new UrlTokenizer(params(0)).getInt
                val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId))
                EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
            }
        }
    }

    private class FollowingTicketsResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val prjService: ProjectService = ApplicationContextUtil.getSpringBean(classOf[ProjectService])
            val prjKeys: java.util.List[Integer] = prjService.getProjectKeysUserInvolved(AppContext.getUsername, AppContext.getAccountId)
            EventBusFactory.getInstance.post(new FollowingTicketEvent.GotoMyFollowingItems(this, prjKeys))
        }
    }

    private class TimeTrackingResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val prjService: ProjectService = ApplicationContextUtil.getSpringBean(classOf[ProjectService])
            val prjKeys: java.util.List[Integer] = prjService.getProjectKeysUserInvolved(AppContext.getUsername, AppContext.getAccountId)
            EventBusFactory.getInstance.post(new TimeTrackingEvent.GotoTimeTrackingView(this, prjKeys))
        }
    }

}
