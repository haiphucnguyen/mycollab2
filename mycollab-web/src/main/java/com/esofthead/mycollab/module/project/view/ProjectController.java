package com.esofthead.mycollab.module.project.view;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.arguments.DateSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.domain.ProjectRole;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleStandupReport;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.MilestoneSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.ProblemSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.RiskSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.StandupReportSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.view.bug.BugContainer;
import com.esofthead.mycollab.module.project.view.message.MessagePresenter;
import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectRoleScreenData;
import com.esofthead.mycollab.module.project.view.parameters.StandupScreenData;
import com.esofthead.mycollab.module.project.view.problem.ProblemPresenter;
import com.esofthead.mycollab.module.project.view.task.TaskContainer;
import com.esofthead.mycollab.module.project.view.user.ProjectDashboardPresenter;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.module.tracker.domain.criteria.BugSearchCriteria;
import com.esofthead.mycollab.module.tracker.domain.criteria.ComponentSearchCriteria;
import com.esofthead.mycollab.module.tracker.domain.criteria.VersionSearchCriteria;
import com.esofthead.mycollab.vaadin.events.ApplicationEvent;
import com.esofthead.mycollab.vaadin.events.ApplicationEventListener;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.IController;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;

public class ProjectController implements IController {
	private static final long serialVersionUID = 1L;
	private ProjectContainer container;

	public ProjectController(ProjectContainer container) {
		this.container = container;
		bindProjectEvents();
		bindRiskEvents();
		bindProblemEvents();
		bindTaskListEvents();
		bindTaskEvents();
		bindBugEvents();
		bindMessageEvents();
		bindMilestoneEvents();
		bindStandupEvents();
		bindUserGroupEvents();
	}

	@SuppressWarnings("serial")
	private void bindProjectEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectEvent.GotoAdd.class;
					}

					@Override
					public void handle(ProjectEvent.GotoAdd event) {
						UserDashboardView projectView = ViewManager
								.getView(UserDashboardView.class);
						ScreenData.Add<Project> data = new ScreenData.Add<Project>(
								new Project());
						projectView.gotoMyProjectList(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectEvent.GotoEdit.class;
					}

					@Override
					public void handle(ProjectEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						SimpleProject project = (SimpleProject) event.getData();
						CurrentProjectVariables.setProject(project);
						ProjectDashboardPresenter presenter = PresenterResolver
								.getPresenter(ProjectDashboardPresenter.class);
						presenter.go(projectView, new ScreenData.Edit<Project>(
								project));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectEvent.GotoMyProject>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectEvent.GotoMyProject.class;
					}

					@Override
					public void handle(ProjectEvent.GotoMyProject event) {
						ProjectViewPresenter presenter = PresenterResolver
								.getPresenter(ProjectViewPresenter.class);
						presenter.handleChain(container,
								(PageActionChain) event.getData());
					}
				});
	}

	private void bindTaskListEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskListEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskListEvent.GotoRead.class;
					}

					@Override
					public void handle(TaskListEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.PreviewTaskListData data = new TaskContainer.PreviewTaskListData(
								(Integer) event.getData());
						projectView.gotoTaskList(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskListEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskListEvent.GotoEdit.class;
					}

					@Override
					public void handle(TaskListEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.EditTaskListData data = new TaskContainer.EditTaskListData(
								(TaskList) event.getData());
						projectView.gotoTaskList(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskListEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskListEvent.GotoAdd.class;
					}

					@Override
					public void handle(TaskListEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.AddTaskListData data = new TaskContainer.AddTaskListData(
								new TaskList());
						projectView.gotoTaskList(data);
					}
				});

		EventBus.getInstance()
				.addListener(
						new ApplicationEventListener<TaskListEvent.GotoTaskListScreen>() {
							private static final long serialVersionUID = 1L;

							@Override
							public Class<? extends ApplicationEvent> getEventType() {
								return TaskListEvent.GotoTaskListScreen.class;
							}

							@Override
							public void handle(
									TaskListEvent.GotoTaskListScreen event) {
								ProjectView projectView = ViewManager
										.getView(ProjectView.class);
								projectView.gotoTaskList(null);
							}
						});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskListEvent.ReoderTaskList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskListEvent.ReoderTaskList.class;
					}

					@Override
					public void handle(TaskListEvent.ReoderTaskList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.ReorderTaskListRequest data = new TaskContainer.ReorderTaskListRequest();
						projectView.gotoTaskList(data);
					}
				});

	}

	private void bindTaskEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskEvent.GotoRead.class;
					}

					@Override
					public void handle(TaskEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.PreviewTaskData data = new TaskContainer.PreviewTaskData(
								(Integer) event.getData());
						projectView.gotoTaskList(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskEvent.GotoAdd.class;
					}

					@Override
					public void handle(TaskEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.AddTaskData data = new TaskContainer.AddTaskData(
								new Task());
						projectView.gotoTaskList(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<TaskEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return TaskEvent.GotoEdit.class;
					}

					@Override
					public void handle(TaskEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						TaskContainer.EditTaskData data = new TaskContainer.EditTaskData(
								(Task) event.getData());
						projectView.gotoTaskList(data);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindRiskEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<RiskEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RiskEvent.GotoAdd.class;
					}

					@Override
					public void handle(RiskEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Add<Risk> data = new ScreenData.Add<Risk>(
								new Risk());
						projectView.gotoRiskView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RiskEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RiskEvent.GotoEdit.class;
					}

					@Override
					public void handle(RiskEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Edit<Risk> data = new ScreenData.Edit<Risk>(
								(Risk) event.getData());
						projectView.gotoRiskView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RiskEvent.GotoRead>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RiskEvent.GotoRead.class;
					}

					@Override
					public void handle(RiskEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
								(Integer) event.getData());
						projectView.gotoRiskView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<RiskEvent.GotoList>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return RiskEvent.GotoList.class;
					}

					@Override
					public void handle(RiskEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						RiskSearchCriteria criteria = new RiskSearchCriteria();

						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						projectView
								.gotoRiskView(new ScreenData.Search<RiskSearchCriteria>(
										criteria));
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindProblemEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProblemEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProblemEvent.GotoAdd.class;
					}

					@Override
					public void handle(ProblemEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Add<Problem> data = new ScreenData.Add<Problem>(
								new Problem());
						ProblemPresenter presenter = PresenterResolver
								.getPresenter(ProblemPresenter.class);
						presenter.go(projectView, data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProblemEvent.GotoRead>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProblemEvent.GotoRead.class;
					}

					@Override
					public void handle(ProblemEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
								(Integer) event.getData());
						ProblemPresenter presenter = PresenterResolver
								.getPresenter(ProblemPresenter.class);
						presenter.go(projectView, data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProblemEvent.GotoList>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProblemEvent.GotoList.class;
					}

					@Override
					public void handle(ProblemEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						ProblemSearchCriteria criteria = new ProblemSearchCriteria();

						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						ScreenData.Search<ProblemSearchCriteria> data = new ScreenData.Search<ProblemSearchCriteria>(
								criteria);
						ProblemPresenter presenter = PresenterResolver
								.getPresenter(ProblemPresenter.class);
						presenter.go(projectView, data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProblemEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProblemEvent.GotoEdit.class;
					}

					@Override
					public void handle(ProblemEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Edit<Problem> data = new ScreenData.Edit<Problem>(
								(Problem) event.getData());
						ProblemPresenter presenter = PresenterResolver
								.getPresenter(ProblemPresenter.class);
						presenter.go(projectView, data);
					}
				});
	}

	@SuppressWarnings("serial")
	private void bindBugEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugEvent.GotoDashboard>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugEvent.GotoDashboard.class;
					}

					@Override
					public void handle(BugEvent.GotoDashboard event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						projectView.gotoBugView(null);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugEvent.GotoAdd.class;
					}

					@Override
					public void handle(BugEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Add<SimpleBug> data = new ScreenData.Add<SimpleBug>(
								new SimpleBug());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugEvent.GotoEdit.class;
					}

					@Override
					public void handle(BugEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Edit<SimpleBug> data = new ScreenData.Edit<SimpleBug>(
								(SimpleBug) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugEvent.GotoRead>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugEvent.GotoRead.class;
					}

					@Override
					public void handle(BugEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
								(Integer) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugEvent.GotoList>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugEvent.GotoList.class;
					}

					@Override
					public void handle(BugEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						BugSearchCriteria criteria = new BugSearchCriteria();

						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						projectView
								.gotoBugView(new ScreenData.Search<BugSearchCriteria>(
										criteria));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugComponentEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugComponentEvent.GotoAdd.class;
					}

					@Override
					public void handle(BugComponentEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.AddComponentData data = new BugContainer.AddComponentData(
								new Component());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugComponentEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugComponentEvent.GotoEdit.class;
					}

					@Override
					public void handle(BugComponentEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.EditComponentData data = new BugContainer.EditComponentData(
								(Component) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugComponentEvent.GotoRead>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugComponentEvent.GotoRead.class;
					}

					@Override
					public void handle(BugComponentEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.ReadComponentData data = new BugContainer.ReadComponentData(
								(Integer) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugComponentEvent.GotoList>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugComponentEvent.GotoList.class;
					}

					@Override
					public void handle(BugComponentEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ComponentSearchCriteria criteria = new ComponentSearchCriteria();
						criteria.setProjectid(new NumberSearchField(
								CurrentProjectVariables.getProjectId()));
						projectView
								.gotoBugView(new BugContainer.SearchComponentData(
										criteria));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugVersionEvent.GotoAdd>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugVersionEvent.GotoAdd.class;
					}

					@Override
					public void handle(BugVersionEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.AddVersionData data = new BugContainer.AddVersionData(
								new Version());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugVersionEvent.GotoEdit>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugVersionEvent.GotoEdit.class;
					}

					@Override
					public void handle(BugVersionEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.EditVersionData data = new BugContainer.EditVersionData(
								(Version) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugVersionEvent.GotoRead>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugVersionEvent.GotoRead.class;
					}

					@Override
					public void handle(BugVersionEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						BugContainer.ReadVersionData data = new BugContainer.ReadVersionData(
								(Integer) event.getData());
						projectView.gotoBugView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<BugVersionEvent.GotoList>() {
					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return BugVersionEvent.GotoList.class;
					}

					@Override
					public void handle(BugVersionEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						VersionSearchCriteria criteria = new VersionSearchCriteria();
						criteria.setProjectId(new NumberSearchField(
								CurrentProjectVariables.getProjectId()));
						projectView
								.gotoBugView(new BugContainer.SearchVersionData(
										criteria));
					}
				});
	}

	private void bindMessageEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<MessageEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return MessageEvent.GotoRead.class;
					}

					@Override
					public void handle(MessageEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
								(Integer) event.getData());
						MessagePresenter presenter = PresenterResolver
								.getPresenter(MessagePresenter.class);
						presenter.go(projectView, data);
					}
				});
	}

	private void bindMilestoneEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<MilestoneEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return MilestoneEvent.GotoAdd.class;
					}

					@Override
					public void handle(MilestoneEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Add<Milestone> data = new ScreenData.Add<Milestone>(
								new Milestone());
						projectView.gotoMilestoneView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<MilestoneEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return MilestoneEvent.GotoRead.class;
					}

					@Override
					public void handle(MilestoneEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Preview<Integer> data = new ScreenData.Preview<Integer>(
								(Integer) event.getData());
						projectView.gotoMilestoneView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<MilestoneEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return MilestoneEvent.GotoList.class;
					}

					@Override
					public void handle(MilestoneEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						MilestoneSearchCriteria criteria = new MilestoneSearchCriteria();

						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						projectView
								.gotoMilestoneView(new ScreenData.Search<MilestoneSearchCriteria>(
										criteria));
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<MilestoneEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return MilestoneEvent.GotoEdit.class;
					}

					@Override
					public void handle(MilestoneEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ScreenData.Edit<Milestone> data = new ScreenData.Edit<Milestone>(
								(Milestone) event.getData());
						projectView.gotoMilestoneView(data);
					}
				});
	}

	private void bindStandupEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<StandUpEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return StandUpEvent.GotoAdd.class;
					}

					@Override
					public void handle(StandUpEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						StandupScreenData.Add data = new StandupScreenData.Add(
								new SimpleStandupReport());
						projectView.gotoStandupReportView(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<StandUpEvent.GotoList>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return StandUpEvent.GotoList.class;
					}

					@Override
					public void handle(StandUpEvent.GotoList event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);

						StandupReportSearchCriteria criteria = new StandupReportSearchCriteria();

						criteria.setProjectId(new NumberSearchField(
								SearchField.AND, CurrentProjectVariables
										.getProjectId()));
						criteria.setOnDate(new DateSearchField(SearchField.AND,
								new GregorianCalendar().getTime()));
						projectView
								.gotoStandupReportView(new StandupScreenData.Search(
										criteria));
					}
				});
	}

	private void bindUserGroupEvents() {
		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectRoleEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectRoleEvent.GotoAdd.class;
					}

					@Override
					public void handle(ProjectRoleEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectRoleScreenData.Add data = new ProjectRoleScreenData.Add(
								new ProjectRole());
						projectView.gotoUsersAndGroup(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectRoleEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectRoleEvent.GotoEdit.class;
					}

					@Override
					public void handle(ProjectRoleEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectRoleScreenData.Add data = new ProjectRoleScreenData.Add(
								(ProjectRole) event.getData());
						projectView.gotoUsersAndGroup(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectRoleEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectRoleEvent.GotoRead.class;
					}

					@Override
					public void handle(ProjectRoleEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectRoleScreenData.Read data = new ProjectRoleScreenData.Read(
								(Integer) event.getData());
						projectView.gotoUsersAndGroup(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectMemberEvent.GotoRead>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectMemberEvent.GotoRead.class;
					}

					@Override
					public void handle(ProjectMemberEvent.GotoRead event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectMemberScreenData.Read data = new ProjectMemberScreenData.Read(
								(Integer) event.getData());
						projectView.gotoUsersAndGroup(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectMemberEvent.GotoAdd>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectMemberEvent.GotoAdd.class;
					}

					@Override
					public void handle(ProjectMemberEvent.GotoAdd event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectMemberScreenData.Add data = new ProjectMemberScreenData.Add(
								new ProjectMember());
						projectView.gotoUsersAndGroup(data);
					}
				});

		EventBus.getInstance().addListener(
				new ApplicationEventListener<ProjectMemberEvent.GotoEdit>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Class<? extends ApplicationEvent> getEventType() {
						return ProjectMemberEvent.GotoEdit.class;
					}

					@Override
					public void handle(ProjectMemberEvent.GotoEdit event) {
						ProjectView projectView = ViewManager
								.getView(ProjectView.class);
						ProjectMemberScreenData.Add data = new ProjectMemberScreenData.Add(
								(ProjectMember) event.getData());
						projectView.gotoUsersAndGroup(data);
					}
				});
	}
}
