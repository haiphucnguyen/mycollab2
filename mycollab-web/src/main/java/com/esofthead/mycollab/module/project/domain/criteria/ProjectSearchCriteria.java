package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProjectSearchCriteria extends SearchCriteria {

    private StringSearchField username;
    private StringSearchField ownerName;
    private StringSearchField accountName;
    private StringSearchField projectStatus;
    private StringSearchField projectType;
    private NumberSearchField saccountid;
    private StringSearchField projectName;
    private StringSearchField involvedMember;

    public StringSearchField getUsername() {
        return username;
    }

    public void setUsername(StringSearchField username) {
        this.username = username;
    }

    public StringSearchField getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(StringSearchField ownerName) {
        this.ownerName = ownerName;
    }

    public StringSearchField getAccountName() {
        return accountName;
    }

    public void setAccountName(StringSearchField accountName) {
        this.accountName = accountName;
    }

    public StringSearchField getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(StringSearchField projectStatus) {
        this.projectStatus = projectStatus;
    }

    public StringSearchField getProjectType() {
        return projectType;
    }

    public void setProjectType(StringSearchField projectType) {
        this.projectType = projectType;
    }

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

	public void setProjectName(StringSearchField projectName) {
		this.projectName = projectName;
	}

	public StringSearchField getProjectName() {
		return projectName;
	}

	public StringSearchField getInvolvedMember() {
		return involvedMember;
	}

	public void setInvolvedMember(StringSearchField involvedMember) {
		this.involvedMember = involvedMember;
	}
}
