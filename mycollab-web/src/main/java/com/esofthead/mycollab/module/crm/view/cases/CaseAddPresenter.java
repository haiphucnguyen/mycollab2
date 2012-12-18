package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CaseAddPresenter extends CrmGenericPresenter<CaseAddView> {
	private static final long serialVersionUID = 1L;

	public CaseAddPresenter() {
		super(CaseAddView.class);
		bind();
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Case>() {

			@Override
			public void onSave(final Case cases) {
				saveCase(cases);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new CaseEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new CaseEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final Case cases) {
				saveCase(cases);
				EventBus.getInstance().fireEvent(
						new CaseEvent.GotoAdd(this, null));
			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Case) data.getParams());
	}

	public void saveCase(Case cases) {
		CaseService caseService = AppContext.getSpringBean(CaseService.class);

		cases.setSaccountid(AppContext.getAccountId());
		if (cases.getId() == null) {
			caseService.saveWithSession(cases, AppContext.getUsername());
		} else {
			caseService.updateWithSession(cases, AppContext.getUsername());
		}

	}

}
