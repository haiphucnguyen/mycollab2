package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.Risk
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.7
  */
object RiskScreenData {

  class Read(param: Integer) extends ScreenData(param) {}

  class Add(param: Risk) extends ScreenData(param) {}

  class Edit(param: Risk) extends ScreenData(param) {}

}
