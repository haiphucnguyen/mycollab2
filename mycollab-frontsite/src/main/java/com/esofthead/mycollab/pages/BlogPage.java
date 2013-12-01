package com.esofthead.mycollab.pages;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.base.BasePage;

public class BlogPage extends BasePage {
	private static final long serialVersionUID = 1L;

	public BlogPage(final PageParameters parameters) {
		super(parameters);
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>("MyCollab - Blog");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>(
				"Blog all MyCollab business news, development tips, sales offers.");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}
}
