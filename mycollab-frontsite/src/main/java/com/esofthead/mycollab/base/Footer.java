package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.Panel;

import com.esofthead.mycollab.pages.PrivacyPage;
import com.esofthead.mycollab.pages.TermOfServicePage;

public class Footer extends Panel {

	private static final long serialVersionUID = 1L;

	public Footer(String id) {
		super(id);

		/*for (int i = 1; i <= 2; i++) {
			add(new BookmarkablePageLink<Void>("privacyLink" + i,
					PrivacyPage.class));
		}*/
		
		add(new BookmarkablePageLink<Void>("privacyLink2", PrivacyPage.class));
		
		BookmarkablePageLink<Void> signin = new BookmarkablePageLink<Void>("privacyLink1", TermOfServicePage.class);
		add(signin);

		add(new ExternalLink("mailLink", "mailto:support@esofthead.com"));
		add(new ExternalLink("twitterLink", "https://twitter.com/mycollab_vn"));
		this.setRenderBodyOnly(true);
	}
}
