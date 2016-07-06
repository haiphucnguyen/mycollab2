package com.mycollab.pro.module.project.view.assignments;

import com.mycollab.module.project.view.IGanttChartView;
import com.mycollab.pro.module.project.view.assignments.gantt.GanttExt;
import com.mycollab.pro.module.project.view.assignments.gantt.GanttTreeTable;
import com.mycollab.vaadin.mvp.LazyPageView;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public interface GanttChartView extends IGanttChartView, LazyPageView {

    GanttExt getGantt();

    GanttTreeTable getTaskTable();
}
