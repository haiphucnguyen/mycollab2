package com.mycollab.ondemand.module.support.domain.criteria;

import com.mycollab.db.arguments.DateSearchField;
import com.mycollab.db.arguments.RangeDateSearchField;
import com.mycollab.db.arguments.SearchCriteria;
import com.mycollab.db.arguments.SetSearchField;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public class BillingAccountSearchCriteria extends SearchCriteria {
    private DateSearchField lastAccessTime;

    private SetSearchField<String> statuses;

    private RangeDateSearchField registerTimeDuration;

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

    public RangeDateSearchField getRegisterTimeDuration() {
        return registerTimeDuration;
    }

    public void setRegisterTimeDuration(RangeDateSearchField registerTimeDuration) {
        this.registerTimeDuration = registerTimeDuration;
    }
}
