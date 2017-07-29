package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.Milestone
import com.mycollab.module.project.domain.criteria.MilestoneSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
  * @author MyCollab Ltd.
  * @since 5.0.3
  */
object MilestoneScreenData {
  
  class Read(params: Integer) extends ScreenData[Integer](params) {}
  
  class Edit(params: Milestone) extends ScreenData[Milestone](params) {}
  
  class Add(params: Milestone) extends ScreenData[Milestone](params) {}
  
  class Search(params: MilestoneSearchCriteria) extends ScreenData[MilestoneSearchCriteria](params) {}
  
  class Roadmap() extends ScreenData() {}
  
  class Kanban() extends ScreenData() {}
  
}
