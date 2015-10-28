package com.esofthead.mycollab.module.project.view.bug;

import com.esofthead.mycollab.common.domain.criteria.TimelineTrackingSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public interface IBugStatusTrendChartWidget extends PageView {
    void display(TimelineTrackingSearchCriteria searchCriteria);
}
