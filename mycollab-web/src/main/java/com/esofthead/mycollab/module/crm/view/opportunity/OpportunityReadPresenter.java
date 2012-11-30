package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.domain.SimpleOpportunity;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.PreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class OpportunityReadPresenter  extends CrmGenericPresenter<OpportunityReadView> {

	public OpportunityReadPresenter(OpportunityReadView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getPreviewFormHandlers().addFormHandler(
				new PreviewFormHandlers<Opportunity>() {

					@Override
					public void onEdit(Opportunity data) {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoEdit(this, data));
					}

					@Override
					public void onDelete(Opportunity data) {
						OpportunityService OpportunityService = AppContext
								.getSpringBean(OpportunityService.class);
						OpportunityService.removeWithSession(data.getId(),
								AppContext.getUsername());
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoList(this, null));
					}

					@Override
					public void onClone(Opportunity data) {
						Opportunity cloneData = (Opportunity) data.copy();
						cloneData.setId(null);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoEdit(this, cloneData));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoList(this, null));
					}
				});
	}
	
	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.previewItem((SimpleOpportunity)data.getParams());
	}

}
