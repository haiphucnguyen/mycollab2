package com.esofthead.mycollab.module.crm.view.activity;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.domain.criteria.MeetingSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.crm.view.CrmLinkGenerator;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class MeetingReadPresenter extends CrmGenericPresenter<MeetingReadView> {

	private static final long serialVersionUID = 1L;

	public MeetingReadPresenter() {
		super(MeetingReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Meeting>() {
					@Override
					public void onEdit(Meeting data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingEdit(this, data));
					}

					@Override
					public void onDelete(final Meeting data) {
						ConfirmDialog.show(
								view.getWindow(),
								LocalizationHelper
										.getMessage(GenericI18Enum.DELETE_DIALOG_TITLE),
								LocalizationHelper
										.getMessage(GenericI18Enum.CONFIRM_DELETE_RECORD_DIALOG_MESSAGE),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_YES_LABEL),
								LocalizationHelper
										.getMessage(GenericI18Enum.BUTTON_NO_LABEL),
								new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											MeetingService campaignService = AppContext
													.getSpringBean(MeetingService.class);
											campaignService.removeWithSession(
													data.getId(),
													AppContext.getUsername());
											EventBus.getInstance()
													.fireEvent(
															new ActivityEvent.GotoTodoList(
																	this, null));
										}
									}
								});
					}

					@Override
					public void onClone(Meeting data) {
						Meeting cloneData = (Meeting) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
					}

					@Override
					public void gotoNext(Meeting data) {
						MeetingService accountService = AppContext
								.getSpringBean(MeetingService.class);
						MeetingSearchCriteria criteria = new MeetingSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = accountService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ActivityEvent.MeetingRead(this,
													nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Meeting data) {
						MeetingService accountService = AppContext
								.getSpringBean(MeetingService.class);
						MeetingSearchCriteria criteria = new MeetingSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = accountService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance()
									.fireEvent(
											new ActivityEvent.MeetingRead(this,
													nextId));
						} else {
							view.getWindow()
									.showNotification(
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
											LocalizationHelper
													.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
											Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_MEETING)) {
			SimpleMeeting meeting = null;
			if (data.getParams() instanceof Integer) {
				MeetingService meetingService = AppContext
						.getSpringBean(MeetingService.class);
				meeting = meetingService.findMeetingById((Integer) data
						.getParams());
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
			} else if (data.getParams() instanceof SimpleMeeting) {
				meeting = (SimpleMeeting) data.getParams();
			}
			super.onGo(container, data);
			view.previewItem(meeting);

			AppContext.addFragment(CrmLinkGenerator
					.generateMeetingPreviewLink(meeting.getId()),
					"Preview Meeting: " + meeting.getSubject());
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
