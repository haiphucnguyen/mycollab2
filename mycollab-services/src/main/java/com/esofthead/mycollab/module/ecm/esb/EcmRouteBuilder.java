package com.esofthead.mycollab.module.ecm.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class EcmRouteBuilder extends SpringRouteBuilder {
	private static Logger log = LoggerFactory.getLogger(EcmRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		log.debug("Configure content save route");
		from(EcmEndPoints.SAVE_CONTENT_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:saveContent.queue");
		from("seda:saveContent.queue")
				.threads()
				.bean(ApplicationContextUtil.getSpringBean(SaveContentCommand.class),
						"saveContent(com.esofthead.mycollab.module.ecm.domain.Content, int, int)");

		log.debug("Configure contents deleted route");
		from(EcmEndPoints.DELETE_RESOURCES_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:deleteResources.queue");
		from("seda:deleteResources.queue").threads().bean(
				ApplicationContextUtil.getSpringBean(DeleteResourcesCommand.class),
				"removeResource(String, int, int)");

	}

}
