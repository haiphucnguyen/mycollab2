package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.crm.domain.CallWithBLOBs;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
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

public class CallAddPresenter extends CrmGenericPresenter<CallAddView> {

	private static final long serialVersionUID = 1L;

	public CallAddPresenter() {
		super(CallAddView.class);

		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<CallWithBLOBs>() {
					@Override
					public void onSave(final CallWithBLOBs item) {
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
					public void onSaveAndNew(final CallWithBLOBs item) {
						save(item);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.CallAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (AppContext.canWrite(RolePermissionCollections.CRM_TASK)) {
			CallWithBLOBs call = null;

			if (data.getParams() instanceof Integer) {
				CallService callService = ApplicationContextUtil
						.getSpringBean(CallService.class);
				call = callService.findByPrimaryKey((Integer) data.getParams(),
						AppContext.getAccountId());
				if (call == null) {
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
			} else if (data.getParams() instanceof CallWithBLOBs) {
				call = (CallWithBLOBs) data.getParams();
			} else {
				throw new MyCollabException("Invalid data: " + data);
			}

			container.removeAllComponents();
			container.addComponent(view.getWidget());

			view.editItem(call);

			if (call.getId() == null) {
				AppContext.addFragment("crm/activity/call/add/",
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_ADD_ITEM_TITLE, "Call"));
			} else {
				AppContext.addFragment("crm/activity/call/edit/"
						+ UrlEncodeDecoder.encode(call.getId()),
						LocalizationHelper.getMessage(
								GenericI18Enum.BROWSER_EDIT_ITEM_TITLE, "Call",
								call.getSubject()));
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	public void save(CallWithBLOBs item) {
		CallService taskService = ApplicationContextUtil.getSpringBean(CallService.class);

		item.setSaccountid(AppContext.getAccountId());
		if (item.getId() == null) {
			taskService.saveWithSession(item, AppContext.getUsername());
		} else {
			taskService.updateWithSession(item, AppContext.getUsername());
		}

	}
}
