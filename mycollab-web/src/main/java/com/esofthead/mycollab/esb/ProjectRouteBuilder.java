package com.esofthead.mycollab.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.esb.handler.ProjectDeleteListener;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class ProjectRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		from(EndpointConstants.PROJECT_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:projectDelete.queue");
		from("seda:projectDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(ProjectDeleteListener.class),
				"projectRemoved(int)");
	}

}
