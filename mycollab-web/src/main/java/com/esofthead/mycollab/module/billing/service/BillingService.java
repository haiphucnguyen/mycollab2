package com.esofthead.mycollab.module.billing.service;

public interface BillingService {
	void registerAccount(int billingPlanId, String username, String password, String email,
			String timezoneId);
}
