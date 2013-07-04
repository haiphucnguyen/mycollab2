package com.esofthead.mycollab.module.billing.service;

import java.util.List;

public interface BillingService {
	void registerAccount(String subdomain, int billingPlanId, String username,
			String password, String email, String timezoneId);

	List<String> getSubdomainsOfUser(String username);
}
