package com.esofthead.mycollab.module.tracker.domain.criteria;

import java.util.Date;

import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class BugSearchCriteria extends SearchCriteria {
	private String assignuser;

	private String involeduser;

	private String loguser;

	private Integer projectid;

	private Date postedDateFrom;

	private Date postedDateTo;
	
	private Date updatedDateFrom;
	
	private Date updatedDateTo;
	
	private Date dueDateFrom;
	
	private Date dueDateTo;
	
	private Date resolvedDateFrom;
	
	private Date resolvedDateTo;

	private String summary;

	private String detail;

	private String environment;

	private Integer[] resolutions;

	private Integer[] componentids;

	private Integer[] affectedversionids;

	private Integer[] fixedversionids;
	
	private Integer[] versionids;

	private Integer[] priorities;

	private Integer[] severities;

	private Integer[] statuses;

	public Integer[] getVersionids() {
		return versionids;
	}

	public void setVersionids(Integer[] versionids) {
		this.versionids = versionids;
	}

	public Integer[] getAffectedversionids() {
		return affectedversionids;
	}

	public void setAffectedversionids(Integer[] affectedversionids) {
		this.affectedversionids = affectedversionids;
	}

	public Integer[] getFixedversionids() {
		return fixedversionids;
	}

	public void setFixedversionids(Integer[] fixedversionids) {
		this.fixedversionids = fixedversionids;
	}

	public Integer[] getStatuses() {
		return statuses;
	}

	public void setStatuses(Integer[] statuses) {
		this.statuses = statuses;
	}

	public Integer[] getPriorities() {
		return priorities;
	}

	public void setPriorities(Integer[] priorities) {
		this.priorities = priorities;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public Integer[] getResolutions() {
		return resolutions;
	}

	public void setResolutions(Integer[] resolutions) {
		this.resolutions = resolutions;
	}

	public Integer[] getComponentids() {
		return componentids;
	}

	public void setComponentids(Integer[] componentids) {
		this.componentids = componentids;
	}

	public Integer[] getSeverities() {
		return severities;
	}

	public void setSeverities(Integer[] severities) {
		this.severities = severities;
	}

	public Integer getProjectid() {
		return projectid;
	}

	public void setProjectid(Integer projectid) {
		this.projectid = projectid;
	}

	public String getLoguser() {
		return loguser;
	}

	public void setLoguser(String loguser) {
		this.loguser = loguser;
	}

	public String getAssignuser() {
		return assignuser;
	}

	public void setAssignuser(String assignuser) {
		this.assignuser = assignuser;
	}

	public String getInvoleduser() {
		return involeduser;
	}

	public void setInvoleduser(String involeduser) {
		this.involeduser = involeduser;
	}

	public Date getPostedDateFrom() {
		return postedDateFrom;
	}

	public void setPostedDateFrom(Date postedDateFrom) {
		this.postedDateFrom = postedDateFrom;
	}

	public Date getPostedDateTo() {
		return postedDateTo;
	}

	public void setPostedDateTo(Date postedDateTo) {
		this.postedDateTo = postedDateTo;
	}

	public Date getUpdatedDateFrom() {
		return updatedDateFrom;
	}

	public void setUpdatedDateFrom(Date updatedDateFrom) {
		this.updatedDateFrom = updatedDateFrom;
	}

	public Date getUpdatedDateTo() {
		return updatedDateTo;
	}

	public void setUpdatedDateTo(Date updatedDateTo) {
		this.updatedDateTo = updatedDateTo;
	}

	public Date getDueDateFrom() {
		return dueDateFrom;
	}

	public void setDueDateFrom(Date dueDateFrom) {
		this.dueDateFrom = dueDateFrom;
	}

	public Date getDueDateTo() {
		return dueDateTo;
	}

	public void setDueDateTo(Date dueDateTo) {
		this.dueDateTo = dueDateTo;
	}

	public Date getResolvedDateFrom() {
		return resolvedDateFrom;
	}

	public void setResolvedDateFrom(Date resolvedDateFrom) {
		this.resolvedDateFrom = resolvedDateFrom;
	}

	public Date getResolvedDateTo() {
		return resolvedDateTo;
	}

	public void setResolvedDateTo(Date resolvedDateTo) {
		this.resolvedDateTo = resolvedDateTo;
	}
	
	

}
