package com.esofthead.mycollab.module.project.domain;

public class SimpleTaskResource extends TaskResource {
    private String taskname;

    private String resourcename;

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }
}
