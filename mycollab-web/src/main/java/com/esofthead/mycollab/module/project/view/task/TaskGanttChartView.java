package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.View;

public interface TaskGanttChartView extends View {
	void displayGanttChart(TaskSearchCriteria searchCriteria);
}
