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

import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.BusinessDayTimeUtils;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.tltv.gantt.client.shared.Step;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.8
 */
public class GanttItemWrapper {
    private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    private SimpleTask task;
    private LocalDate startDate, endDate;
    private GanttItemWrapper parent;
    private Step ownStep;
    private List<GanttItemWrapper> subItems;

    public GanttItemWrapper(SimpleTask task) {
        this.task = task;
        calculateDates();
        this.ownStep = generateStep();
    }

    public SimpleTask getTask() {
        return task;
    }

    public void setTask(SimpleTask task) {
        this.task = task;
    }

    public boolean hasSubTasks() {
        return (task.getNumSubTasks() != null && task.getNumSubTasks() > 0);
    }

    public String getName() {
        return task.getTaskname();
    }

    public List<GanttItemWrapper> subTasks(SearchCriteria.OrderField orderField) {
        List<SimpleTask> subTasks = projectTaskService.findSubTasks(task.getId(), AppContext.getAccountId(), orderField);
        if (subItems == null) {
            subItems = new ArrayList<>();
            for (SimpleTask subTask : subTasks) {
                GanttItemWrapper subItem = new GanttItemWrapper(subTask);
                subItem.setParent(this);
                subItems.add(subItem);
            }
        }

        return subItems;
    }

    public Integer getId() {
        return task.getId();
    }

    private void calculateDates() {
        startDate = (task.getStartdate() != null) ? new LocalDate(task.getStartdate()) : new LocalDate();
        endDate = (task.getEnddate() != null) ? new LocalDate(task.getEnddate()) : new LocalDate();
    }

    public Double getDuration() {
        if (task.getDuration() != null) {
            return task.getDuration();
        } else {
            return (BusinessDayTimeUtils.duration(startDate, endDate) + 1) * 1d;
        }
    }

    public List<TaskPredecessor> getPredecessors() {
        return task.getPredecessors();
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Date getActualStartDate() {
        return task.getActualstartdate();
    }

    public Date getActualEndDate() {
        return task.getActualenddate();
    }

    public Date getDueDate() {
        return task.getDeadline();
    }

    public Double getPercentageComplete() {
        return task.getPercentagecomplete();
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        task.setStartdate(startDate.toDate());
        ownStep.setStartDate(startDate.toDate());
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        task.setEnddate(endDate.toDate());
        ownStep.setEndDate(endDate.toDate());
    }

    public Integer getGanttIndex() {
        return task.getGanttindex();
    }

    public void setGanttIndex(Integer ganttIndex) {
        task.setGanttindex(ganttIndex);
    }

    public String getAssignUser() {
        return task.getAssignuser();
    }

    String buildCaption() {
        return task.getTaskname();
    }

    String buildTooltip() {
        return ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(), task, AppContext.getSiteUrl(),
                AppContext.getTimezone());
    }

    public void markAsDirty() {
        calculateDates();
        ownStep.setCaption(buildCaption());
        ownStep.setDescription(buildTooltip());
        ownStep.setStartDate(startDate.toDate());
        ownStep.setEndDate(endDate.plusDays(1).toDate());
    }

    StepExt generateStep() {
        StepExt step = new StepExt();
        step.setCaption(buildCaption());
        step.setCaptionMode(Step.CaptionMode.HTML);
        step.setDescription(buildTooltip());
        step.setStartDate(startDate.toDate());
        step.setEndDate(endDate.plusDays(1).toDate());
        step.setGanttItemWrapper(this);
        step.setProgress(task.getPercentagecomplete());
        return step;
    }

    public GanttItemWrapper getParent() {
        return parent;
    }

    public void setParent(GanttItemWrapper parent) {
        this.parent = parent;
    }

    public Step getStep() {
        return ownStep;
    }
}
