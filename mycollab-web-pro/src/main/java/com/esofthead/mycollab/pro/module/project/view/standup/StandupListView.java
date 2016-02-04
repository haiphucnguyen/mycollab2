package com.esofthead.mycollab.pro.module.project.view.standup;

import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public interface StandupListView extends PageView {
    void setSearchCriteria(StandupReportSearchCriteria searchCriteria);
}
