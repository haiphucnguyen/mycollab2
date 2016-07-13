package com.mycollab.ondemand.module.billing.domain;

import com.mycollab.core.arguments.NotBindable;
import com.mycollab.module.user.domain.BillingPlan;

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
}
