package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.SignInPage;
import com.esofthead.mycollab.pages.TermOfServicePage;
import com.esofthead.mycollab.pages.TourPage;

public class Footer extends Panel {

	private static final long serialVersionUID = 1L;

	public Footer(String id) {
		super(id);
		
		BookmarkablePageLink<Void> tour = new BookmarkablePageLink<Void>("tourlink", TourPage.class);
		add(tour);
		BookmarkablePageLink<Void> pricing = new BookmarkablePageLink<Void>("pricinglink", PricingPage.class);
		add(pricing);
		ExternalLink support = new ExternalLink("supportlink", "mailto:sales@mycollab.com");
		add(support);
		ExternalLink contact = new ExternalLink("contactlink", "mailto:sales@mycollab.com");
		add(contact);
		BookmarkablePageLink<Void> privacy = new BookmarkablePageLink<Void>("privacylink", PrivacyPage.class);
		add(privacy);
		BookmarkablePageLink<Void> signIn = new BookmarkablePageLink<Void>("signInlink", SignInPage.class);
		add(signIn);
		BookmarkablePageLink<Void> terms = new BookmarkablePageLink<Void>("termslink", TermOfServicePage.class);
		add(terms);
		
		this.setRenderBodyOnly(true);
	}
}
