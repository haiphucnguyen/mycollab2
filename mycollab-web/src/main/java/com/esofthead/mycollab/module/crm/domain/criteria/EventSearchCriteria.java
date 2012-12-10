package com.esofthead.mycollab.module.crm.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.StringSearchField;

public class EventSearchCriteria extends SearchCriteria {

    private NumberSearchField saccountid;
    private StringSearchField subject;
    private StringSearchField relatedTo;
    private StringSearchField assignUser;
    private DateSearchField startDate;
    private DateSearchField endDate;

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

    public DateSearchField getStartDate() {
        return startDate;
    }

    public void setStartDate(DateSearchField startDate) {
        this.startDate = startDate;
    }

    public DateSearchField getEndDate() {
        return endDate;
    }

    public void setEndDate(DateSearchField endDate) {
        this.endDate = endDate;
    }
}
