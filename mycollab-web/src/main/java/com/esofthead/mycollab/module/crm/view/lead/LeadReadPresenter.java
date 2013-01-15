package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.Meeting;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.domain.Task;
import com.esofthead.mycollab.module.crm.domain.criteria.LeadSearchCriteria;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.AbstractRelatedListHandler;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.DefaultPreviewFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {

	private static final long serialVersionUID = 1L;

	public LeadReadPresenter() {
		super(LeadReadView.class);
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new DefaultPreviewFormHandler<Lead>() {
					@Override
					public void onEdit(Lead data) {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Lead data) {
						LeadService LeadService = AppContext
								.getSpringBean(LeadService.class);
						LeadService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Lead data) {
						Lead cloneData = (Lead) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new LeadEvent.GotoList(this, null));
					}

					@Override
					public void gotoNext(Lead data) {
						LeadService contactService = AppContext
								.getSpringBean(LeadService.class);
						LeadSearchCriteria criteria = new LeadSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.GREATHER));
						Integer nextId = contactService
								.getNextItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new LeadEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the last record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}

					}

					@Override
					public void gotoPrevious(Lead data) {
						LeadService contactService = AppContext
								.getSpringBean(LeadService.class);
						LeadSearchCriteria criteria = new LeadSearchCriteria();
						criteria.setSaccountid(new NumberSearchField(AppContext
								.getAccountId()));
						criteria.setId(new NumberSearchField(data.getId(),
								NumberSearchField.LESSTHAN));
						Integer nextId = contactService
								.getPreviousItemKey(criteria);
						if (nextId != null) {
							EventBus.getInstance().fireEvent(
									new LeadEvent.GotoRead(this, nextId));
						} else {
							view.getWindow().showNotification("Information",
									"You are already in the first record",
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
						}
					}
				});

		view.getRelatedActivityHandlers().addRelatedListHandler(
				new AbstractRelatedListHandler() {
					@Override
					public void createNewRelatedItem(String itemId) {
						if (itemId.equals("task")) {
							Task task = new Task();
							task.setType(CrmTypeConstants.LEAD);
							task.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.TaskEdit(
											LeadReadPresenter.this, task));
						} else if (itemId.equals("meeting")) {
							Meeting meeting = new Meeting();
							meeting.setType(CrmTypeConstants.LEAD);
							meeting.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.MeetingEdit(
											LeadReadPresenter.this, meeting));
						} else if (itemId.equals("call")) {
							Call call = new Call();
							call.setType(CrmTypeConstants.LEAD);
							call.setTypeid(view.getItem().getId());
							EventBus.getInstance().fireEvent(
									new ActivityEvent.CallEdit(
											LeadReadPresenter.this, call));
						}
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (data.getParams() instanceof Integer) {
			LeadService leadService = AppContext
					.getSpringBean(LeadService.class);
			SimpleLead lead = leadService.findLeadById((Integer) data
					.getParams());
			if (lead != null) {
                super.onGo(container, data);
                view.previewItem(lead);
            } else {
                AppContext.getApplication().getMainWindow().showNotification("Information", "The record is not existed", Window.Notification.TYPE_HUMANIZED_MESSAGE);
                return;
            }
		}
	}
}
