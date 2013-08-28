package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.ErrorReportingUtils;
import com.esofthead.mycollab.base.BaseErrorPage;

public class Error500Page extends BaseErrorPage {

	private static final long serialVersionUID = 1L;

	public Error500Page(PageParameters parameters) {
		super(parameters);
	}

	public Error500Page(Exception e) {
		super(e);

//		add(new Label("pagetitle", "Internal Error"));

		add(new Label("error_code", "500"));
		add(new Label("error_brief", "Oops, there is an error."));

		// Send error report to server
		ErrorReportingUtils.reportError(e);
	}

}
