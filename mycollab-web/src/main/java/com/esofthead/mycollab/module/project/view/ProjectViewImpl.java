package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class ProjectViewImpl extends AbstractView implements ProjectView {

	private HorizontalSplitPanel root;
	private DetachedTabs myProjectTab;
	private CssLayout mySpaceArea = new CssLayout();

	private ProjectMessagePresenter messagePresenter;
	private ProjectMilestonePresenter milestonesPresenter;

	private SimpleProject project;

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
		root.addComponent(mySpaceArea);

		buildComponents();
		this.addComponent(root);
	}

	private void buildComponents() {
		myProjectTab.addTab(constructProjectDashboardComponent(), "Dashboard");
		myProjectTab.addTab(constructProjectMessageComponent(), "Messages");
		myProjectTab.addTab(constructProjectMilestoneComponent(), "Milestones");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Tasks");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Bugs");
		myProjectTab.addTab(constructProjectDashboardComponent(), "Calendar");

		myProjectTab
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						if ("Messages".equals(caption)) {
							messagePresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						} else if ("Milestones".equals(caption)) {
							milestonesPresenter.go(ProjectViewImpl.this,
									new ScreenData<SimpleProject>(project));
						}
					}
				});
	}

	private Component constructProjectDashboardComponent() {
		return new ProjectDashboardViewImpl();
	}

	private Component constructProjectMessageComponent() {
		ProjectMessageViewImpl messageView = ViewManager
				.getView(ProjectMessageViewImpl.class);
		messagePresenter = new ProjectMessagePresenter(messageView);
		return messageView;
	}

	private Component constructProjectMilestoneComponent() {
		ProjectMilestoneViewImpl milestoneView = ViewManager
				.getView(ProjectMilestoneViewImpl.class);
		milestonesPresenter = new ProjectMilestonePresenter(milestoneView);
		return milestoneView;
	}

	@Override
	public void displayProject(SimpleProject project) {
		this.project = project;
	}

	@Override
	public Component gotoSubView(String name) {
		ProjectAbstractView component = (ProjectAbstractView)myProjectTab.selectTab(name);
		component.setProject(project);
		return component;
	}
}
