package com.esofthead.mycollab.module.project.view.parameters

import java.util.Date

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object StandupScreenData {

  class Search(param: Date) extends ScreenData[Date](param) {}

  class Add(param: StandupReportWithBLOBs) extends ScreenData[StandupReportWithBLOBs](param) {}

  class Edit(param: StandupReportWithBLOBs) extends ScreenData[StandupReportWithBLOBs](param) {}

}
