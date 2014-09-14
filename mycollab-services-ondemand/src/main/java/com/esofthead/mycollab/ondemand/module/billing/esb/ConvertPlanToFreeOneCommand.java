package com.esofthead.mycollab.ondemand.module.billing.esb;

import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
public interface ConvertPlanToFreeOneCommand {
	void convertToFreePlan(BillingAccountWithOwners billing);
}
