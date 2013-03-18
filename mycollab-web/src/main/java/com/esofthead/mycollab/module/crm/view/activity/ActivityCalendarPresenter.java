package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ActivityCalendarPresenter extends
		AbstractPresenter<ActivityCalendarView> {
	private static final long serialVersionUID = 1L;

	public ActivityCalendarPresenter() {
		super(ActivityCalendarView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		AppContext.addFragment("crm/activity/calendar", "Activity Calendar");
	}

}
