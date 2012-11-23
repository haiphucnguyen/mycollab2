package com.esofthead.mycollab.module.project.ui;

import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class UserDashboardViewImpl extends AbstractView implements
		UserDashboardView {
	private HorizontalSplitPanel root;

	private DetachedTabs mySpaceTabs;
	private CssLayout mySpaceArea = new CssLayout();
	private DetachedTabs calendarToolTabs;

	public UserDashboardViewImpl() {
		root = new HorizontalSplitPanel();
		root.setSplitPosition(200, Sizeable.UNITS_PIXELS);
		root.setLocked(true);
		root.setSizeFull();

		mySpaceArea.setWidth("100%");
		mySpaceTabs = new DetachedTabs.Vertical(mySpaceArea);
		mySpaceTabs.setSizeFull();
		mySpaceTabs.setHeight(null);

		calendarToolTabs = new DetachedTabs.Vertical(mySpaceArea);
		calendarToolTabs.setSizeFull();
		calendarToolTabs.setHeight(null);

		VerticalLayout menu = new VerticalLayout();
		menu.setSizeFull();
		menu.setStyleName("sidebar-menu");

		menu.addComponent(new Label("My Home"));
		menu.addComponent(mySpaceTabs);
		menu.addComponent(new Label("Calendar"));
		menu.addComponent(calendarToolTabs);

		mySpaceTabs.setStyleName("hide-selection");
		calendarToolTabs.setStyleName("hide-selection");
		menu.addListener(new LayoutClickListener() {
			public void layoutClick(LayoutClickEvent event) {
				if (!root.getSecondComponent().equals(mySpaceArea)) {
					root.setSecondComponent(mySpaceArea);
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

		root.setFirstComponent(menu);

		buildComponents();
		showWelcomeScreen();

		this.addComponent(root);
		this.setExpandRatio(root, 1.0f);
	}

	private void showWelcomeScreen() {
		CustomLayout welcome = new CustomLayout("projectWelcomeScreen");
		welcome.setSizeFull();
		root.setSecondComponent(welcome);
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
