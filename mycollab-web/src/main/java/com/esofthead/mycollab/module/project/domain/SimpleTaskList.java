/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author haiphucnguyen
 */
public class SimpleTaskList extends TaskList {
    private static final long serialVersionUID = 1L;
    private String milestoneName;
    private String ownerAvatarId;
    private String ownerFullName;
    private List<SimpleTask> subTasks = new ArrayList<SimpleTask>();
    private double percentageComplete;
    private int numOpenTasks;
    private int numAllTasks;
    private int numComments;
    private String comment;

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

    public Date getStartDate() {
        Date result = null;
        for (SimpleTask task : subTasks) {
            if (task.getStartdate() != null) {
                if (result == null) {
                    result = task.getStartdate();
                } else {
                    if (result.after(task.getStartdate())) {
                        result = task.getStartdate();
                    }
                }
            }
        }
        return result;
    }

    public Date getEndDate() {
        Date result = null;
        for (SimpleTask task : subTasks) {
            if (task.getEnddate() != null) {
                if (result == null) {
                    result = task.getEnddate();
                } else {
                    if (result.before(task.getEnddate())) {
                        result = task.getEnddate();
                    }
                }
            }
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SimpleTaskList)) {
            return false;
        } else {
            SimpleTaskList taskList = (SimpleTaskList) o;
            return (taskList.getId() == this.getId() && taskList
                    .getGroupindex() == this.getGroupindex());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47
                * hash
                + (this.milestoneName != null ? this.milestoneName.hashCode()
                        : 0);
        hash = 47
                * hash
                + (this.ownerFullName != null ? this.ownerFullName.hashCode()
                        : 0);
        return hash;
    }

    public double getPercentageComplete() {
        return percentageComplete;
    }

    public void setPercentageComplete(double percentageComplete) {
        this.percentageComplete = percentageComplete;
    }

    public int getNumOpenTasks() {
        return numOpenTasks;
    }

    public void setNumOpenTasks(int numOpenTasks) {
        this.numOpenTasks = numOpenTasks;
    }

    public int getNumAllTasks() {
        return numAllTasks;
    }

    public void setNumAllTasks(int numAllTasks) {
        this.numAllTasks = numAllTasks;
    }

    public String getOwnerAvatarId() {
        return ownerAvatarId;
    }

    public void setOwnerAvatarId(String ownerAvatarId) {
        this.ownerAvatarId = ownerAvatarId;
    }

    public int getNumComments() {
        return numComments;
    }

    public void setNumComments(int numComments) {
        this.numComments = numComments;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
