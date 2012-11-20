package com.esofthead.mycollab.module.project.ui;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private HorizontalSplitPanel root;
	private DetachedTabs myProjectTab;
	private CssLayout mySpaceArea = new CssLayout();

	@Override
	protected void initializeLayout() {
		root = new HorizontalSplitPanel();
		root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		root.setLocked(true);
		root.setSizeFull();

		mySpaceArea.setWidth("100%");
		myProjectTab = new DetachedTabs.Vertical(mySpaceArea);
		myProjectTab.setSizeFull();
		myProjectTab.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setSizeFull();
		menu.setStyleName("sidebar-menu");

		root.setFirstComponent(menu);

		buildComponents();
		this.addComponent(root);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Tasks");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Bugs");
	}

	private com.vaadin.ui.Component constructProjectDashboardComponent() {
		return AppContext.getView(ProjectDashboardViewImpl.class);
	}
}
