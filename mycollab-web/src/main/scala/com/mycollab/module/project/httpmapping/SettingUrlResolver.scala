package com.mycollab.module.project.httpmapping

import com.mycollab.common.UrlTokenizer
import com.mycollab.eventmanager.EventBusFactory
import com.mycollab.module.project.event.ProjectEvent
import com.mycollab.module.project.view.parameters.{ProjectScreenData, ProjectSettingScreenData}
import com.mycollab.vaadin.mvp.PageActionChain

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
class SettingUrlResolver extends ProjectUrlResolver {
  protected override def handlePage(params: String*) {
    val projectId = UrlTokenizer(params(0)).getInt
    val chain = new PageActionChain(new ProjectScreenData.Goto(projectId), new ProjectSettingScreenData.ViewSettings)
    EventBusFactory.getInstance().post(new ProjectEvent.GotoMyProject(this, chain))
  }
}
