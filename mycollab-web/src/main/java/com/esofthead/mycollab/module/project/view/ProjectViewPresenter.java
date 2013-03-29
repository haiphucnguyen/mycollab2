package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.ClassUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.bug.BugPresenter;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.milestone.MilestonePresenter;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ComponentScreenData;
import com.esofthead.mycollab.module.project.view.parameters.MessageScreenData;
import com.esofthead.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.module.project.view.people.UserGroupPresenter;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
import com.esofthead.mycollab.module.project.view.standup.StandupPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskPresenter;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.vaadin.mvp.AbstractPresenter;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Window;

public class ProjectViewPresenter extends AbstractPresenter<ProjectView> {

	private static final long serialVersionUID = 1L;

	public ProjectViewPresenter() {
		super(ProjectView.class);
	}

	@Override
	public void onGo(ComponentContainer container, ScreenData<?> data) {
		ProjectModule prjContainer = (ProjectModule) container;
		prjContainer.removeAllComponents();
		prjContainer.addComponent((Component) view);
		prjContainer.setComponentAlignment((Component) view,
				Alignment.TOP_CENTER);

		if (data.getParams() instanceof Integer) {
			if (CurrentProjectVariables.getProjectId() == (Integer) data
					.getParams()) {
				// do nothing
			} else {
				ProjectService projectService = (ProjectService) AppContext
						.getSpringBean(ProjectService.class);
				SimpleProject project = (SimpleProject) projectService
						.findProjectById((Integer) data.getParams());

				if (project == null) {
					AppContext
							.getApplication()
							.getMainWindow()
							.showNotification(
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE),
									AppContext
											.getMessage(GenericI18Enum.INFORMATION_RECORD_IS_NOT_EXISTED_MESSAGE),
									Window.Notification.TYPE_HUMANIZED_MESSAGE);
				} else {
					CurrentProjectVariables.setProject(project);
					view.constructProjectHeaderPanel(project, null);

				}
			}

		}
	}

	@Override
	protected void onDefaultStopChain() {
		ProjectDashboardPresenter presenter = PresenterResolver
				.getPresenter(ProjectDashboardPresenter.class);
		presenter.go(this.view, null);
	}

	@Override
	protected void onHandleChain(ComponentContainer container,
			PageActionChain pageActionChain) {
		ScreenData pageAction = pageActionChain.peek();

		AbstractPresenter<?> presenter = null;

		if (ClassUtils.instanceOf(pageAction, MilestoneScreenData.Read.class,
				MilestoneScreenData.Search.class,
				MilestoneScreenData.Add.class, MilestoneScreenData.Edit.class)) {
			presenter = PresenterResolver
					.getPresenter(MilestonePresenter.class);
		} else if (ClassUtils.instanceOf(pageAction,
				MessageScreenData.Read.class, MessageScreenData.Search.class)) {
			presenter = PresenterResolver.getPresenter(MessagePresenter.class);
		} else if (ClassUtils.instanceOf(pageAction,
				ProblemScreenData.Read.class, ProblemScreenData.Search.class,
				ProblemScreenData.Add.class, ProblemScreenData.Edit.class)) {
			presenter = PresenterResolver.getPresenter(ProblemPresenter.class);
		} else if (ClassUtils.instanceOf(pageAction, RiskScreenData.Read.class,
				RiskScreenData.Search.class, RiskScreenData.Add.class,
				RiskScreenData.Edit.class)) {
			presenter = PresenterResolver.getPresenter(RiskPresenter.class);
		} else if (ClassUtils.instanceOf(pageAction, TaskScreenData.Read.class,
				TaskScreenData.Edit.class,
				TaskGroupScreenData.GotoDashboard.class,
				TaskGroupScreenData.Read.class, TaskGroupScreenData.Edit.class)) {
			presenter = PresenterResolver.getPresenter(TaskPresenter.class);
		} else if (ClassUtils.instanceOf(pageAction, BugScreenData.Read.class,
				BugScreenData.GotoDashboard.class, BugScreenData.Add.class,
				BugScreenData.Edit.class, BugScreenData.Search.class,
				ComponentScreenData.Read.class, ComponentScreenData.Add.class,
				ComponentScreenData.Edit.class,
				ComponentScreenData.Search.class, VersionScreenData.Read.class,
				VersionScreenData.Search.class, VersionScreenData.Add.class,
				VersionScreenData.Edit.class)) {
			presenter = PresenterResolver.getPresenter(BugPresenter.class);
		} else if (ClassUtils.instanceOf(pageAction,
				StandupScreenData.Search.class, StandupScreenData.Add.class)) {
			presenter = PresenterResolver.getPresenter(StandupPresenter.class);
		} else if (ClassUtils.instanceOf(pageAction,
				ProjectMemberScreenData.Search.class,
				ProjectMemberScreenData.Read.class,
				ProjectRoleScreenData.Search.class,
				ProjectRoleScreenData.Add.class,
				ProjectRoleScreenData.Read.class)) {
			presenter = PresenterResolver
					.getPresenter(UserGroupPresenter.class);
		} else {
			throw new UnsupportedOperationException(
					"Not support page action chain " + pageAction);
		}

		presenter.handleChain(view, pageActionChain);
	}
}
