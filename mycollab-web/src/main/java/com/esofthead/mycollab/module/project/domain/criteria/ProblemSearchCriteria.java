package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class ProblemSearchCriteria extends SearchCriteria {
    private StringSearchField problemname;

    private NumberSearchField projectId;
    
    private StringSearchField raisedByUser;
    
    private StringSearchField assignToUser;
    
    private BooleanSearchField isCompleted;

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

	public BooleanSearchField getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(BooleanSearchField isCompleted) {
		this.isCompleted = isCompleted;
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
}
