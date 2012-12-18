package com.esofthead.mycollab.module.crm.view.lead;

import com.esofthead.mycollab.module.crm.domain.Lead;
import com.esofthead.mycollab.module.crm.events.LeadEvent;
import com.esofthead.mycollab.module.crm.service.LeadService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class LeadAddPresenter extends CrmGenericPresenter<LeadAddView> {
	private static final long serialVersionUID = 1L;

	public LeadAddPresenter() {
		super(LeadAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Lead>() {

			@Override
			public void onSave(final Lead lead) {
				saveLead(lead);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new LeadEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new LeadEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final Lead lead) {
				saveLead(lead);
				EventBus.getInstance().fireEvent(
						new LeadEvent.GotoAdd(this, null));
			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Lead) data.getParams());
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
