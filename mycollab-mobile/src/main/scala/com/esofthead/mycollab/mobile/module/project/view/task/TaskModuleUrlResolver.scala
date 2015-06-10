package com.esofthead.mycollab.mobile.module.project.view.task

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.mobile.module.project.ProjectUrlResolver
import com.esofthead.mycollab.mobile.module.project.events.ProjectEvent
import com.esofthead.mycollab.mobile.module.project.view.parameters.{TaskGroupScreenData, ProjectScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class TaskModuleUrlResolver extends ProjectUrlResolver {
    addSubResolver("taskgroup", new TaskGroupUrlResolver)
    addSubResolver("task", new TaskUrlResolver)

    protected override def handlePage(params: String*) {
        val projectId: Int = new UrlTokenizer(params(0)).getInt
        val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId), new TaskGroupScreenData.List)
        EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
}
