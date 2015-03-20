package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.StandupReportWithBLOBs
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object StandupScreenData {

  class Search(param: StandupReportSearchCriteria) extends ScreenData[StandupReportSearchCriteria](param) {}

  class Add(param: StandupReportWithBLOBs) extends ScreenData[StandupReportWithBLOBs](param) {}

  class Edit(param: StandupReportWithBLOBs) extends ScreenData[StandupReportWithBLOBs](param) {}

  class Read(param: Integer) extends ScreenData[Integer](param) {}

}
