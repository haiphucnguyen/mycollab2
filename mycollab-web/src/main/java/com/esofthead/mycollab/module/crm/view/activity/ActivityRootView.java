package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.crm.domain.criteria.EventSearchCriteria;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.web.AppContext;
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

public class ActivityRootView extends AbstractView {
	private static final long serialVersionUID = 1L;

	private DetachedTabs activityTabs;
	private CssLayout mySpaceArea = new CssLayout();

	private ActivityCalendarPresenter calendarPresenter;

	private EventListPresenter eventListPresenter;

	public ActivityRootView() {
		super();

		this.setSizeFull();

		mySpaceArea.setWidth("100%");
		activityTabs = new DetachedTabs.Horizontal(mySpaceArea);
		activityTabs.setWidth("100%");
		activityTabs.setHeight(null);

		activityTabs.addTab(constructCalendarView(), "Calendar");
		activityTabs.addTab(constructActivityListView(), "All Todo and Events");

		activityTabs
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();

						if (caption.equals("Calendar")) {
							gotoCalendar();
						} else if (caption.equals("All Todo and Events")) {
							gotoActivityList();
						}
					}
				});

		this.addComponent(activityTabs);
		this.addComponent(mySpaceArea);
		this.setExpandRatio(mySpaceArea, 1.0f);
	}

	private ComponentContainer constructCalendarView() {
		ActivityCalendarViewImpl view = ViewManager
				.getView(ActivityCalendarViewImpl.class);
		calendarPresenter = new ActivityCalendarPresenter(view);
		return view;
	}

	private ComponentContainer constructActivityListView() {
		EventListViewImpl view = ViewManager.getView(EventListViewImpl.class);
		eventListPresenter = new EventListPresenter(view);
		return view;
	}

	public void gotoCalendar() {
		com.vaadin.ui.Component calendarComp = activityTabs
				.selectTab("Calendar");

		if (calendarComp != null) {
			calendarPresenter.go(this, null);
		}
	}

	public void gotoActivityList() {
		com.vaadin.ui.Component activityList = activityTabs
				.selectTab("All Todo and Events");

		if (activityList != null) {
			EventSearchCriteria searchCriteria = new EventSearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));
			eventListPresenter.go(this, new ScreenData<EventSearchCriteria>(
					searchCriteria));
		}
	}
}
