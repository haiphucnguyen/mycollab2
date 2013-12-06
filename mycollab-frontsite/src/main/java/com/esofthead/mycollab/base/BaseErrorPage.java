package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.esofthead.mycollab.pages.HomePage;

public class BaseErrorPage extends BasePage {

	private static final long serialVersionUID = 1L;

	public BaseErrorPage(PageParameters parameters) {
		super(parameters);

		add(new BookmarkablePageLink<Void>("back_to_home", HomePage.class));
	}

	public BaseErrorPage(Exception e) {
		super();

		add(new BookmarkablePageLink<Void>("back_to_home", HomePage.class));
	}

	@Override
	public IModel getPageTitle() {
		return new Model<String>("MyCollab - Error");
	}

	@Override
	public IModel getDescription() {
		return new Model<String>("MyCollab - Error Page");
	}

	@Override
	public IModel getKeywords() {
		return new Model<String>(
				"project management tool, business tools, crm system, online collaboration, cloud office, documents management, online office");
	}

}
