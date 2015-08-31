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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.utils.BusinessDayTimeUtils;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.ProjectTooltipGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import org.joda.time.LocalDate;
import org.tltv.gantt.client.shared.Step;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd.
 * @since 5.0.8
 */
public class GanttItemWrapper implements PropertyChangeListener {
    private PropertyChangeSupport pcs;
    private ProjectTaskService projectTaskService = ApplicationContextUtil.getSpringBean(ProjectTaskService.class);
    private SimpleTask task;
    private LocalDate startDate, endDate;

    private GanttExt gantt;
    private GanttItemWrapper parent;
    private StepExt ownStep;
    private List<GanttItemWrapper> subItems;

    public GanttItemWrapper(GanttExt gantt, SimpleTask task) {
        this.gantt = gantt;
        ownStep = new StepExt();
        setTask(task);
        pcs = new PropertyChangeSupport(this);
        pcs.addPropertyChangeListener(this);
    }

    public SimpleTask getTask() {
        return task;
    }

    public void setTask(SimpleTask task) {
        this.task = task;
        startDate = (task.getStartdate() != null) ? new LocalDate(task.getStartdate()) : new LocalDate();
        endDate = (task.getEnddate() != null) ? new LocalDate(task.getEnddate()) : new LocalDate();
        ownStep.setCaption(task.getTaskname());
        ownStep.setCaptionMode(Step.CaptionMode.HTML);
        ownStep.setDescription(buildTooltip());
        ownStep.setStartDate(startDate.toDate());
        ownStep.setEndDate(endDate.plusDays(1).toDate());
        ownStep.setGanttItemWrapper(this);
        ownStep.setProgress(task.getPercentagecomplete());
    }

    public boolean hasSubTasks() {
        return (task.getNumSubTasks() != null && task.getNumSubTasks() > 0);
    }

    public String getName() {
        return task.getTaskname();
    }

    public List<GanttItemWrapper> subTasks(SearchCriteria.OrderField orderField) {
        if (subItems == null) {
            List<SimpleTask> subTasks = projectTaskService.findSubTasks(task.getId(), AppContext.getAccountId(), orderField);
            subItems = new ArrayList<>();
            if (subTasks.size() > 0) {
                for (SimpleTask subTask : subTasks) {
                    GanttItemWrapper subItem = new GanttItemWrapper(gantt, subTask);
                    subItem.setParent(this);
                    subItems.add(subItem);
                }
            }
        }
        calculateDatesByChildTasks();
        return subItems;
    }

    private void calculateDatesByChildTasks() {
        if (subItems != null && subItems.size() > 0) {
            LocalDate calStartDate = new LocalDate(2099, 1, 1);
            LocalDate calEndDate = new LocalDate(1970, 1, 1);
            for (GanttItemWrapper item : subItems) {
                calStartDate = DateTimeUtils.min(calStartDate, item.getStartDate());
                calEndDate = DateTimeUtils.max(calEndDate, item.getEndDate());
            }
            this.setStartDate(calStartDate);
            this.setEndDate(calEndDate);
        }
    }

    public Integer getId() {
        return task.getId();
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
        if (!this.startDate.isEqual(startDate)) {
            LocalDate oldValue = this.startDate;
            this.startDate = startDate;
            task.setStartdate(startDate.toDate());
            ownStep.setStartDate(startDate.toDate());
            pcs.firePropertyChange("startDate", oldValue, startDate);
        }
    }

    public void setEndDate(LocalDate endDate) {
        if (!this.endDate.isEqual(endDate)) {
            LocalDate oldValue = this.endDate;
            this.endDate = endDate;
            task.setEnddate(endDate.toDate());
            ownStep.setEndDate(endDate.plusDays(1).toDate());
            pcs.firePropertyChange("endDate", oldValue, endDate);
        }
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

    String buildTooltip() {
        return ProjectTooltipGenerator.generateToolTipTask(AppContext.getUserLocale(), task, AppContext.getSiteUrl(),
                AppContext.getTimezone());
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

    public void adjustTaskDatesByPredecessors(List<TaskPredecessor> predecessors) {
        if (predecessors.size() > 0) {
            LocalDate currentStartDate = new LocalDate(getStartDate());
            LocalDate currentEndDate = new LocalDate(getEndDate());
            LocalDate boundStartDate = new LocalDate(1970, 1, 1);
            LocalDate boundEndDate = new LocalDate(2100, 1, 1);

            for (TaskPredecessor predecessor : predecessors) {
                int ganttIndex = predecessor.getGanttIndex();

                GanttItemWrapper ganttPredecessor = gantt.getBeanContainer().getItemByGanttIndex(ganttIndex);
                int dur = getDuration().intValue();
                if (ganttPredecessor != null) {
                    Integer lagDay = predecessor.getLagday() + 1;

                    if (TaskPredecessor.FS.equals(predecessor.getPredestype())) {
                        LocalDate endDate = new LocalDate(ganttPredecessor.getEndDate());
                        LocalDate expectedStartDate = BusinessDayTimeUtils.plusDays(endDate, lagDay);
                        if (boundStartDate.isBefore(expectedStartDate)) {
                            boundStartDate = expectedStartDate;
                        }
                        if (currentStartDate.isBefore(expectedStartDate)) {
                            currentStartDate = expectedStartDate;
                            LocalDate expectedEndDate = currentStartDate.plusDays(dur);
                            currentEndDate = DateTimeUtils.min(boundEndDate, expectedEndDate);
                        }
                    } else if (TaskPredecessor.FF.equals(predecessor.getPredestype())) {
                        LocalDate endDate = new LocalDate(ganttPredecessor.getEndDate());
                        LocalDate expectedEndDate = BusinessDayTimeUtils.plusDays(endDate, lagDay);
                        if (boundEndDate.isAfter(expectedEndDate)) {
                            boundEndDate = expectedEndDate;
                        }
                        if (currentEndDate.isAfter(expectedEndDate)) {
                            currentEndDate = expectedEndDate;
                            LocalDate expectedStartDate = currentEndDate.minusDays(dur);
                            currentStartDate = DateTimeUtils.max(boundStartDate, expectedStartDate);
                        }
                    } else if (TaskPredecessor.SF.equals(predecessor.getPredestype())) {
                        LocalDate startDate = new LocalDate(predecessor.getStartDate());
                        LocalDate expectedEndDate = BusinessDayTimeUtils.plusDays(startDate, lagDay);
                        if (boundEndDate.isAfter(expectedEndDate)) {
                            boundEndDate = expectedEndDate;
                        }
                        if (currentEndDate.isAfter(expectedEndDate)) {
                            currentEndDate = expectedEndDate;
                            LocalDate expectedStartDate = currentEndDate.minusDays(dur);
                            currentStartDate = DateTimeUtils.max(boundStartDate, expectedStartDate);
                        }
                    } else if (TaskPredecessor.SS.equals(predecessor.getPredestype())) {
                        LocalDate startDate = new LocalDate(predecessor.getStartDate());
                        LocalDate expectedStartDate = BusinessDayTimeUtils.plusDays(startDate, lagDay);

                        if (boundStartDate.isBefore(expectedStartDate)) {
                            boundStartDate = expectedStartDate;
                        }

                        if (currentStartDate.isAfter(expectedStartDate)) {
                            currentStartDate = expectedStartDate;
                            LocalDate expectedEndDate = BusinessDayTimeUtils.plusDays(startDate, lagDay);
                            currentEndDate = DateTimeUtils.min(boundEndDate, expectedEndDate);
                        }
                    } else {
                        throw new MyCollabException("Do not support predecessor type " + predecessor.getPredestype());
                    }

                    if (currentEndDate.isBefore(currentStartDate)) {
                        throw new UserInvalidInputException("Invalid constraint");
                    }
                }
            }

            setStartDate(currentStartDate);
            setEndDate(currentEndDate);
            System.out.println("Start date: " + currentStartDate + "---" + currentEndDate + " Step start " + new
                    LocalDate(getStep().getStartDate()) + "--Step end: " + new LocalDate(getStep().getEndDate()));
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        updateParentDates();
    }

    public void updateParentDates() {
        GanttItemWrapper parentTask = this.getParent();
        if (parentTask != null) {
            parentTask.calculateDatesByChildTasks();
            gantt.markStepDirty(parentTask.getStep());
            parentTask.updateParentDates();
        }
    }
}
