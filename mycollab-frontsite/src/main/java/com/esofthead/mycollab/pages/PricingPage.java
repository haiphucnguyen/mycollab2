package com.esofthead.mycollab.pages;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class PricingPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public PricingPage(final PageParameters parameters) {
		super(parameters);

		this.add(new BookmarkablePageLink<Void>("free-link", SignUpPage.class,
				new PageParameters().add("planId", "1")));
		this.add(new BookmarkablePageLink<Void>("micro-link", SignUpPage.class,
				new PageParameters().add("planId", "2")));
		this.add(new BookmarkablePageLink<Void>("compact-link",
				SignUpPage.class, new PageParameters().add("planId", "3")));
		this.add(new BookmarkablePageLink<Void>("corporate-link",
				SignUpPage.class, new PageParameters().add("planId", "4")));
		this.add(new BookmarkablePageLink<Void>("enterprise-link",
				SignUpPage.class, new PageParameters().add("planId", "5")));

	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>("Pricing - Online Project Management, CRM, Document Management");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"MyCollab offers various options of pricing to meet customer needs");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}
}
