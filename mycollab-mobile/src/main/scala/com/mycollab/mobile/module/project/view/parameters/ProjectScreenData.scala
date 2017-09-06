package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.SimpleProject
import com.mycollab.vaadin.mvp.ScreenData
import com.mycollab.common.domain.criteria.ActivityStreamSearchCriteria

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
