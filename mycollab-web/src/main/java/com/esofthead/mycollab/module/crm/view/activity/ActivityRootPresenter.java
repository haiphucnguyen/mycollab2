package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.parameters.ActivityScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.AssignmentScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class ActivityRootPresenter extends
		CrmGenericPresenter<ActivityRootView> {
	private static final long serialVersionUID = 1L;

	public ActivityRootPresenter() {
		super(ActivityRootView.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);

		AbstractPresenter presenter = null;

		if (data instanceof AssignmentScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(AssignmentAddPresenter.class);
		} else if (data instanceof AssignmentScreenData.Edit) {

		} else if (data instanceof AssignmentScreenData.Read) {
			
		}
		else if (data instanceof ActivityScreenData.GotoActivityList) {
			view.gotoActivityList();
			AppContext.addFragment("crm/activity/todo", "Activity To Do");
		} else {
			view.gotoCalendar();
			AppContext
					.addFragment("crm/activity/calendar", "Activity Calendar");
		}

		if (presenter != null) {
			presenter.go(view.getWidget(), data);
		}
	}

}
