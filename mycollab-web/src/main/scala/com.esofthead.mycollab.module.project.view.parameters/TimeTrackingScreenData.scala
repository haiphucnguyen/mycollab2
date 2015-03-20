package com.esofthead.mycollab.module.project.view.parameters

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.esofthead.mycollab.vaadin.mvp.ScreenData

/**
 * @author MyCollab Ltd.
 * @since 5.0.3
 */
object TimeTrackingScreenData {

  class Search(param: ItemTimeLoggingSearchCriteria) extends ScreenData[ItemTimeLoggingSearchCriteria](param) {}

}
