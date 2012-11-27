package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {
	private final HorizontalLayout root;

	private final DetachedTabs mySpaceTabs;
	private final CssLayout mySpaceArea = new CssLayout();
	private final DetachedTabs calendarToolTabs;

	public UserDashboardViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);
		root = new HorizontalLayout();
		root.setStyleName("menuContent");
		// root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		// root.setLocked(true);
		root.setWidth("100%");

		mySpaceArea.setWidth("100%");
		mySpaceTabs = new DetachedTabs.Vertical(mySpaceArea);
		mySpaceTabs.setWidth("200px");
		mySpaceTabs.setHeight(null);

		calendarToolTabs = new DetachedTabs.Vertical(mySpaceArea);
		calendarToolTabs.setSizeFull();
		calendarToolTabs.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setWidth("200px");
		menu.setStyleName("sidebar-menu");

		Label myHome = new Label("My Home");
		myHome.setStyleName("sectionHeader");
		menu.addComponent(myHome);
		menu.addComponent(mySpaceTabs);
		Label calendar = new Label("Calendar");
		calendar.setStyleName("sectionHeader");
		menu.addComponent(calendar);
		menu.addComponent(calendarToolTabs);

		mySpaceTabs.setStyleName("hide-selection");
		calendarToolTabs.setStyleName("hide-selection");
		menu.addListener(new LayoutClickListener() {
			@Override
			public void layoutClick(LayoutClickEvent event) {
				if (!root.getComponent(1).equals(mySpaceArea)) {
					// root.addComponent(mySpaceArea);
					root.replaceComponent(root.getComponent(1), mySpaceArea);
					root.setExpandRatio(root.getComponent(1), 1.0f);
				}
				if (event.getChildComponent() == mySpaceTabs) {
					calendarToolTabs.setStyleName("hide-selection");
					mySpaceTabs.setStyleName("");
				} else if (event.getChildComponent() == calendarToolTabs) {
					mySpaceTabs.setStyleName("hide-selection");
					calendarToolTabs.setStyleName("");
				}
			}
		});

		root.addComponent(menu);

		buildComponents();
		showWelcomeScreen();

		this.addComponent(root);
		// this.setExpandRatio(root, 1.0f);

	}

	private void showWelcomeScreen() {
		CustomLayout welcome = new CustomLayout("projectWelcomeScreen");
		welcome.setSizeFull();
		root.addComponent(welcome);
	}

	private void buildComponents() {
		mySpaceTabs.addTab(constructMyFeedsComponents(), "My Feeds");
		mySpaceTabs.addTab(constructMyProjectsComponents(), "My Projects");
		mySpaceTabs.addTab(constructMyFeedsComponents(), "My Tasks");
		mySpaceTabs.addTab(constructMyFeedsComponents(), "My Bugs");

		mySpaceTabs
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						if ("My Projects".equals(caption)) {
							gotoMyProjectList();
						}
					}
				});
	}

	private Layout constructMyFeedsComponents() {
		return new VerticalLayout();
	}

	private ComponentContainer constructMyProjectsComponents() {
		MyProjectsViewImpl view = ViewManager.getView(MyProjectsViewImpl.class);
		return view;
	}

	@Override
	public void gotoMyProjectList() {
		com.vaadin.ui.Component myProjectComponent = mySpaceTabs
				.selectTab("My Projects");
		if (myProjectComponent != null) {
			MyProjectsViewImpl view = ViewManager
					.getView(MyProjectsViewImpl.class);
			view.doDefaultSearch();
		}
	}

}
