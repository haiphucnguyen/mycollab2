package com.esofthead.mycollab.module.billing.service;

public interface BillingService {
	void registerAccount(String subdomain, int billingPlanId, String username,
			String password, String email, String timezoneId);
}
