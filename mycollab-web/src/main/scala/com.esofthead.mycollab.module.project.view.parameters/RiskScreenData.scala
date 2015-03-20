package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.Risk
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object RiskScreenData {

  class Read(params: Integer) extends ScreenData[Integer](params) {}

  class Add(param: Risk) extends ScreenData[Risk](param) {}

  class Edit(param: Risk) extends ScreenData[Risk](param) {}

  class Search(param: RiskSearchCriteria) extends ScreenData[RiskSearchCriteria](param) {}

}
