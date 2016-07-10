package com.mycollab.ondemand.module.billing.dao;

import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BillingSubscriptionMapperExt {
    SimpleBillingSubscription findSubscription(Integer sAccountId);
}
