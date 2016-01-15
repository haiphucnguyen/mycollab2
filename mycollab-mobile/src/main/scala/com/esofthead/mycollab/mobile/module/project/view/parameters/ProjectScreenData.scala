package com.esofthead.mycollab.mobile.module.project.view.parameters

import com.esofthead.mycollab.common.domain.criteria.ActivityStreamSearchCriteria
import com.esofthead.mycollab.module.project.domain.SimpleProject
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.0.9
  */
object ProjectScreenData {

  class Goto(param: Integer) extends ScreenData[Integer](param) {}

  class Add(param: SimpleProject) extends ScreenData[SimpleProject](param) {}

  class Edit(param: Integer) extends ScreenData[Integer](param) {}

  class ProjectActivities(param: ActivityStreamSearchCriteria) extends ScreenData[ActivityStreamSearchCriteria](param) {}

  class GotoDashboard() extends ScreenData[AnyRef](null) {}

  class AllActivities(param: ActivityStreamSearchCriteria) extends ScreenData[ActivityStreamSearchCriteria](param) {}

}
