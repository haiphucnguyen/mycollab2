package com.esofthead.mycollab.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BasePageHome extends WebPage {

	private static final long serialVersionUID = 1L;

	public BasePageHome(final PageParameters parameters) {
		super(parameters);
		createBaseComponents();
	}

	public BasePageHome() {
		createBaseComponents();
	}

	private void createBaseComponents() {
		MainNavigationMenu mainMenu = new MainNavigationMenu("mainMenu");
		add(mainMenu);
		
		FooterHome footer = new FooterHome("footer");
		add(footer);
	}

}
