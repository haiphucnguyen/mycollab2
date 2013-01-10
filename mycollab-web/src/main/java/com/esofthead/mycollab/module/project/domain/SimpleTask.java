package com.esofthead.mycollab.module.project.domain;

public class SimpleTask extends Task {

    private static final long serialVersionUID = 1L;
    private String projectName;
    private String taskListName;
    private int numComments;

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskListName() {
        return taskListName;
    }

    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName;
    }
}
