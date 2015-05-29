package com.esofthead.mycollab.module.project.view.task.gantt;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.1.0
 */
public class TaskGanttItemWrapper extends GanttItemWrapper {
    private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    private SimpleTask task;
    private Date startDate, endDate;

    public TaskGanttItemWrapper(SimpleTask task, Date minDate, Date maxDate) {
        super(minDate, maxDate);
        this.task = task;
        calculateDates();
        this.ownStep = generateStep();
    }

    @Override
    public String getName() {
        return task.getTaskname();
    }

    @Override
    public List<GanttItemWrapper> subTasks() {
        List<SimpleTask> subTasks = projectTaskService.findSubTasks(task.getId(), AppContext.getAccountId());
        if (subItems == null) {
            subItems = new ArrayList<>();
            for (SimpleTask subTask : subTasks) {
                TaskGanttItemWrapper subItem = new TaskGanttItemWrapper(subTask, minDate, maxDate);
                subItem.setParent(this);
                subItems.add(subItem);
            }
        }

        return subItems;
    }

    private void calculateDates() {
        startDate = task.getStartdate();
        endDate = task.getEnddate();

        if (endDate == null) {
            endDate = task.getDeadline();
        }

        if (startDate == null) {
            if (endDate == null) {
                startDate = DateTimeUtils.getCurrentDateWithoutMS();
                endDate = DateTimeUtils.subtractOrAddDayDuration(startDate, 1);
            } else {
                endDate = DateTimeUtils.trimHMSOfDate(endDate);
                startDate = DateTimeUtils.subtractOrAddDayDuration(endDate, -1);
            }
        } else {
            startDate = DateTimeUtils.trimHMSOfDate(startDate);
            if (endDate == null) {
                endDate = DateTimeUtils.subtractOrAddDayDuration(startDate, 1);
            } else {
                endDate = DateTimeUtils.trimHMSOfDate(endDate);
                endDate = DateTimeUtils.subtractOrAddDayDuration(endDate, 1);
            }
        }
    }

    @Override
    Date getStartDate() {
        return startDate;
    }

    @Override
    Date getEndDate() {
        return endDate;
    }

    @Override
    String buildCaption() {
        return task.getTaskname();
    }
}
