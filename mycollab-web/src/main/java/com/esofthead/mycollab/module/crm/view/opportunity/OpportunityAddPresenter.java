package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.web.AppContext;

public class OpportunityAddPresenter extends
		CrmGenericPresenter<OpportunityAddView> {

	public OpportunityAddPresenter(OpportunityAddView view) {
		this.view = view;
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Opportunity>() {

					@Override
					public void onSave(final Opportunity account) {
						saveOpportunity(account);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoList(this, null));
					}

					@Override
					public void onCancel() {
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoList(this, null));
					}

					@Override
					public void onSaveAndNew(final Opportunity account) {
						saveOpportunity(account);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveOpportunity(Opportunity opportunity) {
		OpportunityService opportunityService = AppContext
				.getSpringBean(OpportunityService.class);

		opportunity.setSaccountid(AppContext.getAccountId());
		if (opportunity.getId() == null) {
			opportunityService.saveWithSession(opportunity,
					AppContext.getUsername());
		} else {
			opportunityService.updateWithSession(opportunity,
					AppContext.getUsername());
		}

	}

}
