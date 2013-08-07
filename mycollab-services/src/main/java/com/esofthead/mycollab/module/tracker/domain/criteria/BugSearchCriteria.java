package com.esofthead.mycollab.module.tracker.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.RangeDateTimeSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class BugSearchCriteria extends SearchCriteria {
	private StringSearchField assignuser;

	private StringSearchField loguser;

	private DateTimeSearchField updatedDate;
	
	private RangeDateTimeSearchField updatedDateRange;
	
	private DateTimeSearchField dueDate;
	
	private RangeDateTimeSearchField dueDateRange;
	
	private DateTimeSearchField resolvedDate;
	
	private RangeDateTimeSearchField resolvedDateRange;

	private StringSearchField summary;
	
	private StringSearchField description;

	private StringSearchField detail;

	private StringSearchField environment;

	private SetSearchField<String> resolutions;

	private SetSearchField<Integer> componentids;

	private SetSearchField<Integer> affectedversionids;

	private SetSearchField<Integer> fixedversionids;
	
	private SetSearchField<Integer> versionids;
	
	private SetSearchField<Integer> milestoneIds;

	private SetSearchField<String> priorities;

	private SetSearchField<String> severities;

	private SetSearchField<String> statuses;
	
	private NumberSearchField projectId;
	
	public static String AFFVERSION = "AffVersion";
	public static String FIXVERSION = "FixVersion";
	public static String COMPONENT = "Component";
	
	public StringSearchField getAssignuser() {
		return assignuser;
	}

	public void setAssignuser(StringSearchField assignuser) {
		this.assignuser = assignuser;
	}

	public StringSearchField getLoguser() {
		return loguser;
	}

	public void setLoguser(StringSearchField loguser) {
		this.loguser = loguser;
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

	public SetSearchField<String> getResolutions() {
		return resolutions;
	}

	public void setResolutions(SetSearchField<String> resolutions) {
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

	public DateTimeSearchField getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(DateTimeSearchField updatedDate) {
		this.updatedDate = updatedDate;
	}

	public RangeDateTimeSearchField getUpdatedDateRange() {
		return updatedDateRange;
	}

	public void setUpdatedDateRange(RangeDateTimeSearchField updatedDateRange) {
		this.updatedDateRange = updatedDateRange;
	}

	public DateTimeSearchField getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTimeSearchField dueDate) {
		this.dueDate = dueDate;
	}

	public RangeDateTimeSearchField getDueDateRange() {
		return dueDateRange;
	}

	public void setDueDateRange(RangeDateTimeSearchField dueDateRange) {
		this.dueDateRange = dueDateRange;
	}

	public DateTimeSearchField getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(DateTimeSearchField resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public RangeDateTimeSearchField getResolvedDateRange() {
		return resolvedDateRange;
	}

	public void setResolvedDateRange(RangeDateTimeSearchField resolvedDateRange) {
		this.resolvedDateRange = resolvedDateRange;
	}

	public SetSearchField<String> getPriorities() {
		return priorities;
	}

	public void setPriorities(SetSearchField<String> priorities) {
		this.priorities = priorities;
	}

	public static String getAFFVERSION() {
		return AFFVERSION;
	}

	public static void setAFFVERSION(String aFFVERSION) {
		AFFVERSION = aFFVERSION;
	}

	public static String getFIXVERSION() {
		return FIXVERSION;
	}

	public static void setFIXVERSION(String fIXVERSION) {
		FIXVERSION = fIXVERSION;
	}

	public static String getCOMPONENT() {
		return COMPONENT;
	}

	public static void setCOMPONENT(String cOMPONENT) {
		COMPONENT = cOMPONENT;
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

	public void setProjectId(NumberSearchField projectId) {
		this.projectId = projectId;
	}

	public NumberSearchField getProjectId() {
		return projectId;
	}

	public void setDescription(StringSearchField description) {
		this.description = description;
	}

	public StringSearchField getDescription() {
		return description;
	}

	public SetSearchField<Integer> getMilestoneIds() {
		return milestoneIds;
	}

	public void setMilestoneIds(SetSearchField<Integer> milestoneIds) {
		this.milestoneIds = milestoneIds;
	}
}
