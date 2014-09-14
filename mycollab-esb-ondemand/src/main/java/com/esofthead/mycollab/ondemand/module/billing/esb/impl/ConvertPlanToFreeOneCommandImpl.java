package com.esofthead.mycollab.ondemand.module.billing.esb.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners;
import com.esofthead.mycollab.ondemand.module.billing.esb.ConvertPlanToFreeOneCommand;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
@Component
public class ConvertPlanToFreeOneCommandImpl implements
		ConvertPlanToFreeOneCommand {

	@Override
	public void convertToFreePlan(BillingAccountWithOwners billing) {
		// TODO Auto-generated method stub

	}

}
