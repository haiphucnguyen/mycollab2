package com.mycollab.pro.module.project.view.time

import com.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria
import com.mycollab.vaadin.mvp.PageView

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
interface TimeTrackingListView : PageView {
    fun setSearchCriteria(searchCriteria: ItemTimeLoggingSearchCriteria)
}
