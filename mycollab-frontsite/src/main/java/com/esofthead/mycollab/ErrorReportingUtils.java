package com.esofthead.mycollab;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.restlet.resource.ClientResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.rest.server.resource.ErrorReportingResource;
import com.esofthead.mycollab.rest.server.resource.UserHubResource;

public class ErrorReportingUtils {
	private static Logger log = LoggerFactory
			.getLogger(ErrorReportingUtils.class);

	public static void reportError(String errorMsg) {
		try {
			final ClientResource clientResource = new ClientResource(
					SiteConfiguration.getErrorReportingUrl());
			final ErrorReportingResource errorResource = clientResource
					.wrap(ErrorReportingResource.class);
			errorResource.sendErrorTrace(errorMsg);
		} catch (Exception e) {
			log.error("Error while sending error to server", e);
			log.error("The base error is " + errorMsg);
		}
	}

	public static void reportError(Throwable e) {
		StringWriter writer = new StringWriter();
		e.printStackTrace(new PrintWriter(writer));
		reportError(writer.toString());
	}

	public static void main(String[] args) {
		final ClientResource clientResource = new ClientResource(
				"https://esofthead.mycollab.com/api/signin");
		final UserHubResource userResource = clientResource
				.wrap(UserHubResource.class);
		System.out.println(userResource
				.getSubdomainsOfUser("hainguyen@esofthead.com").length);

	}
}
