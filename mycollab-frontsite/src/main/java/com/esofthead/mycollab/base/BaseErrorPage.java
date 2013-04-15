package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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

}
