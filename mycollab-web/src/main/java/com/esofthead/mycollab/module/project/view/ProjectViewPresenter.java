package com.esofthead.mycollab.module.project.view;

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
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.VersionScreenData;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.risk.RiskPresenter;
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
		ProjectContainer prjContainer = (ProjectContainer) container;
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
							.showNotification("Information",
									"The record is not existed",
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
				MilestoneScreenData.Search.class)) {
			presenter = PresenterResolver
					.getPresenter(MilestonePresenter.class);
		} else if (ClassUtils.instanceOf(pageAction,
				MessageScreenData.Read.class, MessageScreenData.Search.class)) {
			presenter = PresenterResolver.getPresenter(MessagePresenter.class);
		} else if (pageAction instanceof ProblemScreenData.Read) {
			presenter = PresenterResolver.getPresenter(ProblemPresenter.class);
		} else if (pageAction instanceof RiskScreenData.Read) {
			presenter = PresenterResolver.getPresenter(RiskPresenter.class);
		} else if (pageAction instanceof TaskScreenData.Read) {
			presenter = PresenterResolver.getPresenter(TaskPresenter.class);
		} else if (pageAction instanceof TaskGroupScreenData.Read) {
			presenter = PresenterResolver.getPresenter(TaskPresenter.class);
		} else if (pageAction instanceof BugScreenData.Read) {
			presenter = PresenterResolver.getPresenter(BugPresenter.class);
		} else if (pageAction instanceof ComponentScreenData.Read) {
			presenter = PresenterResolver.getPresenter(BugPresenter.class);
		} else if (pageAction instanceof VersionScreenData.Read) {
			presenter = PresenterResolver.getPresenter(BugPresenter.class);
		} else {
			throw new UnsupportedOperationException(
					"Not support page action chain " + pageAction);
		}

		presenter.handleChain(view, pageActionChain);
	}

}
