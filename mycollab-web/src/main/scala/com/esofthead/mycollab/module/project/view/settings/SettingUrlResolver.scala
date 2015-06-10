package com.esofthead.mycollab.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectSettingScreenData, ProjectScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
 * @author MyCollab Ltd
 * @since 5.0.9
 */
class SettingUrlResolver extends ProjectUrlResolver {
    protected override def handlePage(params: String*) {
        val projectId: Int = new UrlTokenizer(params(0)).getInt
        val chain: PageActionChain = new PageActionChain(new ProjectScreenData.Goto(projectId),
            new ProjectSettingScreenData.ViewSettings)
        EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
    }
}
