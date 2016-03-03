package com.esofthead.mycollab.module.project.view.settings

import com.esofthead.mycollab.common.UrlTokenizer
import com.esofthead.mycollab.eventmanager.EventBusFactory
import com.esofthead.mycollab.module.project.events.ProjectEvent
import com.esofthead.mycollab.module.project.view.ProjectUrlResolver
import com.esofthead.mycollab.module.project.view.parameters.{ProjectScreenData, ProjectSettingScreenData}
import com.esofthead.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class SettingUrlResolver extends ProjectUrlResolver {
  protected override def handlePage(params: String*) {
    val projectId = new UrlTokenizer(params(0)).getInt
    val chain = new PageActionChain(new ProjectScreenData.Goto(projectId),
      new ProjectSettingScreenData.ViewSettings)
    EventBusFactory.getInstance.post(new ProjectEvent.GotoMyProject(this, chain))
  }
}
