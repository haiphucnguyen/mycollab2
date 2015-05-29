package com.esofthead.mycollab.module.project.view.task.gantt;

import org.tltv.gantt.client.shared.Step;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
public abstract class GanttItemWrapper {
    Date minDate, maxDate;
    GanttItemWrapper parent;
    Step ownStep;
    List<GanttItemWrapper> subItems;

    GanttItemWrapper(Date minDate, Date maxDate) {
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    abstract public String getName();

    abstract public List<GanttItemWrapper> subTasks();

    abstract Date getStartDate();

    abstract Date getEndDate();

    abstract String buildCaption();

    abstract String buildTooltip();

    public Step getStep() {
        return ownStep;
    }

    StepExt generateStep() {
        Date startDate = this.getStartDate();
        if (startDate.before(minDate)) {
            startDate = minDate;
        }
        Date endDate = this.getEndDate();
        if (endDate.after(maxDate)) {
            endDate = maxDate;
        }
        StepExt step = new StepExt();
        step.setCaption(buildCaption());
        step.setCaptionMode(Step.CaptionMode.HTML);
        step.setDescription(buildTooltip());
        step.setStartDate(startDate);
        step.setEndDate(endDate);
        step.setGanttItemWrapper(this);
        return step;
    }

    public GanttItemWrapper getParent() {
        return parent;
    }

    public void setParent(GanttItemWrapper parent) {
        this.parent = parent;
    }
}
