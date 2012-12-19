package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class BugSearchCriteria extends SearchCriteria {
	private StringSearchField assignuser;

	private StringSearchField involeduser;

	private StringSearchField loguser;

	private NumberSearchField projectid;

	private DateTimeSearchField postedDateFrom;

	private DateTimeSearchField postedDateTo;
	
	private DateTimeSearchField updatedDateFrom;
	
	private DateTimeSearchField updatedDateTo;
	
	private DateTimeSearchField dueDateFrom;
	
	private DateTimeSearchField dueDateTo;
	
	private DateTimeSearchField resolvedDateFrom;
	
	private DateTimeSearchField resolvedDateTo;

	private StringSearchField summary;

	private StringSearchField detail;

	private StringSearchField environment;

	private SetSearchField<Integer> resolutions;

	private SetSearchField<Integer> componentids;

	private SetSearchField<Integer> affectedversionids;

	private SetSearchField<Integer> fixedversionids;
	
	private SetSearchField<Integer> versionids;

	private SetSearchField<Integer> priorities;

	SetSearchField<String> severities;

	SetSearchField<String> statuses;

	public StringSearchField getAssignuser() {
		return assignuser;
	}

	public void setAssignuser(StringSearchField assignuser) {
		this.assignuser = assignuser;
	}

	public StringSearchField getInvoleduser() {
		return involeduser;
	}

	public void setInvoleduser(StringSearchField involeduser) {
		this.involeduser = involeduser;
	}

	public StringSearchField getLoguser() {
		return loguser;
	}

	public void setLoguser(StringSearchField loguser) {
		this.loguser = loguser;
	}

	public NumberSearchField getProjectid() {
		return projectid;
	}

	public void setProjectid(NumberSearchField projectid) {
		this.projectid = projectid;
	}

	public DateTimeSearchField getPostedDateFrom() {
		return postedDateFrom;
	}

	public void setPostedDateFrom(DateTimeSearchField postedDateFrom) {
		this.postedDateFrom = postedDateFrom;
	}

	public DateTimeSearchField getPostedDateTo() {
		return postedDateTo;
	}

	public void setPostedDateTo(DateTimeSearchField postedDateTo) {
		this.postedDateTo = postedDateTo;
	}

	public DateTimeSearchField getUpdatedDateFrom() {
		return updatedDateFrom;
	}

	public void setUpdatedDateFrom(DateTimeSearchField updatedDateFrom) {
		this.updatedDateFrom = updatedDateFrom;
	}

	public DateTimeSearchField getUpdatedDateTo() {
		return updatedDateTo;
	}

	public void setUpdatedDateTo(DateTimeSearchField updatedDateTo) {
		this.updatedDateTo = updatedDateTo;
	}

	public DateTimeSearchField getDueDateFrom() {
		return dueDateFrom;
	}

	public void setDueDateFrom(DateTimeSearchField dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}

	public DateTimeSearchField getDueDateTo() {
		return dueDateTo;
	}

	public void setDueDateTo(DateTimeSearchField dueDateTo) {
		this.dueDateTo = dueDateTo;
	}

	public DateTimeSearchField getResolvedDateFrom() {
		return resolvedDateFrom;
	}

	public void setResolvedDateFrom(DateTimeSearchField resolvedDateFrom) {
		this.resolvedDateFrom = resolvedDateFrom;
	}

	public DateTimeSearchField getResolvedDateTo() {
		return resolvedDateTo;
	}

	public void setResolvedDateTo(DateTimeSearchField resolvedDateTo) {
		this.resolvedDateTo = resolvedDateTo;
	}

	public StringSearchField getSummary() {
		return summary;
	}

	public void setSummary(StringSearchField summary) {
		this.summary = summary;
	}

	public StringSearchField getDetail() {
		return detail;
	}

	public void setDetail(StringSearchField detail) {
		this.detail = detail;
	}

	public StringSearchField getEnvironment() {
		return environment;
	}

	public void setEnvironment(StringSearchField environment) {
		this.environment = environment;
	}

	public SetSearchField<Integer> getResolutions() {
		return resolutions;
	}

	public void setResolutions(SetSearchField<Integer> resolutions) {
		this.resolutions = resolutions;
	}

	public SetSearchField<Integer> getComponentids() {
		return componentids;
	}

	public void setComponentids(SetSearchField<Integer> componentids) {
		this.componentids = componentids;
	}

	public SetSearchField<Integer> getAffectedversionids() {
		return affectedversionids;
	}

	public void setAffectedversionids(SetSearchField<Integer> affectedversionids) {
		this.affectedversionids = affectedversionids;
	}

	public SetSearchField<Integer> getFixedversionids() {
		return fixedversionids;
	}

	public void setFixedversionids(SetSearchField<Integer> fixedversionids) {
		this.fixedversionids = fixedversionids;
	}

	public SetSearchField<Integer> getVersionids() {
		return versionids;
	}

	public void setVersionids(SetSearchField<Integer> versionids) {
		this.versionids = versionids;
	}

	public SetSearchField<Integer> getPriorities() {
		return priorities;
	}

	public void setPriorities(SetSearchField<Integer> priorities) {
		this.priorities = priorities;
	}

	public SetSearchField<String> getSeverities() {
		return severities;
	}

	public void setSeverities(SetSearchField<String> severities) {
		this.severities = severities;
	}

	public SetSearchField<String> getStatuses() {
		return statuses;
	}

	public void setStatuses(SetSearchField<String> statuses) {
		this.statuses = statuses;
	}
}
