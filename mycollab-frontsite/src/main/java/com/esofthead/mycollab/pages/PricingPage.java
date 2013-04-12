package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class PricingPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public PricingPage(PageParameters parameters) {
		super(parameters);

		add(new Label("pagetitle", "Pricing & Sign Up"));

		add(new BookmarkablePageLink<Void>("micro-link", SignUpPage.class,
				new PageParameters().add("planId", "2")));
		add(new BookmarkablePageLink<Void>("compact-link", SignUpPage.class,
				new PageParameters().add("planId", "1")));
		add(new BookmarkablePageLink<Void>("corporate-link", SignUpPage.class,
				new PageParameters().add("planId", "3")));
		add(new BookmarkablePageLink<Void>("enterprise-link", SignUpPage.class,
				new PageParameters().add("planId", "4")));

	}

}
