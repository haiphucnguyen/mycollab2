package com.mycollab.ondemand.module.billing.domain;

import com.mycollab.core.arguments.NotBindable;
import com.mycollab.module.user.domain.BillingPlan;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public class SimpleBillingSubscription extends BillingSubscription {
    private Date expireDate;

    @NotBindable
    private BillingPlan billingPlan;

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public BillingPlan getBillingPlan() {
        return billingPlan;
    }

    public void setBillingPlan(BillingPlan billingPlan) {
        this.billingPlan = billingPlan;
    }

    public Boolean canAccess() {
        return (expireDate != null) ? new DateTime(expireDate).isAfter(new DateTime().minus(7)) : false;
    }

    public Boolean isValid() {
        return (expireDate != null) ? new DateTime(expireDate).isAfter(new DateTime()) : false;
    }
}
