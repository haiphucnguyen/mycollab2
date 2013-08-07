package com.esofthead.mycollab.module.user.domain;

public class SimpleBillingAccount extends BillingAccount {
	private static final long serialVersionUID = 1L;
	
	private BillingPlan billingPlan;

	public BillingPlan getBillingPlan() {
		return billingPlan;
	}

	public void setBillingPlan(BillingPlan billingPlan) {
		this.billingPlan = billingPlan;
	}
}
