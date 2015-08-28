/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.project.view.task.gantt;

import com.vaadin.server.Page;
import org.joda.time.LocalDate;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.StepComponent;
import org.tltv.gantt.SubStepComponent;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
public class GanttExt extends Gantt {
    private LocalDate minDate, maxDate;

    public GanttExt() {
        minDate = new LocalDate();
        maxDate = new LocalDate();
        this.setResizableSteps(true);
        this.setMovableSteps(true);
        this.setHeight((Page.getCurrent().getBrowserWindowHeight() - 270) + "px");
        updateGanttMinDate();
        updateGanttMaxDate();
    }

    public int getStepIndex(Step step) {
        StepComponent sc = this.stepComponents.get(step);
        return this.getState().steps.indexOf(sc);
    }

    public void addTask(GanttItemWrapper task) {
        Step step = task.getStep();
        super.addStep(step);
        calculateMaxMinDates(task);
    }

    public void addTask(int index, GanttItemWrapper task) {
        Step step = task.getStep();
        super.addStep(index, step);
        calculateMaxMinDates(task);
    }

    private void updateGanttMinDate() {
        this.setStartDate(minDate.minusDays(14).toDate());
    }

    private void updateGanttMaxDate() {
        this.setEndDate(maxDate.plusDays(14).toDate());
    }

    public void calculateMaxMinDates(GanttItemWrapper task) {
        if (minDate.isAfter(task.getStartDate())) {
            minDate = task.getStartDate();
            updateGanttMinDate();
        }

        if (maxDate.isBefore(task.getEndDate())) {
            maxDate = task.getEndDate();
            updateGanttMaxDate();
        }
    }

    @Override
    public AbstractStep getStep(String uid) {
        if (uid == null) {
            return null;
        } else {
            StepExt key = new StepExt();
            key.setUid(uid);
            StepComponent sc = this.stepComponents.get(key);
            if (sc != null) {
                return sc.getState().step;
            } else {
                SubStep key1 = new SubStep();
                key1.setUid(uid);
                SubStepComponent sub = this.subStepMap.get(key1);
                return sub != null ? sub.getState().step : null;
            }
        }
    }
}