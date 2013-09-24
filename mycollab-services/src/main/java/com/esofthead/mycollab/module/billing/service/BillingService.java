package com.esofthead.mycollab.module.billing.service;

import java.util.List;

import com.esofthead.mycollab.core.cache.CacheEvict;
import com.esofthead.mycollab.core.cache.CacheKey;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;

public interface BillingService extends IService {

	void registerAccount(String subdomain, int billingPlanId, String username,
			String password, String email, String timezoneId);

	@CacheEvict
	void cancelAccount(@CacheKey Integer accountid);

	@CacheEvict
	void updateBillingPlan(@CacheKey Integer accountid, int newBillingPlanId);

	List<String> getSubdomainsOfUser(String username);

	List<BillingPlan> getAvailablePlans();

	BillingPlan getFreeBillingPlan();
}
