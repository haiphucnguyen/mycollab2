package com.mycollab.ondemand.module.billing.service.ibatis;

import com.mycollab.ondemand.module.billing.domain.SimpleBillingSubscription;
import com.mycollab.ondemand.module.billing.service.BillingSubscriptionService;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@Service
public class BillingSubscriptionServiceImpl implements BillingSubscriptionService {
    @Override
    public SimpleBillingSubscription findSubscription(Integer sAccountId) {
        return null;
    }
}
