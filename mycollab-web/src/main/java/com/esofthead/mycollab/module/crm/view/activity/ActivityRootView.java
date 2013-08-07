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
import com.github.wolfie.detachedtabs.DetachedTabs;
import com.github.wolfie.detachedtabs.DetachedTabs.TabChangedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;

@ViewComponent
public class ActivityRootView extends AbstractView {
	private static final long serialVersionUID = 1L;

	private final DetachedTabs activityTabs;
	private final CssLayout mySpaceArea = new CssLayout();

	private ActivityCalendarPresenter calendarPresenter;

	private EventPresenter eventPresenter;

	public ActivityRootView() {
		super();

		this.setSizeFull();

		mySpaceArea.setWidth("100%");
		activityTabs = new DetachedTabs.Horizontal(mySpaceArea);
		activityTabs.setWidth("100%");
		activityTabs.setHeight(null);
		activityTabs.setStyleName("activityTabs");

		activityTabs.addTab(constructCalendarView(), LocalizationHelper
				.getMessage(ActivityI18nEnum.CALENDAR_TAB_TITLE));
		activityTabs.addTab(constructActivityListView(), LocalizationHelper
				.getMessage(ActivityI18nEnum.ACTIVITY_LIST_TAB_TITLE));

		activityTabs
				.addTabChangedListener(new DetachedTabs.TabChangedListener() {

					@Override
					public void tabChanged(TabChangedEvent event) {
						Button btn = event.getSource();
						String caption = btn.getCaption();

						if (caption.equals(LocalizationHelper
								.getMessage(ActivityI18nEnum.CALENDAR_TAB_TITLE))) {
							gotoCalendar();
						} else if (caption.equals(LocalizationHelper
								.getMessage(ActivityI18nEnum.ACTIVITY_LIST_TAB_TITLE))) {
							gotoActivityList();
						}
					}
				});

		this.addComponent(activityTabs);
		this.addComponent(mySpaceArea);
		this.setExpandRatio(mySpaceArea, 1.0f);
	}

	private ComponentContainer constructCalendarView() {
		calendarPresenter = PresenterResolver
				.getPresenter(ActivityCalendarPresenter.class);
		return calendarPresenter.getView();
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
