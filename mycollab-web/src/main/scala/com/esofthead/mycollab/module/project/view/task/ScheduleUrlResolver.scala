package com.esofthead.mycollab.module.project.view.task

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, TaskGroupScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class ScheduleUrlResolver extends ProjectUrlResolver {
    this.addSubResolver("dashboard", new DashboardUrlResolver)
    this.addSubResolver("task", new TaskUrlResolver)
    this.addSubResolver("taskgroup", new TaskGroupUrlResolver)
    this.defaultUrlResolver = new FilterUrlResolver

    private class DashboardUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new TaskGroupScreenData.GotoDashboard)
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

    private class FilterUrlResolver extends ProjectUrlResolver {
        protected override def handlePage(params: String*) {
            val projectId: Integer = new UrlTokenizer(params(0)).getInt
            val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
                new TaskGroupScreenData.GotoDashboard)
            EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
        }
    }

}
