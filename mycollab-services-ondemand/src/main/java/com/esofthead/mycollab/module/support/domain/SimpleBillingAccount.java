package com.esofthead.mycollab.module.support.domain;

import com.esofthead.mycollab.module.user.domain.BillingAccount;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class SimpleBillingAccount extends BillingAccount {
    private int numUsers;
    private int numProjects;
    private Date lastAccessTime;

    public int getNumUsers() {
        return numUsers;
    }

    public void setNumUsers(int numUsers) {
        this.numUsers = numUsers;
    }

    public int getNumProjects() {
        return numProjects;
    }

    public void setNumProjects(int numProjects) {
        this.numProjects = numProjects;
    }

    public Date getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Date lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }
}
