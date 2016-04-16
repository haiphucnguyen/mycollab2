package com.esofthead.mycollab.pro.module.project.view.assignments;

import com.esofthead.mycollab.module.project.view.IGanttChartView;
import com.esofthead.mycollab.pro.module.project.view.assignments.gantt.GanttExt;
import com.esofthead.mycollab.pro.module.project.view.assignments.gantt.GanttTreeTable;
import com.esofthead.mycollab.vaadin.mvp.LazyPageView;

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
public interface GanttChartView extends IGanttChartView, LazyPageView {

    GanttExt getGantt();

    GanttTreeTable getTaskTable();
}
