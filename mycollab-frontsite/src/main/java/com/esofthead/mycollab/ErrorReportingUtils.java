package com.esofthead.mycollab;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.rest.client.RemoteServiceProxy;
import com.esofthead.mycollab.rest.server.resource.ErrorReportingResource;

public class ErrorReportingUtils {
	private static Logger log = LoggerFactory
			.getLogger(ErrorReportingUtils.class);

	public static void reportError(String errorMsg) {
		try {
			ErrorReportingResource errorResource = RemoteServiceProxy
					.build(SiteConfiguration.getApiUrl(),
							ErrorReportingResource.class);

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
}
