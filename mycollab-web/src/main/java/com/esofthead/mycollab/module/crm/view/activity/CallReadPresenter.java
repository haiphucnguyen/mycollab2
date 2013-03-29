package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;

import org.vaadin.dialogs.ConfirmDialog;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class CallReadPresenter extends CrmGenericPresenter<CallReadView> {

	private static final long serialVersionUID = 1L;

	public CallReadPresenter() {
		super(CallReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<SimpleCall>() {
					@Override
					public void onEdit(SimpleCall data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.CallEdit(this, data));
					}

					@Override
					public void onDelete(final SimpleCall data) {
						ConfirmDialog.show(
								view.getWindow(),
								"Please Confirm:",
								"Are you sure to delete call '"
										+ data.getSubject() + "' ?", "Yes",
								"No", new ConfirmDialog.Listener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void onClose(ConfirmDialog dialog) {
										if (dialog.isConfirmed()) {
											CallService callService = AppContext
													.getSpringBean(CallService.class);
											callService.removeWithSession(
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
					public void onClone(SimpleCall data) {
						Call cloneData = (Call) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.CallEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
					}

					@Override
					public void gotoNext(SimpleCall data) {
						CallService callService = AppContext
								.getSpringBean(CallService.class);
						CallSearchCriteria criteria = new CallSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATER));
						Integer nextId = callService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this, nextId));
						} else {
							view.getWindow().showNotification(AppContext.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext.getMessage(GenericI18Enum.INFORMATION_GOTO_LAST_RECORD),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(SimpleCall data) {
						CallService callService = AppContext
								.getSpringBean(CallService.class);
						CallSearchCriteria criteria = new CallSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = callService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this, nextId));
						} else {
							view.getWindow().showNotification(AppContext.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext.getMessage(GenericI18Enum.INFORMATION_GOTO_FIRST_RECORD),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canRead(RolePermissionCollections.CRM_CALL)) {
			SimpleCall call = null;
			if (data.getParams() instanceof Integer) {
				CallService callService = AppContext
						.getSpringBean(CallService.class);
				call = callService.findCallById((Integer) data.getParams());
				if (call == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			} else if (data.getParams() instanceof SimpleCall) {
				call = (SimpleCall) data.getParams();
			}
			super.onGo(container, data);
			view.previewItem(call);
			AppContext.addFragment("crm/activity/call/preview/"
					+ UrlEncodeDecoder.encode(call.getId()), "Preiview Call: "
					+ call.getSubject());
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}
}
