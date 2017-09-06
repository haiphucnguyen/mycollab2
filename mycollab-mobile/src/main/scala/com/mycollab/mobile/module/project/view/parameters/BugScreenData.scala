package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.tracker.domain.BugWithBLOBs
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object BugScreenData {

  class Read(param: Integer) extends ScreenData(param) {}

  class Add(param: BugWithBLOBs) extends ScreenData(param) {}

  class Edit(param: BugWithBLOBs) extends ScreenData(param) {}

}
