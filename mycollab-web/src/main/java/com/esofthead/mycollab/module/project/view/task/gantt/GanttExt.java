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

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
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
    private GanttItemContainer beanContainer;
    private ProjectTaskService taskService;

    public GanttExt() {
        minDate = new LocalDate();
        maxDate = new LocalDate();
        this.setResizableSteps(true);
        this.setMovableSteps(true);
        this.setHeight((Page.getCurrent().getBrowserWindowHeight() - 270) + "px");
        updateGanttMinDate();
        updateGanttMaxDate();
        beanContainer = new GanttItemContainer();

        this.addClickListener(new Gantt.ClickListener() {
            @Override
            public void onGanttClick(Gantt.ClickEvent clickEvent) {
                if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.TASKS)) {
                    StepExt step = (StepExt) clickEvent.getStep();
                    getUI().addWindow(new QuickEditTaskWindow(GanttExt.this, step.getGanttItemWrapper()));
                }
            }
        });

        this.addMoveListener(new Gantt.MoveListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttMove(MoveEvent event) {
                StepExt step = (StepExt) event.getStep();
                updateTasksInfo(step, event.getStartDate(), event.getEndDate());
            }
        });

        this.addResizeListener(new Gantt.ResizeListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void onGanttResize(ResizeEvent event) {
                updateTasksInfo((StepExt) event.getStep(), event.getStartDate(), event.getEndDate());
            }
        });

        taskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    }

    public GanttItemContainer getBeanContainer() {
        return beanContainer;
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
    protected void fireMoveEvent(String stepUid, String newStepUid, long startDate, long endDate) {
        AbstractStep step = getStep(stepUid);
        if (step instanceof StepExt) {
            StepExt stepExt = (StepExt) step;
            GanttItemWrapper item = stepExt.getGanttItemWrapper();
            if (item.hasSubTasks()) {
                step.setStartDate(item.getStartDate().toDate());
                step.setEndDate(item.getEndDate().plusDays(1).toDate());
                this.markStepDirty(step);
                NotificationUtil.showWarningNotification("Can not adjust dates of parent task");
            } else {
                super.fireMoveEvent(stepUid, newStepUid, startDate, endDate);
            }
        }
    }

    @Override
    protected void fireResizeEvent(String stepUid, long startDate, long endDate) {
        AbstractStep step = getStep(stepUid);
        if (step instanceof StepExt) {
            StepExt stepExt = (StepExt) step;
            GanttItemWrapper item = stepExt.getGanttItemWrapper();
            if (item.hasSubTasks()) {
                step.setStartDate(item.getStartDate().toDate());
                step.setEndDate(item.getEndDate().plusDays(1).toDate());
                this.markStepDirty(step);
                NotificationUtil.showWarningNotification("Can not adjust dates of parent task");
            } else {
                super.fireResizeEvent(stepUid, startDate, endDate);
            }
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

    private void updateTasksInfo(StepExt step, long startDate, long endDate) {
        GanttItemWrapper ganttItemWrapper = step.getGanttItemWrapper();
        SimpleTask task = ganttItemWrapper.getTask();
        ganttItemWrapper.setStartDate(new LocalDate(startDate));
        ganttItemWrapper.setEndDate(new LocalDate(endDate));
        taskService.updateSelectiveWithSession(task, AppContext.getUsername());
        ganttItemWrapper.updateParentDates();
        EventBusFactory.getInstance().post(new TaskEvent.GanttTaskUpdate(GanttExt.this, ganttItemWrapper));
        this.calculateMaxMinDates(ganttItemWrapper);
    }
}