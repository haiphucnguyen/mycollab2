package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class MeetingAddPresenter extends CrmGenericPresenter<MeetingAddView> {

	private static final long serialVersionUID = 1L;

	public MeetingAddPresenter() {
		super(MeetingAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Meeting>() {
					@Override
					public void onSave(final Meeting item) {
						save(item);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.GotoTodoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.GotoTodoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Meeting item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_MEETING)) {
			Meeting meeting = null;
			if (data.getParams() instanceof Meeting) {
				meeting = (Meeting) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				MeetingService meetingService = AppContext
						.getSpringBean(MeetingService.class);
				meeting = meetingService.findByPrimaryKey(
						(Integer) data.getParams(), AppContext.getAccountId());
				if (meeting == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									LocalizationHelper
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}

			container.removeAllComponents();
			container.addComponent(view.getWidget());

			view.editItem(meeting);

			if (meeting.getId() == null) {
				AppContext.addFragment("crm/activity/meeting/add/",
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_ADD_ITEM_TITLE,
								"Meeting"));
			} else {
				AppContext.addFragment("crm/activity/meeting/edit/"
						+ UrlEncodeDecoder.encode(meeting.getId()),
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_EDIT_ITEM_TITLE,
								"Meeting", meeting.getSubject()));
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void save(Meeting item) {
		MeetingService meetingService = AppContext
				.getSpringBean(MeetingService.class);

		item.setSaccountid(AppContext.getAccountId());
		if (item.getId() == null) {
			meetingService.saveWithSession(item, AppContext.getUsername());
		} else {
			meetingService.updateWithSession(item, AppContext.getUsername());
		}
	}
}
