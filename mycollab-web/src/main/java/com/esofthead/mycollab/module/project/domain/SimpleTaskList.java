/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class SimpleTaskList extends TaskList{
    private String milestoneName;
    
    private String ownerFullName;
    
    private List<SimpleTask> tasks;

    public List<SimpleTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<SimpleTask> tasks) {
        this.tasks = tasks;
    }

    public String getMilestoneName() {
        return milestoneName;
    }

    public void setMilestoneName(String milestoneName) {
        this.milestoneName = milestoneName;
    }

    public String getOwnerFullName() {
        return ownerFullName;
    }

    public void setOwnerFullName(String ownerFullName) {
        this.ownerFullName = ownerFullName;
    }
}
