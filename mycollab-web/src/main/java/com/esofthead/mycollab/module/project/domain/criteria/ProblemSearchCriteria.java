package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProblemSearchCriteria extends SearchCriteria {

    private StringSearchField problemname;
    private NumberSearchField projectId;
    private StringSearchField raisedByUser;
    private StringSearchField assignToUser;
    private NumberSearchField id;

    public StringSearchField getProblemname() {
        return problemname;
    }

    public void setProblemname(StringSearchField problemname) {
        this.problemname = problemname;
    }

    public NumberSearchField getProjectId() {
        return projectId;
    }

    public void setProjectId(NumberSearchField projectId) {
        this.projectId = projectId;
    }

    public StringSearchField getAssignToUser() {
        return assignToUser;
    }

    public void setAssignToUser(StringSearchField assignToUser) {
        this.assignToUser = assignToUser;
    }

    public StringSearchField getRaisedByUser() {
        return raisedByUser;
    }

    public void setRaisedByUser(StringSearchField raisedByUser) {
        this.raisedByUser = raisedByUser;
    }

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}
}
