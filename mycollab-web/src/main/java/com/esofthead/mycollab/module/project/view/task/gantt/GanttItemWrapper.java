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

import com.esofthead.mycollab.common.TooltipBuilder;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.BusinessDayTimeUtils;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.project.domain.AssignWithPredecessors;
import com.esofthead.mycollab.module.project.domain.MilestoneGanttItem;
import com.esofthead.mycollab.module.project.domain.TaskGanttItem;
import com.esofthead.mycollab.module.project.domain.TaskPredecessor;
import com.esofthead.mycollab.module.project.events.GanttEvent;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Tr;
import com.vaadin.ui.UI;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.LocalDate;
import org.tltv.gantt.client.shared.Step;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.esofthead.mycollab.common.TooltipBuilder.TdUtil.buildCellName;
import static com.esofthead.mycollab.common.TooltipBuilder.TdUtil.buildCellValue;

/**
 * @author MyCollab Ltd.
 * @since 5.0.8
 */
public class GanttItemWrapper {
    private AssignWithPredecessors task;
    private LocalDate startDate, endDate;
    private LocalDate boundStartDate = new LocalDate(1970, 1, 1), boundEndDate = new LocalDate(2100, 1, 1);

    private GanttExt gantt;
    private GanttItemWrapper parent;
    private StepExt ownStep;
    private List<GanttItemWrapper> subItems;

    public GanttItemWrapper(GanttExt gantt, AssignWithPredecessors task) {
        this.gantt = gantt;
        ownStep = new StepExt();
        setTask(task);
    }

    public AssignWithPredecessors getTask() {
        return task;
    }

    public void setTask(AssignWithPredecessors task) {
        this.task = task;
        startDate = (task.getStartDate() != null) ? new LocalDate(task.getStartDate()) : new LocalDate();
        endDate = (task.getEndDate() != null) ? new LocalDate(task.getEndDate()) : new LocalDate();
        ownStep.setCaption(task.getName());
        ownStep.setCaptionMode(Step.CaptionMode.HTML);
        ownStep.setDescription(buildTooltip());
        ownStep.setStartDate(startDate.toDate());
        ownStep.setEndDate(endDate.plusDays(1).toDate());
        ownStep.setGanttItemWrapper(this);
    }

    public boolean isMilestone() {
        return task instanceof MilestoneGanttItem;
    }

    public boolean isTask() {
        return task instanceof TaskGanttItem;
    }

    public boolean hasSubTasks() {
        return task.hasSubAssignments();
    }

    public String getName() {
        return task.getName();
    }

    public List<GanttItemWrapper> subTasks() {
        if (subItems == null) {
            if (task instanceof MilestoneGanttItem) {
                subItems = buildSubTasks(gantt, (MilestoneGanttItem) task);
            } else if (task instanceof TaskGanttItem) {
                subItems = buildSubTasks(gantt, (TaskGanttItem) task);
            } else {
                throw new MyCollabException("Do not support type except milestone and task");
            }
        }

        return subItems;
    }

    private static List<GanttItemWrapper> buildSubTasks(GanttExt gantt, MilestoneGanttItem ganttItem) {
        List<TaskGanttItem> items = ganttItem.getSubTasks();
        return buildSubTasks(gantt, items);
    }

    private static List<GanttItemWrapper> buildSubTasks(GanttExt gantt, TaskGanttItem ganttItem) {
        List<TaskGanttItem> items = ganttItem.getSubTasks();
        return buildSubTasks(gantt, items);
    }

    private static List<GanttItemWrapper> buildSubTasks(GanttExt gantt, List<TaskGanttItem> items) {
        List<GanttItemWrapper> tmpList = new ArrayList<>(items.size());
        for (TaskGanttItem item : items) {
            GanttItemWrapper ganttItemWrapper = new GanttItemWrapper(gantt, item);
            tmpList.add(ganttItemWrapper);
        }
        return tmpList;
    }

    private void calculateDatesByChildTasks() {
        if (subItems != null && subItems.size() > 0) {
            LocalDate calStartDate = new LocalDate(2100, 1, 1);
            LocalDate calEndDate = new LocalDate(1970, 1, 1);
            for (GanttItemWrapper item : subItems) {
                calStartDate = DateTimeUtils.min(calStartDate, item.getStartDate());
                calEndDate = DateTimeUtils.max(calEndDate, item.getEndDate());
            }
            setStartAndEndDate(calStartDate, calEndDate);
        }
    }

    public Integer getId() {
        return task.getId();
    }

    public Double getDuration() {
        if (task.getDuration() != null) {
            return task.getDuration();
        } else {
            return BusinessDayTimeUtils.duration(startDate, endDate) * 1d;
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
        return task.getActualStartDate();
    }

    public Date getActualEndDate() {
        return task.getActualEndDate();
    }

    public Date getDueDate() {
        return task.getDeadline();
    }

    public Double getPercentageComplete() {
        return task.getProgress();
    }

    public void setStartAndEndDate(LocalDate newStartDate, LocalDate newEndDate) {
        boolean hasChange = false;
        if (!this.startDate.isEqual(newStartDate)) {
            hasChange = true;
            this.startDate = newStartDate;
            task.setStartDate(startDate.toDate());
            ownStep.setStartDate(startDate.toDate());
        }

        if (!this.endDate.isEqual(newEndDate)) {
            hasChange = true;
            this.endDate = newEndDate;
            task.setEndDate(endDate.toDate());
            ownStep.setEndDate(endDate.plusDays(1).toDate());
        }

        if (hasChange) {
            onDateChanges();
        }
    }

    public Integer getGanttIndex() {
        return task.getGanttIndex();
    }

    public void setGanttIndex(Integer ganttIndex) {
        task.setGanttIndex(ganttIndex);
    }

    public String getAssignUser() {
        return task.getAssignUser();
    }

    String buildTooltip() {
        TooltipBuilder tooltipBuilder = new TooltipBuilder();
        tooltipBuilder.setTitle(task.getName());
        Tr trRow2 = new Tr();
        Td cell21 = buildCellName(AppContext.getMessage(TaskI18nEnum.FORM_START_DATE));
        String startDate = AppContext.formatDate(task.getStartDate());
        Td cell22 = buildCellValue(startDate);
        Td cell23 = buildCellName(AppContext.getMessage(TaskI18nEnum.FORM_ACTUAL_START_DATE));
        String actualStartDate = AppContext.formatDate(task.getActualStartDate());
        Td cell24 = buildCellValue(actualStartDate);
        trRow2.appendChild(cell21, cell22, cell23, cell24);
        tooltipBuilder.appendRow(trRow2);

        Tr trRow3 = new Tr();
        Td cell31 = buildCellName(AppContext.getMessage(TaskI18nEnum.FORM_END_DATE));
        String endDate = AppContext.formatDate(task.getEndDate());
        Td cell32 = buildCellValue(endDate);
        Td cell33 = buildCellName(AppContext.getMessage(TaskI18nEnum.FORM_ACTUAL_END_DATE));
        String actualEndDate = AppContext.formatDate(task.getActualEndDate());
        Td cell34 = buildCellValue(actualEndDate);
        trRow3.appendChild(cell31, cell32, cell33, cell34);
        tooltipBuilder.appendRow(trRow3);

        return tooltipBuilder.create().write();
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
        if (CollectionUtils.isNotEmpty(predecessors)) {
            LocalDate currentStartDate = new LocalDate(getStartDate());
            LocalDate currentEndDate = new LocalDate(getEndDate());
            boundStartDate = new LocalDate(1970, 1, 1);
            boundEndDate = new LocalDate(2100, 1, 1);

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

            setStartAndEndDate(currentStartDate, currentEndDate);
        }
    }

    public void resetBoundDates() {
        boundStartDate = new LocalDate(1970, 1, 1);
        boundEndDate = new LocalDate(2100, 1, 1);
    }

    public void adjustDependentTasksDates() {
        List<TaskPredecessor> dependents = task.getDependents();
        if (CollectionUtils.isNotEmpty(dependents)) {
            for (TaskPredecessor dependent : dependents) {
                GanttItemWrapper dependentGanttItem = gantt.getBeanContainer().getItemByGanttIndex(dependent.getGanttIndex());
                if (dependentGanttItem != null) {
                    dependentGanttItem.adjustTaskDatesByPredecessors(dependentGanttItem.getPredecessors());
                }
            }
        }
    }

    private void onDateChanges() {
        ownStep.setDescription(buildTooltip());
        EventBusFactory.getInstance().post(new GanttEvent.AddGanttItemUpdateToQueue(GanttItemWrapper.this, task));
        gantt.markStepDirty(ownStep);
        updateParentDates();
        UI.getCurrent().push();
    }

    private void updateParentDates() {
        GanttItemWrapper parentTask = this.getParent();
        if (parentTask != null) {
            parentTask.calculateDatesByChildTasks();
            gantt.markStepDirty(parentTask.getStep());
            parentTask.updateParentDates();
        }
    }
}
