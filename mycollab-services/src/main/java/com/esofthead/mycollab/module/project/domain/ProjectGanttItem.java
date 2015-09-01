package com.esofthead.mycollab.module.project.domain;


import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class ProjectGanttItem extends AssignWithPredecessors {
    private List<MilestoneGanttItem> subTasks;

    private List<TaskGanttItem> tasksWithNoMilestones;

    public List<MilestoneGanttItem> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<MilestoneGanttItem> subTasks) {
        this.subTasks = subTasks;
    }

    public List<TaskGanttItem> getTasksWithNoMilestones() {
        return tasksWithNoMilestones;
    }

    public void setTasksWithNoMilestones(List<TaskGanttItem> tasksWithNoMilestones) {
        this.tasksWithNoMilestones = tasksWithNoMilestones;
    }

    @Override
    public boolean hasSubAssignments() {
        return CollectionUtils.isNotEmpty(subTasks) || CollectionUtils.isNotEmpty(tasksWithNoMilestones);
    }
}
