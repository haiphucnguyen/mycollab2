/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haiphucnguyen
 */
public class SimpleTaskList extends TaskList {

    private String milestoneName;
    private String ownerFullName;
    private List<SimpleTask> subTasks = new ArrayList<SimpleTask>();

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

    public List<SimpleTask> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<SimpleTask> subTasks) {
        this.subTasks = subTasks;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SimpleTaskList)) {
            return false;
        } else {
            SimpleTaskList taskList = (SimpleTaskList) o;
            return (taskList.getId() == this.getId() && taskList.getGroupindex() == this.getGroupindex());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.milestoneName != null ? this.milestoneName.hashCode() : 0);
        hash = 47 * hash + (this.ownerFullName != null ? this.ownerFullName.hashCode() : 0);
        return hash;
    }
    
    
}
