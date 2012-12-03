package com.esofthead.mycollab.module.crm.view.opportunity;

import com.esofthead.mycollab.module.crm.domain.Opportunity;
import com.esofthead.mycollab.module.crm.events.OpportunityEvent;
import com.esofthead.mycollab.module.crm.service.OpportunityService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class OpportunityAddPresenter extends
		CrmGenericPresenter<OpportunityAddView> {
	private static final long serialVersionUID = 1L;

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
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new OpportunityEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Opportunity account) {
						saveOpportunity(account);
						EventBus.getInstance().fireEvent(
								new OpportunityEvent.GotoAdd(this, null));
					}
				});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Opportunity) data.getParams());
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
