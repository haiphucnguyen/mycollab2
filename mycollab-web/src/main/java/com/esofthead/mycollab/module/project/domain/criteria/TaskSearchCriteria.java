package com.esofthead.mycollab.module.project.domain.criteria;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;

public class TaskSearchCriteria extends SearchCriteria {
    private BooleanSearchField iscompleted;
    private BooleanSearchField ismilestone;
    private NumberSearchField projectid;
    private DateTimeSearchField greaterThan;
    private DateTimeSearchField lessThan;
    private NumberSearchField taskListId;
    

    public BooleanSearchField getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(BooleanSearchField iscompleted) {
        this.iscompleted = iscompleted;
    }

    public BooleanSearchField getIsmilestone() {
        return ismilestone;
    }

    public void setIsmilestone(BooleanSearchField ismilestone) {
        this.ismilestone = ismilestone;
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
}
