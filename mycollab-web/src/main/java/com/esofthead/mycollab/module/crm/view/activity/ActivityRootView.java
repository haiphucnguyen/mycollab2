package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.module.crm.localization.ActivityI18nEnum;
import com.esofthead.mycollab.module.crm.view.parameters.ActivityScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

@ViewComponent
public class ActivityRootView extends AbstractView {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout root;
	private final DetachedTabs activityTabs;
	private final CssLayout mySpaceArea = new CssLayout();

	private ActivityCalendarPresenter calendarPresenter;
	private EventPresenter eventPresenter;

	public ActivityRootView() {
		super();
		this.setSizeFull();

		this.setWidth("100%");

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("projectDashboardView");
		contentWrapper.addStyleName("main-content-wrapper");
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		root = new HorizontalLayout();
		root.setStyleName("menuContent");

		activityTabs = new DetachedTabs.Vertical(mySpaceArea);
		activityTabs.setSizeFull();
		activityTabs.setHeight(null);

		CssLayout menu = new CssLayout();
		menu.setWidth("170px");
		menu.setStyleName("sidebar-menu");
		menu.addComponent(activityTabs);

		root.addComponent(menu);
		mySpaceArea.setStyleName("projectTabContent");
		mySpaceArea.setWidth("100%");
		mySpaceArea.setHeight(null);
		mySpaceArea.setMargin(true);
		root.addComponent(mySpaceArea);
		root.setExpandRatio(mySpaceArea, 1.0f);
		root.setWidth("100%");
		buildComponents();
		contentWrapper.addComponent(root);
	}

	private void buildComponents() {
		activityTabs.addTab(constructCalendarView(), new MenuButton("Calendar",
				"notification.png"));
		activityTabs.addTab(constructActivityListView(), new MenuButton(
				"Activities List", "layout.png"));

		activityTabs
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {
					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();
						mySpaceArea.setStyleName("projectTabContent");

						if ("Calendar".equals(caption)) {
							calendarPresenter.go(ActivityRootView.this,
									new ActivityScreenData.GotoCalendar());
						} else if ("Activities List".equals(caption)) {
							eventPresenter.go(ActivityRootView.this,
									new ActivityScreenData.GotoActivityList(
											null));
						}
					}
				});
	}

	private ComponentContainer constructCalendarView() {
		calendarPresenter = PresenterResolver
				.getPresenter(ActivityCalendarPresenter.class);
		ActivityCalendarView activityCalendarView = calendarPresenter.getView();
		return activityCalendarView;
	}

	private ComponentContainer constructActivityListView() {
		eventPresenter = PresenterResolver.getPresenter(EventPresenter.class);
		return eventPresenter.getView();
	}

	public void gotoCalendar() {
		com.vaadin.ui.Component calendarComp = activityTabs
				.selectTab(LocalizationHelper
						.getMessage(ActivityI18nEnum.CALENDAR_TAB_TITLE));
		this.mySpaceArea.setStyleName("calendarTab");

		if (calendarComp != null) {
			calendarPresenter.go(this, null);
		}
	}

	private static class MenuButton extends Button {
		private static final long serialVersionUID = 1L;

		public MenuButton(String caption, String iconResource) {
			super(caption);
			this.setIcon(MyCollabResource.newResource("icons/22/crm/"
					+ iconResource));
			this.setStyleName("link");
		}
	}

	public Component gotoView(String viewName) {
		return activityTabs.selectTab(viewName);
	}

	public void gotoActivityList() {
		com.vaadin.ui.Component activityList = activityTabs
				.selectTab(LocalizationHelper
						.getMessage(ActivityI18nEnum.ACTIVITY_LIST_TAB_TITLE));
		this.mySpaceArea.setStyleName("activityTab");

		if (activityList != null) {
			EventSearchCriteria searchCriteria = new EventSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));
			eventPresenter.go(this, new ActivityScreenData.GotoActivityList(
					searchCriteria));
		}
	}
}
