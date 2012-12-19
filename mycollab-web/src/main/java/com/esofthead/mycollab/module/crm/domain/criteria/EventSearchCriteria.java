package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateTimeSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class EventSearchCriteria extends SearchCriteria {

    private NumberSearchField saccountid;
    private StringSearchField subject;
    private StringSearchField relatedTo;
    private StringSearchField assignUser;
    private DateTimeSearchField startDate;
    private DateTimeSearchField endDate;

    public NumberSearchField getSaccountid() {
        return saccountid;
    }

    public void setSaccountid(NumberSearchField saccountid) {
        this.saccountid = saccountid;
    }

    public StringSearchField getSubject() {
        return subject;
    }

    public void setSubject(StringSearchField subject) {
        this.subject = subject;
    }

    public StringSearchField getRelatedTo() {
        return relatedTo;
    }

    public void setRelatedTo(StringSearchField relatedTo) {
        this.relatedTo = relatedTo;
    }

    public StringSearchField getAssignUser() {
        return assignUser;
    }

    public void setAssignUser(StringSearchField assignUser) {
        this.assignUser = assignUser;
    }

    public DateTimeSearchField getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTimeSearchField startDate) {
        this.startDate = startDate;
    }

    public DateTimeSearchField getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTimeSearchField endDate) {
        this.endDate = endDate;
    }
}
