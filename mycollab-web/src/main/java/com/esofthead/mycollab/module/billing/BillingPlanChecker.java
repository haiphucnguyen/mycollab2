package com.esofthead.mycollab.module.billing;

import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.web.AppContext;

public class BillingPlanChecker {
	public static boolean isBugComponentEnable() {
		SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
		return (billingAccount == null) ? false : billingAccount
				.getBillingPlan().getHasbugenable();
	}

	public static boolean isStandupComponentEnable() {
		SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
		return (billingAccount == null) ? false : billingAccount
				.getBillingPlan().getHasstandupmeetingenable();
	}
}
