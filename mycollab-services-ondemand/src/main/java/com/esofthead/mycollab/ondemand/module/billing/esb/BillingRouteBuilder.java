package com.esofthead.mycollab.ondemand.module.billing.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
@Component
@Profile("!test")
public class BillingRouteBuilder extends SpringRouteBuilder {

	@Autowired
	private ConvertPlanToFreeOneCommand convertPlanToFreeOneCommand;

	@Override
	public void configure() throws Exception {
		from(BillingEndPoints.CONVERT_PLAN_TO_FREE).setExchangePattern(
				ExchangePattern.InOnly).to("seda:convertFreePlan.queue");
		from("seda:convertFreePlan.queue")
				.threads()
				.bean(convertPlanToFreeOneCommand,
						"convertToFreePlan(com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners)");

	}

}
