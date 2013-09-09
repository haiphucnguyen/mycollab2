package com.esofthead.mycollab.pages.tour;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.pages.TourPage;

public class CRMPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public CRMPage(PageParameters parameters) {
		super(parameters);
		add(new Label("pagetitle", "Tour"));

		BookmarkablePageLink<Void> highlight = new BookmarkablePageLink<Void>(
				"highlightLink", TourPage.class);
		add(highlight);
		BookmarkablePageLink<Void> document = new BookmarkablePageLink<Void>(
				"documentLink", DocumentManagementPage.class);
		add(document);
		BookmarkablePageLink<Void> project = new BookmarkablePageLink<Void>(
				"projectLink", ProjectManagementPage.class);
		add(project);
		BookmarkablePageLink<Void> crm = new BookmarkablePageLink<Void>(
				"crmLink", CRMPage.class);
		add(crm);
		BookmarkablePageLink<Void> collaboration = new BookmarkablePageLink<Void>(
				"collaborationLink", CollaborationPage.class);
		add(collaboration);
		BookmarkablePageLink<Void> team = new BookmarkablePageLink<Void>(
				"teamLink", TeamManagementPage.class);
		add(team);
	}

}
