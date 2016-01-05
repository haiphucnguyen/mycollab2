package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object BugScreenData {

    class Search(param: BugSearchCriteria) extends ScreenData(param) {}

    class Read(param: Integer) extends ScreenData(param) {}

    class Add(param: BugWithBLOBs) extends ScreenData(param) {}

    class Edit(param: BugWithBLOBs) extends ScreenData(param) {}

}
