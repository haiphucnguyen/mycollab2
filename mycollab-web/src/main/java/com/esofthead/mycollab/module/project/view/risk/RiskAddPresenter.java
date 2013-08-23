package com.esofthead.mycollab.module.project.view.risk;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;
import com.esofthead.mycollab.vaadin.events.EditFormHandler;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.HistoryViewManager;
import com.esofthead.mycollab.vaadin.mvp.NullViewState;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewState;
import com.esofthead.mycollab.vaadin.ui.MessageConstants;
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
			MessageConstants.showMessagePermissionAlert();
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
		RiskService riskService = AppContext.getSpringBean(RiskService.class);
		risk.setProjectid(CurrentProjectVariables.getProjectId());
		risk.setSaccountid(AppContext.getAccountId());

		SimpleRelayEmailNotification relayNotification = new SimpleRelayEmailNotification();
		relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
		relayNotification.setChangeby(AppContext.getUsername());
		relayNotification.setChangecomment("");
		relayNotification.setSaccountid(AppContext.getAccountId());
		relayNotification.setType(MonitorTypeConstants.PRJ_RISK);

		relayNotification
				.setEmailhandlerbean(ProjectRiskRelayEmailNotificationAction.class
						.getName());

		relayNotification.setExtratypeid(risk.getProjectid());
		List<SimpleUser> usersInProject = projectMemberService
				.getUsersInProject(risk.getProjectid(), 0);
		relayNotification.setNotifyUsers(usersInProject);

		RelayEmailNotificationService relayEmailNotificationService = AppContext
				.getSpringBean(RelayEmailNotificationService.class);

		if (risk.getId() == null) {
			Integer id = riskService.saveWithSession(risk,
					AppContext.getUsername());
			relayNotification.setTypeid(id);
			relayEmailNotificationService.saveWithSession(relayNotification,
					AppContext.getUsername());
		} else {
			relayNotification.setAction(MonitorTypeConstants.UPDATE_ACTION);
			riskService.updateWithSession(risk, AppContext.getUsername());
			relayNotification.setTypeid(risk.getId());
			relayEmailNotificationService.saveWithSession(relayNotification,
					AppContext.getUsername());
		}

	}
}
