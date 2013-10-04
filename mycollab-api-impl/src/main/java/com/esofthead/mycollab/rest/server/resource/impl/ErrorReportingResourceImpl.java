package com.esofthead.mycollab.rest.server.resource.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.rest.server.resource.ErrorReportingResource;

@Component
public class ErrorReportingResourceImpl implements ErrorReportingResource {

	private static Logger log = LoggerFactory
			.getLogger(ErrorReportingResourceImpl.class);

	@Override
	public void sendErrorTrace(String errorMsg) {
		log.error(errorMsg);
	}

}
