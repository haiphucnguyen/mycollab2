package com.esofthead.mycollab.base;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import com.esofthead.mycollab.pages.HomePage;
import com.esofthead.mycollab.pages.PricingPage;
import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.TourPage;

public class MainNavigationMenu extends Panel {

	private static final long serialVersionUID = 1L;

	public MainNavigationMenu(String id) {
		super(id);

		List<MarkupContainer> menus = createMenu();

		ListView<MarkupContainer> listView = new ListView<MarkupContainer>(
				"mainNav", menus) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<MarkupContainer> listItem) {
				final MarkupContainer link = listItem.getModelObject();
				listItem.add(link);
			}
			
			
		};

		add(listView);

		BookmarkablePageLink<Void> homePageLink = new BookmarkablePageLink<Void>(
				"backHome", HomePage.class);
		add(homePageLink);

		ExternalLink signInLink = new ExternalLink("signIn",
				"http://app.mycollab.com", "Sign In");
		add(signInLink);
		
		

		this.setRenderBodyOnly(true);
	}

	private List<MarkupContainer> createMenu() {
		List<MarkupContainer> menus = new ArrayList<MarkupContainer>();

		menus.add(new BookmarkablePageLink<Void>("mainNavItem", TourPage.class)
				.add(new Label("itemLabel", "Tour")));
		menus.add(new ExternalLink("mainNavItem", "mailto:sales@esofthead.com")
				.add(new Label("itemLabel", "Support")));
		menus.add(new BookmarkablePageLink<Void>("mainNavItem",
				PricingPage.class).add(new Label("itemLabel",
				"Pricing & Signup")));
		menus.add(new BookmarkablePageLink<Void>("mainNavItem",
				PrivacyPage.class).add(new Label("itemLabel", "Privacy")));

		return menus;
	}
}
