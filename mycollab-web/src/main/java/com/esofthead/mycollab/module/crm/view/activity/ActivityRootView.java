/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.criteria.ActivitySearchCriteria;
import com.esofthead.mycollab.module.crm.localization.ActivityI18nEnum;
import com.esofthead.mycollab.module.crm.view.parameters.ActivityScreenData;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.VerticalTabsheet;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;
import com.vaadin.ui.TabSheet.SelectedTabChangeListener;
import com.vaadin.ui.TabSheet.Tab;

/**
 * 
 * @author MyCollab Ltd.
 * @since 2.0
 * 
 */
@ViewComponent
public class ActivityRootView extends AbstractPageView {
	private static final long serialVersionUID = 1L;

	private final HorizontalLayout root;
	private final VerticalTabsheet activityTabs;

	private ActivityCalendarPresenter calendarPresenter;
	private ActivityPresenter eventPresenter;

	public ActivityRootView() {
		super();
		this.setSizeFull();

		final CssLayout contentWrapper = new CssLayout();
		contentWrapper.setStyleName("verticalTabView");
		contentWrapper.setWidth("100%");
		this.addComponent(contentWrapper);

		root = new HorizontalLayout();
		root.setStyleName("menuContent");

		activityTabs = new VerticalTabsheet();
		activityTabs.setSizeFull();
		activityTabs.setNavigatorWidth("170px");
		activityTabs.setNavigatorStyleName("sidebar-menu");
		activityTabs.setContainerStyleName("tab-content");
		activityTabs.setHeight(null);

		root.addComponent(activityTabs);
		root.setWidth("100%");
		buildComponents();
		contentWrapper.addComponent(root);
	}

	private void buildComponents() {
		activityTabs.addTab(constructCalendarView(), "Calendar",
				MyCollabResource.newResource("icons/22/crm/calendar.png"));

		activityTabs.addTab(constructActivityListView(), "Activities List",
				MyCollabResource.newResource("icons/22/crm/activitylist.png"));

		activityTabs
				.addSelectedTabChangeListener(new SelectedTabChangeListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void selectedTabChange(SelectedTabChangeEvent event) {
						Tab tab = ((VerticalTabsheet) event.getSource())
								.getSelectedTab();
						String caption = tab.getCaption();

						if ("Calendar".equals(caption)) {
							calendarPresenter.go(ActivityRootView.this,
									new ActivityScreenData.GotoCalendar());
						} else if ("Activities List".equals(caption)) {
							ActivitySearchCriteria criteria = new ActivitySearchCriteria();
							criteria.setSaccountid(new NumberSearchField(
									AppContext.getAccountId()));
							eventPresenter.go(ActivityRootView.this,
									new ActivityScreenData.GotoActivityList(
											criteria));
						}

					}
				});
	}

	private ComponentContainer constructCalendarView() {
		calendarPresenter = PresenterResolver
				.getPresenter(ActivityCalendarPresenter.class);
		ActivityCalendarView activityCalendarView = calendarPresenter
				.initView();
		return activityCalendarView;
	}

	private ComponentContainer constructActivityListView() {
		eventPresenter = PresenterResolver
				.getPresenter(ActivityPresenter.class);
		return eventPresenter.initView();
	}

	public void gotoCalendar() {
		com.vaadin.ui.Component calendarComp = activityTabs
				.selectTab(LocalizationHelper
						.getMessage(ActivityI18nEnum.CALENDAR_TAB_TITLE));

		if (calendarComp != null) {
			calendarPresenter.go(this, null);
		}
	}

	public Component gotoView(String viewName) {
		return activityTabs.selectTab(viewName);
	}

	public void gotoActivityList() {
		Component activityList = activityTabs.selectTab(LocalizationHelper
				.getMessage(ActivityI18nEnum.ACTIVITY_LIST_TAB_TITLE));

		if (activityList != null) {
			ActivitySearchCriteria searchCriteria = new ActivitySearchCriteria();
			searchCriteria.setSaccountid(new NumberSearchField(SearchField.AND,
					AppContext.getAccountId()));
			eventPresenter.go(this, new ActivityScreenData.GotoActivityList(
					searchCriteria));
		}
	}
}
