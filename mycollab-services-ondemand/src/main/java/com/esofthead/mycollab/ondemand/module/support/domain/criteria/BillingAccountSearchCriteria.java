package com.esofthead.mycollab.ondemand.module.support.domain.criteria;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.SearchCriteria;
import com.esofthead.mycollab.core.arguments.SetSearchField;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class BillingAccountSearchCriteria extends SearchCriteria {
    private DateSearchField lastAccessTime;

    private SetSearchField<String> statuses;

    public DateSearchField getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(DateSearchField lastAccessTime) {
        this.lastAccessTime = lastAccessTime;
    }

    public SetSearchField<String> getStatuses() {
        return statuses;
    }

    public void setStatuses(SetSearchField<String> statuses) {
        this.statuses = statuses;
    }
}
