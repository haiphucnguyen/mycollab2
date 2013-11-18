package com.esofthead.mycollab.premium.module.project.view.risk;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageBox;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.ComponentContainer;

public class RiskAddPresenter extends AbstractPresenter<RiskAddView> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectMemberService projectMemberService;

	public RiskAddPresenter() {
		super(RiskAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.RISKS)) {
			RiskContainer riskContainer = (RiskContainer) container;
			riskContainer.removeAllComponents();
			riskContainer.addComponent(view.getWidget());
			Risk risk = (Risk) data.getParams();
			view.editItem(risk);

			ProjectBreadcrumb breadCrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (risk.getId() == null) {
				breadCrumb.gotoRiskAdd();
			} else {
				breadCrumb.gotoRiskEdit(risk);
			}
		} else {
			MessageBox.showMessagePermissionAlert();
		}
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(new EditFormHandler<Risk>() {
			@Override
			public void onSave(final Risk risk) {
				saveRisk(risk);
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new RiskEvent.GotoList(this, null));
				}
			}

			@Override
			public void onCancel() {
				ViewState viewState = HistoryViewManager.back();
				if (viewState instanceof NullViewState) {
					EventBus.getInstance().fireEvent(
							new RiskEvent.GotoList(this, null));
				}
			}

			@Override
			public void onSaveAndNew(final Risk risk) {
				saveRisk(risk);
				EventBus.getInstance().fireEvent(
						new RiskEvent.GotoAdd(this, null));
			}
		});
	}

	public void saveRisk(Risk risk) {
		RiskService riskService = ApplicationContextUtil.getSpringBean(RiskService.class);
		risk.setProjectid(CurrentProjectVariables.getProjectId());
		risk.setSaccountid(AppContext.getAccountId());

		if (risk.getId() == null) {
			riskService.saveWithSession(risk, AppContext.getUsername());
		} else {
			riskService.updateWithSession(risk, AppContext.getUsername());
		}

	}
}
