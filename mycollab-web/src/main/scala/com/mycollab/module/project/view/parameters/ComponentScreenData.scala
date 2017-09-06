package com.mycollab.module.project.view.parameters

import com.mycollab.module.tracker.domain.Component
import com.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object ComponentScreenData {

  class Read(params:Integer) extends ScreenData[Integer](params){}

  class Add(params:Component) extends ScreenData[Component](params) {}

  class Edit(params:Component) extends ScreenData[Component](params) {}

  class Search(params:ComponentSearchCriteria) extends ScreenData[ComponentSearchCriteria](params) {}
}
