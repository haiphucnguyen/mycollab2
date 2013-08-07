package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.localization.ActivityI18nEnum;
import com.esofthead.mycollab.module.crm.view.parameters.ActivityScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.AssignmentScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.CallScreenData;
import com.esofthead.mycollab.module.crm.view.parameters.MeetingScreenData;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.ComponentContainer;

public class EventPresenter extends AbstractPresenter<EventContainer> {
	private static final long serialVersionUID = 1L;

	public EventPresenter() {
		super(EventContainer.class);
	}

	@Override
	public void go(ComponentContainer container, ScreenData<?> data) {
		super.go(container, data, false);
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		ActivityRootView activityContainer = (ActivityRootView) container;
		EventContainer eventContainer = (EventContainer) activityContainer
				.gotoView(LocalizationHelper
						.getMessage(ActivityI18nEnum.ACTIVITY_LIST_TAB_TITLE));

		AbstractPresenter presenter = null;

		if (data instanceof AssignmentScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(AssignmentAddPresenter.class);
		} else if (data instanceof AssignmentScreenData.Edit) {
			presenter = PresenterResolver
					.getPresenter(AssignmentAddPresenter.class);
		} else if (data instanceof AssignmentScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(AssignmentReadPresenter.class);
		} else if (data instanceof MeetingScreenData.Add) {
			presenter = PresenterResolver
					.getPresenter(MeetingAddPresenter.class);
		} else if (data instanceof MeetingScreenData.Edit) {
			presenter = PresenterResolver
					.getPresenter(MeetingAddPresenter.class);
		} else if (data instanceof MeetingScreenData.Read) {
			presenter = PresenterResolver
					.getPresenter(MeetingReadPresenter.class);
		} else if (data instanceof CallScreenData.Add) {
			presenter = PresenterResolver.getPresenter(CallAddPresenter.class);
		} else if (data instanceof CallScreenData.Edit) {
			presenter = PresenterResolver.getPresenter(CallAddPresenter.class);
		} else if (data instanceof CallScreenData.Read) {
			presenter = PresenterResolver.getPresenter(CallReadPresenter.class);
		} else if (data instanceof ActivityScreenData.GotoActivityList) {
			// AppContext.addFragment("crm/activity/todo", "Activity To Do");
			presenter = PresenterResolver
					.getPresenter(EventListPresenter.class);
		}

		presenter.go(eventContainer, data);
	}

}
