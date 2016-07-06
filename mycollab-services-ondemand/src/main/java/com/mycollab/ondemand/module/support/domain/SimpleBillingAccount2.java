package com.mycollab.ondemand.module.support.domain;

import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.SimpleUser;

import java.util.Date;
import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class SimpleBillingAccount2 extends BillingAccount {
    private int numUsers;
    private int numProjects;
    private Date lastAccessTime;
    private List<SimpleUser> accountOwners;

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

    public List<SimpleUser> getAccountOwners() {
        return accountOwners;
    }

    public void setAccountOwners(List<SimpleUser> accountOwners) {
        this.accountOwners = accountOwners;
    }
}
