package com.esofthead.mycollab.shell;

import com.esofthead.mycollab.module.billing.BillingPlanChecker;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.web.AppContext;

public class BillingPlanCheckerContext {
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

	public static boolean canCreateNewProject() {
		return BillingPlanChecker
				.canCreateNewProject(AppContext.getAccountId());
	}

	public static boolean canCreateNewUser() {
		return BillingPlanChecker.canCreateNewUser(AppContext.getAccountId());
	}

	public static boolean canUploadMoreFiles(long extraBytes) {
		return BillingPlanChecker.canUploadMoreFiles(AppContext.getAccountId(),
				extraBytes);
	}
}
