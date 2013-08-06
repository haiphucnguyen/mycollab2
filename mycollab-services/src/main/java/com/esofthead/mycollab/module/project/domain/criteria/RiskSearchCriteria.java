package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class RiskSearchCriteria extends SearchCriteria {
	private StringSearchField riskname;

	private StringSearchField raisedByUser;

	private StringSearchField assignToUser;

	private BooleanSearchField isCompleted;

	private NumberSearchField projectId;
	
	 private NumberSearchField id;

	public StringSearchField getRiskname() {
		return riskname;
	}

	public void setRiskname(StringSearchField riskname) {
		this.riskname = riskname;
	}

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public StringSearchField getRaisedByUser() {
		return raisedByUser;
	}

	public void setRaisedByUser(StringSearchField raisedByUser) {
		this.raisedByUser = raisedByUser;
	}

	public StringSearchField getAssignToUser() {
		return assignToUser;
	}

	public void setAssignToUser(StringSearchField assignToUser) {
		this.assignToUser = assignToUser;
	}

	public BooleanSearchField getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(BooleanSearchField isCompleted) {
		this.isCompleted = isCompleted;
	}

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}
}
