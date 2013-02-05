package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class TaskSearchCriteria extends SearchCriteria {
    private NumberSearchField projectid;
    private DateTimeSearchField greaterThan;
    private DateTimeSearchField lessThan;
    private NumberSearchField taskListId;
    private NumberSearchField id;
    
    private StringSearchField status;

    public StringSearchField getStatus() {
        return status;
    }

    public void setStatus(StringSearchField status) {
        this.status = status;
    }

    public NumberSearchField getProjectid() {
        return projectid;
    }

    public void setProjectid(NumberSearchField projectid) {
        this.projectid = projectid;
    }

    public DateTimeSearchField getLessThan() {
        return lessThan;
    }

    public void setLessThan(DateTimeSearchField lessThan) {
        this.lessThan = lessThan;
    }

    public DateTimeSearchField getGreaterThan() {
        return greaterThan;
    }

    public void setGreaterThan(DateTimeSearchField greaterThan) {
        this.greaterThan = greaterThan;
    }

    public NumberSearchField getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(NumberSearchField taskListId) {
        this.taskListId = taskListId;
    }

	public void setId(NumberSearchField id) {
		this.id = id;
	}

	public NumberSearchField getId() {
		return id;
	}
}
