package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public BasePage(final PageParameters parameters) {
		super(parameters);

		MainNavigationMenu mainMenu = new MainNavigationMenu("mainMenu");
		add(mainMenu);

		Footer footer = new Footer("footer");
		add(footer);
	}
}
