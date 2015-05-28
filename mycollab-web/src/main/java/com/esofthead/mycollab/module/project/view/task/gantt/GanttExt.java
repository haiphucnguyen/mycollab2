package com.esofthead.mycollab.module.project.view.task.gantt;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.StepComponent;
import org.tltv.gantt.client.shared.Step;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class GanttExt extends Gantt {

    public int getStepIndex(Step step) {
        StepComponent sc = this.stepComponents.get(step);
        return this.getState().steps.indexOf(sc);
    }
}