package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private HorizontalSplitPanel root;
	private DetachedTabs myProjectTab;
	private CssLayout mySpaceArea = new CssLayout();

	public ProjectViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);
		
		HorizontalLayout topPanel = new HorizontalLayout();
		topPanel.addComponent(new Button("Home"));
		topPanel.addComponent(new Button("Project"));
		
		this.addComponent(topPanel);
		
		root = new HorizontalSplitPanel();
		root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		root.setStyleName("menuContent");
		root.setWidth("100%");

		mySpaceArea.setWidth("100%");
		myProjectTab = new DetachedTabs.Vertical(mySpaceArea);
		myProjectTab.setSizeFull();
		myProjectTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(myProjectTab);

		root.setFirstComponent(menu);

		buildComponents();
		this.addComponent(root);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Messages");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Milestones");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Tasks");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Bugs");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Calendar");
	}

	private com.vaadin.ui.Component constructProjectDashboardComponent() {
		return new ProjectDashboardViewImpl();
	}
}
