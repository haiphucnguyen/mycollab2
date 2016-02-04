package com.esofthead.mycollab.pro.module.project.view.time;

import com.esofthead.mycollab.module.project.domain.criteria.ItemTimeLoggingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 2.0
 */
public interface TimeTrackingListView extends PageView {
    void setSearchCriteria(ItemTimeLoggingSearchCriteria searchCriteria);
}
