package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.Risk
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
object RiskScreenData {

  class Search(param: RiskSearchCriteria) extends ScreenData(param) {}

  class Read(param: Integer) extends ScreenData(param) {}

  class Add(param: Risk) extends ScreenData(param) {}

  class Edit(param: Risk) extends ScreenData(param) {}

}
