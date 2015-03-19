package com.esofthead.mycollab.module.project.view

import com.esofthead.mycollab.core.MyCollabException
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardContainer
import com.esofthead.mycollab.vaadin.mvp.ScreenData
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter
import com.vaadin.ui.ComponentContainer

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
class ProjectSearchItemsPresenter( ) extends AbstractPresenter[ProjectSearchItemsView](classOf[ProjectSearchItemsView]) {
  override protected def onGo(container: ComponentContainer, data: ScreenData[_]): Unit = {
    val projectViewContainer: ProjectDashboardContainer = container.asInstanceOf[ProjectDashboardContainer]
    projectViewContainer.removeAllComponents
    projectViewContainer.addComponent(view.getWidget)
    val value = data.getParams.asInstanceOf[String]
    if (value != null) {
      view.displayResults(value)
    } else {
      throw new MyCollabException("Do not support param type " + value)
    }
  }
}
