package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		add(new Label("pagetitle", "Homepage"));

		for (int i = 1; i <= 4; i++) {
			add(new BookmarkablePageLink<Void>("tourLink" + i, TourPage.class));
		}

		add(new BookmarkablePageLink<Void>("pricingLink", PricingPage.class));
	}
}
