package com.esofthead.mycollab.pro.module.project.view.assignments.gantt;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;

/**
 * @author MyCollab Ltd.
 * @since 5.0.8
 */
public class StepExt extends Step {
    private GanttItemWrapper ganttItemWrapper;

    public GanttItemWrapper getGanttItemWrapper() {
        return ganttItemWrapper;
    }

    public void setGanttItemWrapper(GanttItemWrapper ganttItemWrapper) {
        this.ganttItemWrapper = ganttItemWrapper;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(1, 31).append(getUid()).build();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else {
            if (obj instanceof AbstractStep) {
                AbstractStep other = (AbstractStep) obj;
                if (this.getUid() == null) {
                    if (other.getUid() != null) {
                        return false;
                    }
                } else if (!this.getUid().equals(other.getUid())) {
                    return false;
                }

                return true;
            } else {
                return false;
            }
        }
    }

}
