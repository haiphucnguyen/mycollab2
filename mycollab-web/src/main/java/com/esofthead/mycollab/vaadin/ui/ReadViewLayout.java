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

	public ReadViewLayout(final ThemeResource icon) {
		setSizeFull();
		setStyleName("readview-layout");
		
		if(icon!=null){
			header = new HorizontalLayout();
			header.setWidth("100%");
			header.setMargin(true, true, false, true);
			header.setStyleName("readview-layout-header");
			this.addComponent(header);

			iconEmbed = new Embedded();
			iconEmbed.setSource(icon);
			header.addComponent(iconEmbed);
			header.setComponentAlignment(iconEmbed, Alignment.MIDDLE_LEFT);

			titleLbl = new Label();
			titleLbl.setStyleName("h1");
			header.addComponent(titleLbl);
			header.setExpandRatio(titleLbl, 1.0f);
			header.setComponentAlignment(titleLbl, Alignment.MIDDLE_LEFT);
			body = new CssLayout();
			body.setStyleName("readview-layout-body");
			body.setSizeFull();
			this.addComponent(body);
			// this.setExpandRatio(body, 1.0f);
	
			viewTab = new DetachedTabs.Horizontal(body);
			viewTab.setSizeUndefined();
			header.addComponent(viewTab);
			header.setComponentAlignment(viewTab, Alignment.BOTTOM_CENTER);
		}else{
			header = null;
			iconEmbed = null;
			titleLbl = null;
			viewTab = null;
			
			body = new CssLayout();
			body.setStyleName("readview-layout-body");
			body.setSizeFull();
			this.addComponent(body);
		}
	}

	public void addControlButtons(final Component controlsBtn) {
		header.addComponent(controlsBtn);
		header.setComponentAlignment(controlsBtn, Alignment.MIDDLE_CENTER);
	}

	public void addTab(final Component content, final String button) {
		viewTab.addTab(content, new Button(button));
	}

	public void addTabChangedListener(
			final DetachedTabs.TabChangedListener listener) {
		viewTab.addTabChangedListener(listener);
	}

	public void selectTab(final String viewName) {
		viewTab.selectTab(viewName);
	}

	public void setTitle(final String title) {
		titleLbl.setValue(title);
	}
}
