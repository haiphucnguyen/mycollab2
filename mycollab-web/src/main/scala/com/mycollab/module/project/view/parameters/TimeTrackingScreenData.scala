package com.mycollab.module.project.view.parameters

import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object TimeTrackingScreenData {

  class Search(param: ItemTimeLoggingSearchCriteria) extends ScreenData[ItemTimeLoggingSearchCriteria](param) {}

}
