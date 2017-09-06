package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Risk
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object RiskScreenData {

  class Read(params: Integer) extends ScreenData[Integer](params) {}

  class Add(param: Risk) extends ScreenData[Risk](param) {}

  class Edit(param: Risk) extends ScreenData[Risk](param) {}

}
