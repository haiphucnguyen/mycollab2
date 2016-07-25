package com.mycollab.ondemand.module.billing.dao;

import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import org.apache.ibatis.annotations.Param;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
public interface BillingSubscriptionMapperExt {
    SimpleBillingSubscription findSubscription(Integer sAccountId);

    BillingSubscriptionHistory getTheLastBillingSuccess(@Param("sAccountId") Integer sAccountId);
}
