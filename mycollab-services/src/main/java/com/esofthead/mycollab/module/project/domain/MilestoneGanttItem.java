package com.esofthead.mycollab.module.project.domain;


import org.apache.commons.collections.CollectionUtils;

import java.util.List;

public class MilestoneGanttItem extends AssignWithPredecessors {
    private List<TaskGanttItem> subTasks;

    public List<TaskGanttItem> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskGanttItem> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public boolean hasSubAssignments() {
        return CollectionUtils.isNotEmpty(subTasks);
    }
}
