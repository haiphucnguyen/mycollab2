package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.module.project.view.user.MyDefectsPresenterImpl;
import com.esofthead.mycollab.module.project.view.user.MyDefectsView.MyDefectsPresenter;
import com.esofthead.mycollab.module.project.view.user.MyDefectsViewImpl;
import com.esofthead.mycollab.module.project.view.user.MyFeedsPresenterImpl;
import com.esofthead.mycollab.module.project.view.user.MyFeedsView.MyFeedsPresenter;
import com.esofthead.mycollab.module.project.view.user.MyFeedsViewImpl;
import com.esofthead.mycollab.module.project.view.user.MyProjectsPresenterImpl;
import com.esofthead.mycollab.module.project.view.user.MyProjectsView.MyProjectPresenter;
import com.esofthead.mycollab.module.project.view.user.MyProjectsViewImpl;
import com.esofthead.mycollab.module.project.view.user.MyTasksPresenterImpl;
import com.esofthead.mycollab.module.project.view.user.MyTasksView.MyTasksPresenter;
import com.esofthead.mycollab.module.project.view.user.MyTasksViewImpl;
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

	private MyProjectPresenter myProjectPresenter;
	private MyFeedsPresenter myFeedsPresenter;
	private MyTasksPresenter myTasksPresenter;
	private MyDefectsPresenter myDefectsPresenter;

	public UserDashboardViewImpl() {
		this.setStyleName("projectDashboardView");
		this.setMargin(false);
		root = new HorizontalLayout();
		root.setStyleName("menuContent");
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
	}

	private void showWelcomeScreen() {
		CustomLayout welcome = new CustomLayout("projectWelcomeScreen");
		welcome.setSizeFull();
		root.addComponent(welcome);
	}

	private void buildComponents() {
		mySpaceTabs.addTab(constructMyFeedsComponents(), "My Feeds");
		mySpaceTabs.addTab(constructMyProjectsComponents(), "My Projects");
		mySpaceTabs.addTab(constructMyTasksComponents(), "My Tasks");
		mySpaceTabs.addTab(constructMyBugsComponents(), "My Bugs");

		mySpaceTabs
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						if ("My Projects".equals(caption)) {
							gotoMyProjectList();
						} else if ("My Feeds".equals(caption)) {
							gotoMyFeeds();
						} else if ("My Tasks".equals(caption)) {
							gotoMyTasks();
						} else if ("My Bugs".equals(caption)) {
							gotoMyBugs();
						}
					}
				});
	}

	private Layout constructMyFeedsComponents() {
		MyFeedsViewImpl view = ViewManager.getView(MyFeedsViewImpl.class);
		myFeedsPresenter = new MyFeedsPresenterImpl(view);
		return view;
	}

	private ComponentContainer constructMyProjectsComponents() {
		MyProjectsViewImpl view = ViewManager.getView(MyProjectsViewImpl.class);
		myProjectPresenter = new MyProjectsPresenterImpl(view);
		return view;
	}

	private ComponentContainer constructMyTasksComponents() {
		MyTasksViewImpl view = ViewManager.getView(MyTasksViewImpl.class);
		myTasksPresenter = new MyTasksPresenterImpl(view);
		return view;
	}

	private ComponentContainer constructMyBugsComponents() {
		MyDefectsViewImpl view = ViewManager.getView(MyDefectsViewImpl.class);
		myDefectsPresenter = new MyDefectsPresenterImpl(view);
		return view;
	}

	@Override
	public void gotoMyProjectList() {
		com.vaadin.ui.Component myProjectComponent = mySpaceTabs
				.selectTab("My Projects");
		if (myProjectComponent != null) {
			myProjectPresenter.doDefaultSearch();
		}
	}

	@Override
	public void gotoMyFeeds() {
		com.vaadin.ui.Component component = mySpaceTabs.selectTab("My Feeds");
		if (component != null) {
			myFeedsPresenter.doDefaultSearch();
		}
	}

	@Override
	public void gotoMyTasks() {
		com.vaadin.ui.Component component = mySpaceTabs.selectTab("My Tasks");
		if (component != null) {
			myTasksPresenter.doDefaultSearch();
		}

	}

	@Override
	public void gotoMyBugs() {
		com.vaadin.ui.Component component = mySpaceTabs.selectTab("My Bugs");
		if (component != null) {
			myDefectsPresenter.doDefaultSearch();
		}

	}

}
