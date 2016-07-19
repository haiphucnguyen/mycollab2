package com.mycollab.ondemand.module.billing.service;

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.mycollab.core.cache.CacheEvict;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.core.cache.Cacheable;
import com.mycollab.db.arguments.BasicSearchRequest;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.user.domain.BillingAccountWithOwners;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.ondemand.module.billing.domain.SimpleBillingAccount2;
import com.mycollab.ondemand.module.billing.domain.criteria.BillingAccountSearchCriteria;

import java.util.List;

/**
 * @author MyCollab Ltd
 * @since 5.2.8
 */
public interface BillingService extends IService {
    List<SimpleBillingAccount2> findPageableListByCriteria(BasicSearchRequest<BillingAccountSearchCriteria> searchRequest);

    void registerAccount(String subDomain, int billingPlanId, String username,
                         String password, String email, String timezoneId,
                         boolean isEmailVerified);

    void cancelAccount(Integer accountId, CustomerFeedbackWithBLOBs feedback);

    List<BillingPlan> getAvailablePlans();

    @CacheEvict
    void updateBillingPlan(@CacheKey Integer accountId, BillingPlan oldPlan, BillingPlan newPlan);

    List<String> getSubDomainsOfUser(String username);

    List<BillingAccountWithOwners> getTrialAccountsWithOwners();

    @Cacheable
    BillingPlan findBillingPlan(@CacheKey Integer sAccountId);
}
