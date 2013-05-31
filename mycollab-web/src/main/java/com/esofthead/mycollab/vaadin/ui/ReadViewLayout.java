package com.esofthead.mycollab.vaadin.ui;

import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;

public class ReadViewLayout extends CssLayout {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout header;
	private final Embedded iconEmbed;
	private final Label titleLbl;
	private final DetachedTabs viewTab;
	private final CssLayout body;

	public ReadViewLayout(ThemeResource icon) {
		setSizeFull();
		setStyleName("readview-layout");

		header = new HorizontalLayout();
		header.setWidth("100%");
		header.setMargin(true, true, false, true);
		header.setStyleName("readview-layout-header");
		this.addComponent(header);

		iconEmbed = new Embedded();
		iconEmbed.setSource(icon);
		header.addComponent(iconEmbed);

		titleLbl = new Label();
		titleLbl.setStyleName("h1");
		header.addComponent(titleLbl);
		header.setExpandRatio(titleLbl, 1.0f);

		body = new CssLayout();
		body.setStyleName("readview-layout-body");
		body.setSizeFull();
		this.addComponent(body);
		// this.setExpandRatio(body, 1.0f);

		viewTab = new DetachedTabs.Horizontal(body);
		viewTab.setSizeUndefined();
		header.addComponent(viewTab);
		header.setComponentAlignment(viewTab, Alignment.BOTTOM_CENTER);
	}

	public void addControlButtons(Component controlsBtn) {
		header.addComponent(controlsBtn);
	}

	public void addTab(Component content, String button) {
		viewTab.addTab(content, new Button(button));
	}

	public void addTabChangedListener(DetachedTabs.TabChangedListener listener) {
		viewTab.addTabChangedListener(listener);
	}

	public void selectTab(String viewName) {
		viewTab.selectTab(viewName);
	}

	public void setTitle(String title) {
		titleLbl.setValue(title);
	}
}
