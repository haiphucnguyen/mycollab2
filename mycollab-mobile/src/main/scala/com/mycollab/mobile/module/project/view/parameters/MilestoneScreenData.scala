package com.mycollab.mobile.module.project.view.parameters

import com.mycollab.module.project.domain.SimpleMilestone
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd
  * @since 5.2.5
  */
object MilestoneScreenData {

  class Search(param: MilestoneSearchCriteria) extends ScreenData(param) {}

  class Read(param: Integer) extends ScreenData(param) {}

  class Edit(param: SimpleMilestone) extends ScreenData(param) {}

  class Add(param: SimpleMilestone) extends ScreenData(param) {}

}
