package com.mycollab.pro.module.project.view.assignments

import com.mycollab.module.project.view.IGanttChartView
import com.mycollab.pro.module.project.view.assignments.gantt.GanttExt
import com.mycollab.pro.module.project.view.assignments.gantt.GanttTreeTable
import com.mycollab.vaadin.mvp.LazyPageView

/**
 * @author MyCollab Ltd.
 * @since 4.0
 */
interface GanttChartView : IGanttChartView, LazyPageView {

    val gantt: GanttExt

    val taskTable: GanttTreeTable
}
