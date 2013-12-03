package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class HomePage extends BasePage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		for (int i = 1; i <= 4; i++) {
			add(new BookmarkablePageLink<Void>("tourLink" + i, TourPage.class));
		}

		add(new BookmarkablePageLink<Void>("pricingLink", PricingPage.class));
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>(
				"MyCollab - Cloud CRM, Project, Document Management and more tools");
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
