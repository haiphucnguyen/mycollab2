package com.esofthead.mycollab.module.billing.service;

import java.util.List;

import com.esofthead.mycollab.module.user.domain.BillingPlan;

public interface BillingService {
	void registerAccount(String subdomain, int billingPlanId, String username,
			String password, String email, String timezoneId);

	void cancelAccount(int accountid);

	void updateBillingPlan(int accountid, int newBillingPlanId);

	List<String> getSubdomainsOfUser(String username);

	List<BillingPlan> getAvailablePlans();
}
