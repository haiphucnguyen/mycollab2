package com.esofthead.mycollab.vaadin.ui;

import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

public class ReadViewLayout extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private HorizontalLayout header;
	private final Embedded iconEmbed;
	private Label titleLbl;
	private DetachedTabs viewTab;
	private CssLayout body;

	public ReadViewLayout(ThemeResource icon) {
		this.setSizeFull();
		header = new HorizontalLayout();
		this.addComponent(header);

		iconEmbed = new Embedded();
		iconEmbed.setSource(icon);
		header.addComponent(iconEmbed);

		titleLbl = new Label();
		header.addComponent(titleLbl);

		body = new CssLayout();
		body.setSizeFull();
		this.addComponent(body);
		this.setExpandRatio(body, 1.0f);

		viewTab = new DetachedTabs.Horizontal(body);
		header.addComponent(viewTab);
	}

	public void addControlButtons(Component controlsBtn) {
		header.addComponent(controlsBtn);
	}

	public void setTitle(String title) {
		titleLbl.setCaption(title);
	}

	public void addTab(Component content, String button) {
		viewTab.addTab(content, button);
	}

	public void selectTab(String viewName) {
		viewTab.selectTab(viewName);
	}

	public void addTabChangedListener(DetachedTabs.TabChangedListener listener) {
		viewTab.addTabChangedListener(listener);
	}
}
