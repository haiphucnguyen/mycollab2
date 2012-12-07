package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.SimpleCall;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CallReadPresenter extends CrmGenericPresenter<CallReadView> {
	private static final long serialVersionUID = 1L;

	public CallReadPresenter(CallReadView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<SimpleCall>() {

					@Override
					public void onEdit(SimpleCall data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.CallAdd(this, data));
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
								new ActivityEvent.CallAdd(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
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
			view.previewItem(call);
		}
		view.previewItem((SimpleCall) data.getParams());
	}
}
