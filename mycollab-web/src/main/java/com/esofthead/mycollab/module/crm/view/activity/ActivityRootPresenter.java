package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class ActivityRootPresenter extends
		CrmGenericPresenter<ActivityRootView> {
	private static final long serialVersionUID = 1L;

	public ActivityRootPresenter() {
		super(ActivityRootView.class);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);

		if (data == null || data.getParams().equals("calendar")) {
			view.gotoCalendar();
		} else if (data.getParams().equals("todo")) {
			view.gotoActivityList();
		}
	}

}
