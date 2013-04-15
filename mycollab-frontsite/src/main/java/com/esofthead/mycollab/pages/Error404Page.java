package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BaseErrorPage;

public class Error404Page extends BaseErrorPage {

	private static final long serialVersionUID = 1L;

	public Error404Page(PageParameters parameters) {
		super(parameters);

		add(new Label("pagetitle", "Page Not Found!"));

		add(new Label("error_code", "404"));
		add(new Label("error_brief", "Page not found, sorry"));
	}
}
