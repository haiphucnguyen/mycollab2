package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class BugSearchCriteria extends SearchCriteria {
	private StringSearchField assignuser;

	private StringSearchField involeduser;

	private StringSearchField loguser;

	private NumberSearchField projectid;

	private DateSearchField postedDateFrom;

	private DateSearchField postedDateTo;
	
	private DateSearchField updatedDateFrom;
	
	private DateSearchField updatedDateTo;
	
	private DateSearchField dueDateFrom;
	
	private DateSearchField dueDateTo;
	
	private DateSearchField resolvedDateFrom;
	
	private DateSearchField resolvedDateTo;

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

	public DateSearchField getPostedDateFrom() {
		return postedDateFrom;
	}

	public void setPostedDateFrom(DateSearchField postedDateFrom) {
		this.postedDateFrom = postedDateFrom;
	}

	public DateSearchField getPostedDateTo() {
		return postedDateTo;
	}

	public void setPostedDateTo(DateSearchField postedDateTo) {
		this.postedDateTo = postedDateTo;
	}

	public DateSearchField getUpdatedDateFrom() {
		return updatedDateFrom;
	}

	public void setUpdatedDateFrom(DateSearchField updatedDateFrom) {
		this.updatedDateFrom = updatedDateFrom;
	}

	public DateSearchField getUpdatedDateTo() {
		return updatedDateTo;
	}

	public void setUpdatedDateTo(DateSearchField updatedDateTo) {
		this.updatedDateTo = updatedDateTo;
	}

	public DateSearchField getDueDateFrom() {
		return dueDateFrom;
	}

	public void setDueDateFrom(DateSearchField dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}

	public DateSearchField getDueDateTo() {
		return dueDateTo;
	}

	public void setDueDateTo(DateSearchField dueDateTo) {
		this.dueDateTo = dueDateTo;
	}

	public DateSearchField getResolvedDateFrom() {
		return resolvedDateFrom;
	}

	public void setResolvedDateFrom(DateSearchField resolvedDateFrom) {
		this.resolvedDateFrom = resolvedDateFrom;
	}

	public DateSearchField getResolvedDateTo() {
		return resolvedDateTo;
	}

	public void setResolvedDateTo(DateSearchField resolvedDateTo) {
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
