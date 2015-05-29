package com.esofthead.mycollab.module.project.view.task.gantt;

import org.tltv.gantt.Gantt;
import org.tltv.gantt.StepComponent;
import org.tltv.gantt.SubStepComponent;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
public class GanttExt extends Gantt {

    public int getStepIndex(Step step) {
        StepComponent sc = this.stepComponents.get(step);
        return this.getState().steps.indexOf(sc);
    }

    @Override
    public AbstractStep getStep(String uid) {
        if(uid == null) {
            return null;
        } else {
            StepExt key = new StepExt();
            key.setUid(uid);
            StepComponent sc = this.stepComponents.get(key);
            if(sc != null) {
                return sc.getState().step;
            } else {
                SubStep key1 = new SubStep();
                key1.setUid(uid);
                SubStepComponent sub = this.subStepMap.get(key1);
                return sub != null?sub.getState().step:null;
            }
        }
    }
}