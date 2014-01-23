package com.esofthead.mycollab.pages.tour;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;
import com.esofthead.mycollab.pages.TourPage;

public class ProjectManagementPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public ProjectManagementPage(PageParameters parameters) {
		super(parameters);

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

	@Override
	public IModel getPageTitle() {
		return new Model<String>("Project Management Features - Online Project Management, CRM, Document Management");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"MyCollab is a secure cloud office that enables you to manage documents, projects, bug and issue tracking, milestones, time tracking, team and customer relations and more in one place.");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}

}
