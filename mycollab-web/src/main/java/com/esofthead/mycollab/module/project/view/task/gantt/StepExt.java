package com.esofthead.mycollab.module.project.view.task.gantt;

import org.tltv.gantt.client.shared.Step;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
public class StepExt extends Step {
    private GanttItemWrapper ganttItemWrapper;

    public GanttItemWrapper getGanttItemWrapper() {
        return ganttItemWrapper;
    }

    public void setGanttItemWrapper(GanttItemWrapper ganttItemWrapper) {
        this.ganttItemWrapper = ganttItemWrapper;
    }
}
