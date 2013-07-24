package com.esofthead.mycollab.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.esb.handler.UserDeleteListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class UserRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from(EndpointConstants.USER_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:userDelete.queue");
		from("seda:userDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(UserDeleteListener.class),
				"userRemoved(String, int)");

	}

}
