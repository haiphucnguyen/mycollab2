package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.web.AppContext;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {

	public LeadReadPresenter(LeadReadView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Lead>() {

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
				});
	}

}
