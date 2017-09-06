package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.criteria.ProjectTicketSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.4.3
  */
object TicketScreenData {
  class GotoDashboard(param: ProjectTicketSearchCriteria) extends ScreenData[Any](param) {}
}
