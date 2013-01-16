package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.domain.criteria.CallSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
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
					public void onDelete(SimpleCall data) {
						CallService callService = AppContext
								.getSpringBean(CallService.class);
						callService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
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
								NumberSearchField.GREATHER));
						Integer nextId = callService.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
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
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		if (data.getParams() instanceof Integer) {
			CallService callService = AppContext
					.getSpringBean(CallService.class);
			SimpleCall call = callService.findCallById((Integer) data
					.getParams());
			if (call != null) {
                view.previewItem(call);
                System.out.println("not null");
            } else {
            	System.out.println("is  null");
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
		}
		view.previewItem((SimpleCall) data.getParams());
	}
}
