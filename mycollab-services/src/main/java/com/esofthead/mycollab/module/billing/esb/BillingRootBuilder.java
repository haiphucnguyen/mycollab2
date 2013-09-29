package com.esofthead.mycollab.module.billing.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class BillingRootBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from(BillingEndpoints.ACCOUNT_DELETED_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:accountDelete.queue");
		from("seda:accountDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getSpringBean(AccountDeletedCommand.class),
						"accountDeleted(int)");

	}

}
