package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class LeadAddPresenter extends CrmGenericPresenter<LeadAddView> {

	public LeadAddPresenter(LeadAddView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Lead>() {

			@Override
			public void onSave(final Lead lead) {
				saveLead(lead);
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoList(this, null));
			}

			@Override
			public void onCancel() {
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoList(this, null));
			}

			@Override
			public void onSaveAndNew(final Lead lead) {
				saveLead(lead);
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoAdd(this, null));
			}
		});
	}

	public void saveLead(Lead lead) {
		LeadService leadService = AppContext.getSpringBean(LeadService.class);

		lead.setSaccountid(AppContext.getAccountId());
		if (lead.getId() == null) {
			leadService.saveWithSession(lead, AppContext.getUsername());
		} else {
			leadService.updateWithSession(lead, AppContext.getUsername());
		}

	}
}
