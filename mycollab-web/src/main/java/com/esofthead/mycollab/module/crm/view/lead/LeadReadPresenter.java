package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.domain.SimpleLead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LeadReadPresenter extends CrmGenericPresenter<LeadReadView> {
	private static final long serialVersionUID = 1L;

	public LeadReadPresenter() {
		super(LeadReadView.class);
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

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);

		if (data.getParams() instanceof Integer) {
			LeadService leadService = AppContext
					.getSpringBean(LeadService.class);
			SimpleLead lead = leadService.findLeadById((Integer) data
					.getParams());
			view.previewItem(lead);
		}
	}

}
