package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public interface IBugAssigneeChartWidget extends PageView {
    void displayChart(BugSearchCriteria searchCriteria);
}
