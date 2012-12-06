package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleMeeting;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.MeetingService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class MeetingReadPresenter  extends CrmGenericPresenter<MeetingReadView> {
	private static final long serialVersionUID = 1L;

	public MeetingReadPresenter(MeetingReadView view) {
		this.view = view;
		bind();
	}
	
	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Meeting>() {

					@Override
					public void onEdit(Meeting data) {
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingAdd(this, data));
					}

					@Override
					public void onDelete(Meeting data) {
						MeetingService campaignService = AppContext
								.getSpringBean(MeetingService.class);
						campaignService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new ActivityEvent.GotoTodoList(this, null));
					}

					@Override
					public void onClone(Meeting data) {
						Meeting cloneData = (Meeting) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new ActivityEvent.MeetingAdd(this, cloneData));
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
		view.previewItem((SimpleMeeting)data.getParams());
	}

}
