package com.esofthead.mycollab.module.project.domain;


import java.util.List;

public class TaskGanttItem extends AssignWithPredecessors {
    private List<TaskGanttItem> subTasks;

    private Integer parentTaskId;

    public List<TaskGanttItem> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<TaskGanttItem> subTasks) {
        this.subTasks = subTasks;
    }

    public Integer getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(Integer parentTaskId) {
        this.parentTaskId = parentTaskId;
    }
}
