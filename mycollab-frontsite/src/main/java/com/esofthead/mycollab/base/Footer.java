package com.esofthead.mycollab.base;

import com.esofthead.mycollab.SiteConfiguration;
import com.esofthead.mycollab.uicomponents.ChatIFrame;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.esofthead.mycollab.pages.ContactUsPage;
import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.SignInPage;
import com.esofthead.mycollab.pages.TermOfServicePage;
import com.esofthead.mycollab.pages.TourPage;

public class Footer extends Panel {

	private static final long serialVersionUID = 1L;

	public Footer(final String id) {
		super(id);

        final ChatIFrame chatFrame = new ChatIFrame("livechat-frame", SiteConfiguration.getChatUrl());
        this.add(chatFrame);

		final BookmarkablePageLink<Void> tour = new BookmarkablePageLink<Void>(
				"tourlink", TourPage.class);
		this.add(tour);
		final BookmarkablePageLink<Void> pricing = new BookmarkablePageLink<Void>(
				"pricinglink", PricingPage.class);
		this.add(pricing);
		final ExternalLink support = new ExternalLink("supportlink",
				"mailto:sales@mycollab.com");
		this.add(support);
		final BookmarkablePageLink<Void> contact = new BookmarkablePageLink<Void>(
				"contactlink", ContactUsPage.class);
		this.add(contact);
		final BookmarkablePageLink<Void> privacy = new BookmarkablePageLink<Void>(
				"privacylink", PrivacyPage.class);
		this.add(privacy);
		final BookmarkablePageLink<Void> signIn = new BookmarkablePageLink<Void>(
				"signInlink", SignInPage.class);
		this.add(signIn);
		final BookmarkablePageLink<Void> terms = new BookmarkablePageLink<Void>(
				"termslink", TermOfServicePage.class);
		this.add(terms);

		this.setRenderBodyOnly(true);
	}
}
