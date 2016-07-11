package com.mycollab.premium.module.billing.service.ibatis;

import com.mycollab.common.domain.CustomerFeedbackWithBLOBs;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.UnsupportedFeatureException;
import com.mycollab.core.cache.CacheKey;
import com.mycollab.module.billing.service.BillingService;
import com.mycollab.module.user.dao.*;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountWithOwners;
import com.mycollab.module.user.domain.BillingPlan;
import com.mycollab.module.user.domain.BillingPlanExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "billingService")
public class BillingServiceImpl implements BillingService {

    @Autowired
    private BillingPlanMapper billingPlanMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

    @Override
    @Transactional
    public void registerAccount(final String subDomain, final int billingPlanId, final String username,
                                final String password, final String email, final String timezoneId, boolean isEmailVerified) {
        throw new UnsupportedFeatureException("This feature is not supported except onsite mode");
    }

    @Override
    public List<String> getSubDomainsOfUser(final String username) {
        throw new UnsupportedFeatureException("This feature is not supported except onsite mode");
    }

    @Override
    public List<BillingPlan> getAvailablePlans() {
        throw new UnsupportedFeatureException("This feature is not supported except onsite mode");
    }

    @Override
    public void updateBillingPlan(Integer accountId, int newBillingPlanId) {
        throw new UnsupportedFeatureException(
                "This feature is not supported except onsite mode");
    }

    @Override
    public void cancelAccount(Integer accountId, CustomerFeedbackWithBLOBs feedback) {
        throw new UnsupportedFeatureException("This feature is not supported except onsite mode");
    }

    @Override
    public BillingPlan getFreeBillingPlan() {
        BillingPlanExample ex = new BillingPlanExample();
        ex.createCriteria().andBillingtypeEqualTo("Free");
        List<BillingPlan> billingPlans = billingPlanMapper.selectByExample(ex);
        if (CollectionUtils.isNotEmpty(billingPlans)) {
            return billingPlans.get(0);
        } else {
            throw new MyCollabException("Can not query free billing plan");
        }
    }

    @Override
    public BillingPlan findBillingPlan(@CacheKey Integer sAccountId) {
        BillingAccount billingAccount = billingAccountMapper.selectByPrimaryKey(sAccountId);
        if (billingAccount != null) {
            Integer billingplanid = billingAccount.getBillingplanid();
            return billingPlanMapper.selectByPrimaryKey(billingplanid);
        }
        return null;
    }

    @Override
    public List<BillingAccountWithOwners> getTrialAccountsWithOwners() {
        return null;
    }

}