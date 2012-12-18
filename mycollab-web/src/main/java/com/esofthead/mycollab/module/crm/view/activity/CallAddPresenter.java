package com.esofthead.mycollab.module.crm.view.activity;

import com.esofthead.mycollab.module.crm.domain.Call;
import com.esofthead.mycollab.module.crm.events.ActivityEvent;
import com.esofthead.mycollab.module.crm.service.CallService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class CallAddPresenter extends CrmGenericPresenter<CallAddView> {
	private static final long serialVersionUID = 1L;

	public CallAddPresenter() {
		super(CallAddView.class);

		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Call>() {

			@Override
			public void onSave(final Call item) {
				save(item);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new ActivityEvent.GotoTodoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new ActivityEvent.GotoTodoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final Call item) {
				save(item);
				EventBus.getInstance().fireEvent(
						new ActivityEvent.CallAdd(this, null));
			}
		});
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		super.onGo(container, data);
		view.editItem((Call) data.getParams());
	}

	public void save(Call item) {
		CallService taskService = AppContext.getSpringBean(CallService.class);

		item.setSaccountid(AppContext.getAccountId());
		if (item.getId() == null) {
			taskService.saveWithSession(item, AppContext.getUsername());
		} else {
			taskService.updateWithSession(item, AppContext.getUsername());
		}

	}
}
