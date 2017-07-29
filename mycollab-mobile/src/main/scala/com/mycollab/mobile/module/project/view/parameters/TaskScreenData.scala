package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.SimpleTask
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object TaskScreenData {

  class Read(param: Integer) extends ScreenData(param) {}

  class Edit(param: SimpleTask) extends ScreenData(param) {}

  class Add(param: SimpleTask) extends ScreenData(param) {}

}
