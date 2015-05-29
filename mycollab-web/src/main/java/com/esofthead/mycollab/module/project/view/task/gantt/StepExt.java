package com.esofthead.mycollab.module.project.view.task.gantt;

import org.tltv.gantt.client.shared.AbstractStep;
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

    public int hashCode() {
        byte result = 1;
        int result1 = 31 * result + (this.getUid() == null?0:this.getUid().hashCode());
        return result1;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else {
            AbstractStep other = (AbstractStep)obj;
            if(this.getUid() == null) {
                if(other.getUid() != null) {
                    return false;
                }
            } else if(!this.getUid().equals(other.getUid())) {
                return false;
            }

            return true;
        }
    }
}
