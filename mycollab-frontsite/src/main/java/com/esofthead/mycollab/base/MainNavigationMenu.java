package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.esofthead.mycollab.pages.BlogPage;
import com.esofthead.mycollab.pages.HomePage;
import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.SignInPage;
import com.esofthead.mycollab.pages.TourPage;

public class MainNavigationMenu extends Panel {

	private static final long serialVersionUID = 1L;

	public MainNavigationMenu(String id) {
		super(id);

		BookmarkablePageLink<Void> homePageLink = new BookmarkablePageLink<Void>(
				"backHome", HomePage.class);
		add(homePageLink);
		BookmarkablePageLink<Void> signIn = new BookmarkablePageLink<Void>(
				"signIn", SignInPage.class);
		add(signIn);
		BookmarkablePageLink<Void> tour = new BookmarkablePageLink<Void>(
				"tour", TourPage.class);
		add(tour);
		BookmarkablePageLink<Void> blog = new BookmarkablePageLink<Void>(
				"blog", BlogPage.class);
		add(blog);
		ExternalLink support = new ExternalLink("support",
				"mailto:sales@mycollab.com");
		add(support);
		BookmarkablePageLink<Void> pricing = new BookmarkablePageLink<Void>(
				"pricing_signup", PricingPage.class);
		add(pricing);
		BookmarkablePageLink<Void> privacy = new BookmarkablePageLink<Void>(
				"privacy", PrivacyPage.class);
		add(privacy);

		this.setRenderBodyOnly(true);
	}
}
