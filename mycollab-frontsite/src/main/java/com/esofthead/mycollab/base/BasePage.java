package com.esofthead.mycollab.base;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class BasePage extends WebPage {

	private static final long serialVersionUID = 1L;

	public BasePage(final PageParameters parameters) {
		super(parameters);
		createBaseComponents();
	}

	public BasePage() {
		createBaseComponents();
	}

	private void createBaseComponents() {
		MainNavigationMenu mainMenu = new MainNavigationMenu("mainMenu");
		add(mainMenu);

		Footer footer = new Footer("footer");
		add(footer);
	}

	@Override
	protected void onBeforeRender() {
		// add the <title> tag
		addOrReplace(new Label("title", getPageTitle()));

		Label desc = new Label("description", "");
		desc.add(new AttributeAppender("content", getDescription(), " "));
		addOrReplace(desc);

		Label keywords = new Label("keywords", "");
		keywords.add(new AttributeAppender("content", getKeywords(), " "));
		addOrReplace(keywords);

		super.onBeforeRender();
	}

	public abstract IModel getPageTitle();

	public abstract IModel getDescription();

	public abstract IModel getKeywords();
}
