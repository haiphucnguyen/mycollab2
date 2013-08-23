package com.esofthead.mycollab.module.project.view.problem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.view.ProjectBreadcrumb;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;
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

public class ProblemAddPresenter extends AbstractPresenter<ProblemAddView> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ProjectMemberService projectMemberService;

	public ProblemAddPresenter() {
		super(ProblemAddView.class);
		bind();
	}

	@Override
	protected void onGo(ComponentContainer container, ScreenData<?> data) {
		if (CurrentProjectVariables
				.canWrite(ProjectRolePermissionCollections.PROBLEMS)) {
			ProblemContainer problemContainer = (ProblemContainer) container;
			problemContainer.removeAllComponents();
			problemContainer.addComponent(view.getWidget());

			Problem problem = (Problem) data.getParams();
			view.editItem(problem);

			ProjectBreadcrumb breadcrumb = ViewManager
					.getView(ProjectBreadcrumb.class);
			if (problem.getId() == null) {
				breadcrumb.gotoProblemAdd();
			} else {
				breadcrumb.gotoProblemEdit(problem);
			}
		} else {
			MessageConstants.showMessagePermissionAlert();
		}
	}

	private void bind() {
		view.getEditFormHandlers().addFormHandler(
				new EditFormHandler<Problem>() {
					@Override
					public void onSave(final Problem problem) {
						saveProblem(problem);
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ProblemEvent.GotoList(this, null));
						}
					}

					@Override
					public void onCancel() {
						ViewState viewState = HistoryViewManager.back();
						if (viewState instanceof NullViewState) {
							EventBus.getInstance().fireEvent(
									new ProblemEvent.GotoList(this, null));
						}
					}

					@Override
					public void onSaveAndNew(final Problem problem) {
						saveProblem(problem);
						EventBus.getInstance().fireEvent(
								new ProblemEvent.GotoAdd(this, null));
					}
				});
	}

	public void saveProblem(Problem problem) {
		ProblemService problemService = AppContext
				.getSpringBean(ProblemService.class);
		problem.setProjectid(CurrentProjectVariables.getProjectId());
		problem.setSaccountid(AppContext.getAccountId());

		SimpleRelayEmailNotification relayNotification = new SimpleRelayEmailNotification();
		relayNotification.setAction(MonitorTypeConstants.CREATE_ACTION);
		relayNotification.setChangeby(AppContext.getUsername());
		relayNotification.setChangecomment("");
		relayNotification.setSaccountid(AppContext.getAccountId());
		relayNotification.setType(MonitorTypeConstants.PRJ_PROBLEM);

		relayNotification
				.setEmailhandlerbean(ProjectProblemRelayEmailNotificationAction.class
						.getName());

		relayNotification.setExtratypeid(problem.getProjectid());
		List<SimpleUser> usersInProject = projectMemberService
				.getUsersInProject(problem.getProjectid(), 0);
		relayNotification.setNotifyUsers(usersInProject);

		RelayEmailNotificationService relayEmailNotificationService = AppContext
				.getSpringBean(RelayEmailNotificationService.class);

		if (problem.getId() == null) {
			Integer id = problemService.saveWithSession(problem,
					AppContext.getUsername());
			relayNotification.setTypeid(id);
			relayEmailNotificationService.saveWithSession(relayNotification,
					AppContext.getUsername());

		} else {
			relayNotification.setAction(MonitorTypeConstants.UPDATE_ACTION);
			relayNotification.setTypeid(problem.getId());
			problemService.updateWithSession(problem, AppContext.getUsername());
			relayEmailNotificationService.saveWithSession(relayNotification,
					AppContext.getUsername());
		}

	}
}
