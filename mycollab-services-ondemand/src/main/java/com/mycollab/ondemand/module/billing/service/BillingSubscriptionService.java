package com.mycollab.ondemand.module.billing.service;

import com.mycollab.db.persistence.service.IService;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BillingSubscriptionService extends IService {
    SimpleBillingSubscription findSubscription(Integer sAccountId);
}
