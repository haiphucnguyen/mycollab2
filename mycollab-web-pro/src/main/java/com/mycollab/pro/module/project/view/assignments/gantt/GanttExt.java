package com.mycollab.pro.module.project.view.assignments.gantt;

import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.module.project.CurrentProjectVariables;
import com.mycollab.module.project.ProjectRolePermissionCollections;
import com.mycollab.module.project.i18n.GanttI18nEnum;
import com.mycollab.pro.module.project.events.GanttEvent;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.server.Page;
import org.joda.time.LocalDate;
import org.tltv.gantt.Gantt;
import org.tltv.gantt.StepComponent;
import org.tltv.gantt.SubStepComponent;
import org.tltv.gantt.client.shared.AbstractStep;
import org.tltv.gantt.client.shared.Step;
import org.tltv.gantt.client.shared.SubStep;

import java.util.TimeZone;

/**
 * @author MyCollab Ltd
 * @since 5.0.8
 */
public class GanttExt extends Gantt {
    private LocalDate minDate, maxDate;
    private GanttItemContainer beanContainer;

    public GanttExt() {
        this.setTimeZone(TimeZone.getTimeZone("Atlantic/Reykjavik"));
        this.setImmediate(true);
        minDate = new LocalDate(2100, 1, 1);
        maxDate = new LocalDate(1970, 1, 1);
        this.setResizableSteps(true);
        this.setMovableSteps(true);
        this.setHeight((Page.getCurrent().getBrowserWindowHeight() - 270) + "px");
        beanContainer = new GanttItemContainer();

        this.addClickListener(clickEvent -> {
            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
                StepExt step = (StepExt) clickEvent.getStep();
                getUI().addWindow(new QuickEditGanttItemWindow(GanttExt.this, step.getGanttItemWrapper()));
            }
        });

        this.addMoveListener(moveEvent -> {
            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
                updateTasksInfoByResizeOrMove((StepExt) moveEvent.getStep(), moveEvent.getStartDate(), moveEvent.getEndDate());
            }
        });

        this.addResizeListener(resizeEvent -> {
            if (CurrentProjectVariables.canWrite(ProjectRolePermissionCollections.INSTANCE.getTASKS())) {
                updateTasksInfoByResizeOrMove((StepExt) resizeEvent.getStep(), resizeEvent.getStartDate(), resizeEvent.getEndDate());
            }
        });
    }

    public GanttItemContainer getBeanContainer() {
        return beanContainer;
    }

    public int getStepIndex(Step step) {
        StepComponent sc = this.stepComponents.get(step);
        return this.getState().steps.indexOf(sc);
    }

    void addTask(GanttItemWrapper task) {
        Step step = task.getStep();
        super.addStep(step);
        calculateMaxMinDates(task);
    }

    public void addTask(int index, GanttItemWrapper task) {
        Step step = task.getStep();
        super.addStep(index, step);
        calculateMaxMinDates(task);
    }

    private void updateGanttDates() {
        if (minDate.isAfter(maxDate)) {
            minDate = new LocalDate();
            maxDate = new LocalDate();
        }
        this.setStartDate(minDate.minusDays(14).toDate());
        this.setEndDate(maxDate.plusDays(14).toDate());
    }

    public void calculateMaxMinDates(GanttItemWrapper task) {
        if (minDate.isAfter(task.getStartDate())) {
            minDate = task.getStartDate();
        }

        if (maxDate.isBefore(task.getEndDate())) {
            maxDate = task.getEndDate();
        }

        updateGanttDates();
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

    private void updateTasksInfoByResizeOrMove(StepExt step, long startDate, long endDate) {
        final GanttItemWrapper ganttItemWrapper = step.getGanttItemWrapper();
        if (ganttItemWrapper.hasSubTasks()) {
            step.setStartDate(ganttItemWrapper.getStartDate().toDate());
            step.setEndDate(ganttItemWrapper.getEndDate().plusDays(1).toDate());
            EventBusFactory.getInstance().post(new GanttEvent.UpdateGanttItem(GanttExt.this, ganttItemWrapper));
            NotificationUtil.showWarningNotification(UserUIContext.getMessage(GanttI18nEnum.ERROR_CAN_NOT_CHANGE_PARENT_TASK_DATES));
        } else {
            LocalDate suggestedStartDate = new LocalDate(startDate);
            LocalDate suggestedEndDate = new LocalDate(endDate);
            ganttItemWrapper.setStartAndEndDate(suggestedStartDate, suggestedEndDate, true, true);
            EventBusFactory.getInstance().post(new GanttEvent.UpdateGanttItem(GanttExt.this, ganttItemWrapper));
            EventBusFactory.getInstance().post(new GanttEvent.AddGanttItemUpdateToQueue(GanttExt.this, ganttItemWrapper));
            this.calculateMaxMinDates(ganttItemWrapper);
        }
    }
}