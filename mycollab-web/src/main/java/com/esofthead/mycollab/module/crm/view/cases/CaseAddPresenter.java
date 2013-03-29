package com.esofthead.mycollab.module.crm.view.cases;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.crm.domain.Case;
import com.esofthead.mycollab.module.crm.events.CaseEvent;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.view.CrmGenericPresenter;
import com.esofthead.mycollab.module.user.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

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
		if (AppContext.canWrite(RolePermissionCollections.CRM_CASE)) {
			Case cases = null;
			if (data.getParams() instanceof Case) {
				cases = (Case) data.getParams();
			} else if (data.getParams() instanceof Integer) {
				CaseService caseService = AppContext
						.getSpringBean(CaseService.class);
				cases = caseService
						.findByPrimaryKey((Integer) data.getParams());
				if (cases == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
					return;
				}
			}
			super.onGo(container, data);
			view.editItem(cases);

			if (cases.getId() == null) {
				AppContext.addFragment("crm/cases/add", "Add Case");
			} else {
				AppContext.addFragment(
						"crm/cases/edit/"
								+ UrlEncodeDecoder.encode(cases.getId()),
						"Edit Case: " + cases.getSubject());
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
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
