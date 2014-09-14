package com.esofthead.mycollab.ondemand.module.billing.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

/**
 * 
 * @author MyCollab Ltd.
 * @since 4.5.1
 *
 */
@Component
public class BillingRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from(BillingEndPoints.CONVERT_PLAN_TO_FREE).setExchangePattern(
				ExchangePattern.InOnly).to("seda:convertFreePlan.queue");
		from("seda:convertFreePlan.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getSpringBean(ConvertPlanToFreeOneCommand.class),
						"convertToFreePlan(com.esofthead.mycollab.module.user.domain.BillingAccountWithOwners)");

	}

}
