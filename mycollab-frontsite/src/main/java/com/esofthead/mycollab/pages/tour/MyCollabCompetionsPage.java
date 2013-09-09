package com.esofthead.mycollab.pages.tour;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.pages.TourPage;
import com.esofthead.mycollab.pages.tour.CRMPage;
import com.esofthead.mycollab.pages.tour.CollaborationPage;
import com.esofthead.mycollab.pages.tour.DocumentManagementPage;
import com.esofthead.mycollab.pages.tour.OnlineOfficeAppsPage;
import com.esofthead.mycollab.pages.tour.ProjectManagementPage;

public class MyCollabCompetionsPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public MyCollabCompetionsPage(PageParameters parameters) {
		super(parameters);
		add(new Label("pagetitle", "Tour"));
		
		BookmarkablePageLink<Void> highlight = new BookmarkablePageLink<Void>("highlightLink", TourPage.class);
		add(highlight);
//		BookmarkablePageLink<Void> officeApp = new BookmarkablePageLink<Void>("officeAppLink", OnlineOfficeAppsPage.class);
//		add(officeApp);
		BookmarkablePageLink<Void> document = new BookmarkablePageLink<Void>("documentLink", DocumentManagementPage.class);
		add(document);
		BookmarkablePageLink<Void> project = new BookmarkablePageLink<Void>("projectLink", ProjectManagementPage.class);
		add(project);
		BookmarkablePageLink<Void> crm = new BookmarkablePageLink<Void>("crmLink", CRMPage.class);
		add(crm);
		BookmarkablePageLink<Void> collaboration = new BookmarkablePageLink<Void>("collaborationLink", CollaborationPage.class);
		add(collaboration);
//		BookmarkablePageLink<Void> competion = new BookmarkablePageLink<Void>("competionsLink", MyCollabCompetionsPage.class);
//		add(competion);
	}

}
