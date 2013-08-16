package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class BlogPage extends BasePage {
	private static final long serialVersionUID = 1L;

	public BlogPage(final PageParameters parameters) {
		super(parameters);

		add(new Label("pagetitle", "Blog"));

	}
}
