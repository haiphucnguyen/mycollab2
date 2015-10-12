package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.PageView;

/**
 * @author MyCollab Ltd
 * @since 5.2.0
 */
public interface ITaskPriorityChartWidget extends PageView {
    void displayChart(TaskSearchCriteria searchCriteria);
}
