package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.pages.tour.CRMPage;
import com.esofthead.mycollab.pages.tour.DocumentManagementPage;
import com.esofthead.mycollab.pages.tour.ProjectManagementPage;
import com.esofthead.mycollab.pages.tour.WhoisitForPage;

public class TourPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public TourPage(PageParameters parameters) {
		super(parameters);
		add(new Label("pagetitle", "Highlights"));

		BookmarkablePageLink<Void> whoisitfor = new BookmarkablePageLink<Void>(
				"whoisitforLink", WhoisitForPage.class);
		add(whoisitfor);
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
	}

}
