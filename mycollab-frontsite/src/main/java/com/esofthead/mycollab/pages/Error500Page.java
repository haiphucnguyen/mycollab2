package com.esofthead.mycollab.pages;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.wicket.markup.html.basic.Label;
import org.restlet.resource.ClientResource;

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.base.BaseErrorPage;
import com.esofthead.mycollab.rest.server.resource.ErrorReportingResource;

public class Error500Page extends BaseErrorPage {

	private static final long serialVersionUID = 1L;

	public Error500Page(Exception e) {
		super(e);

		add(new Label("pagetitle", "Internal Error"));

		add(new Label("error_code", "500"));
		add(new Label("error_brief", "Oops, there is an error."));

		// Send error report to server
		try {
			final ClientResource clientResource = new ClientResource(
					SiteConfiguration.getErrorReportingUrl());
			final ErrorReportingResource errorResource = clientResource
					.wrap(ErrorReportingResource.class);
			StringWriter writer = new StringWriter();
			e.printStackTrace(new PrintWriter(writer));
			errorResource.sendErrorTrace(writer.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
