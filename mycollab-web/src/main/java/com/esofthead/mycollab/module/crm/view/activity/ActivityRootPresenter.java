package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.crm.localization.ActivityI18nEnum;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.parameters.ActivityScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.AssignmentScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.CallScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.MeetingScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
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

		if (ClassUtils.instanceOf(data, AssignmentScreenData.Read.class,
				AssignmentScreenData.Add.class,
				AssignmentScreenData.Edit.class, MeetingScreenData.Add.class,
				MeetingScreenData.Edit.class, MeetingScreenData.Read.class,
				CallScreenData.Read.class, CallScreenData.Add.class,
				CallScreenData.Edit.class,
				ActivityScreenData.GotoActivityList.class)) {
			presenter = PresenterResolver.getPresenter(EventPresenter.class);
		} else {
			presenter = PresenterResolver
					.getPresenter(ActivityCalendarPresenter.class);
		}

		presenter.go(view.getWidget(), data);
	}

}
