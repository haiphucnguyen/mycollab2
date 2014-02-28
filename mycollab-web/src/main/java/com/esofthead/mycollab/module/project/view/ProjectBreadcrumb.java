/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.project.view;

import java.util.Date;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.project.domain.Message;
import com.esofthead.mycollab.module.project.domain.Milestone;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.SimpleProjectRole;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.events.BugComponentEvent;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.BugVersionEvent;
import com.esofthead.mycollab.module.project.events.MessageEvent;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.events.ProjectMemberEvent;
import com.esofthead.mycollab.module.project.events.ProjectNotificationEvent;
import com.esofthead.mycollab.module.project.events.ProjectRoleEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.StandUpEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.tracker.domain.BugWithBLOBs;
import com.esofthead.mycollab.module.tracker.domain.Component;
import com.esofthead.mycollab.module.tracker.domain.Version;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.CacheableComponent;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.lexaden.breadcrumb.Breadcrumb;
import com.lexaden.breadcrumb.BreadcrumbLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class ProjectBreadcrumb extends Breadcrumb implements CacheableComponent {
	private static final long serialVersionUID = 1L;
	private static LabelStringGenerator menuLinkGenerator = new BreadcrumbLabelStringGenerator();

	private SimpleProject project;

	private Button homeBtn;

	public ProjectBreadcrumb() {
		this.setShowAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setHideAnimationSpeed(Breadcrumb.AnimSpeed.SLOW);
		this.setUseDefaultClickBehaviour(false);
		homeBtn = new Button(null, new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new ProjectEvent.GotoMyProject(this,
								new PageActionChain(
										new ProjectScreenData.Goto(project.getId())
										)
								)
						);
			}
		});
		this.addLink(homeBtn);
	}

	@Override
	public void addLink(Button newBtn) {
		if (getComponentCount() > 0)
			homeBtn.setCaption(null);
		else
			homeBtn.setCaption("Dashboard");

		super.addLink(newBtn);
	}

	@Override
	public void select(int id) {
		if (id == 0) {
			homeBtn.setCaption("Dashboard");
		} else {
			homeBtn.setCaption(null);
		}
		super.select(id);
	}

	public void setProject(SimpleProject project) {
		this.project = project;
		initBreadcrumb();
	}

	private void initBreadcrumb() {
		this.select(0);
		/*PopupButton projectSelectionPopupBtn = CommonUIFactory
				.createPopupButtonTooltip(
						menuLinkGenerator.handleText(project.getName()),
						project.getName());

		VerticalLayout filterBtnLayout = new VerticalLayout();
		filterBtnLayout.setMargin(true);
		filterBtnLayout.setSpacing(true);
		filterBtnLayout.setWidth("260px");

		ProjectService projectService = ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		ProjectSearchCriteria criteria = new ProjectSearchCriteria();
		criteria.setInvolvedMember(new StringSearchField(StringSearchField.AND,
				AppContext.getUsername()));
		List<SimpleProject> projects = projectService
				.findPagableListByCriteria(new SearchRequest<ProjectSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));

		for (final SimpleProject item : projects) {
			Button btnProject = generateBreadcrumbLink(item.getName(),
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					EventBus.getInstance().fireEvent(
							new ProjectEvent.GotoMyProject(this,
									new PageActionChain(
											new ProjectScreenData.Goto(
													item.getId()))));
				}
			});

			btnProject.setStyleName("link");
			filterBtnLayout.addComponent(btnProject);
		}

		projectSelectionPopupBtn.setContent(filterBtnLayout);
		this.addLink(projectSelectionPopupBtn);
		this.setLinkEnabled(true, 1);*/
	}

	public void gotoMessageList() {
		this.select(0);
		this.addLink(new Button("Messages"));
		AppContext.addFragment(
				"project/message/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Message List");
	}

	public void gotoMessage(Message message) {
		this.select(0);
		this.addLink(new Button("Messages", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new MessageEvent.GotoList(this, null));
			}
		}));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(message.getTitle()));
		AppContext.addFragment(ProjectLinkUtils.generateMessagePreviewLink(
				project.getId(), message.getId()), "Preview Message: "
						+ message.getTitle());
	}

	public void gotoRiskList() {
		this.select(0);
		this.addLink(new Button("Risks"));
		AppContext
		.addFragment(
				"project/risk/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Risk List");
	}

	public void gotoRiskRead(Risk risk) {
		this.select(0);
		this.addLink(new Button("Risks", new GotoRiskListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(risk.getRiskname()));
		AppContext.addFragment(
				ProjectLinkUtils.generateRiskPreview(project.getId(),
						risk.getId()), "Preview Risk: " + risk.getRiskname());
	}

	public void gotoRiskEdit(final Risk risk) {
		this.select(0);
		this.addLink(new Button("Risks", new GotoRiskListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(risk.getRiskname(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new RiskEvent.GotoRead(this, risk.getId()));
			}
		}));
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/risk/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ risk.getId()),
								"Edit Risk: " + risk.getRiskname());
	}

	public void gotoRiskAdd() {
		this.select(0);
		this.addLink(new Button("Risks", new GotoRiskListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/risk/add/" + UrlEncodeDecoder.encode(project.getId()),
				"Risk Add");
	}

	private static class GotoRiskListListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance()
			.fireEvent(new RiskEvent.GotoList(this, null));
		}
	}

	public void gotoMilestoneList() {
		this.select(0);
		this.addLink(new Button("Phases"));
		AppContext.addFragment(
				"project/milestone/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Phase List");
	}

	public void gotoMilestoneRead(Milestone milestone) {
		this.select(0);
		this.addLink(new Button("Phases", new GotoMilestoneListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(milestone.getName()));
		AppContext.addFragment(ProjectLinkUtils.generateMilestonePreviewLink(
				project.getId(), milestone.getId()), "Preview Phase: "
						+ milestone.getName());
	}

	public void gotoMilestoneEdit(final Milestone milestone) {
		this.select(0);
		this.addLink(new Button("Phases", new GotoMilestoneListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(milestone.getName(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new MilestoneEvent.GotoRead(this, milestone
								.getId()));
			}
		}));
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/milestone/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ milestone.getId()), "Edit Phase: "
										+ milestone.getName());
	}

	public void gotoMilestoneAdd() {
		this.select(0);
		this.addLink(new Button("Phases", new GotoMilestoneListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext
		.addFragment(
				"project/milestone/add/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Add Phase");
	}

	private static class GotoMilestoneListListener implements
	Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new MilestoneEvent.GotoList(this, null));
		}
	}

	public void gotoProblemList() {
		this.select(0);
		this.addLink(new Button("Problems"));
		AppContext.addFragment(
				"project/problem/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Problem List");
	}

	public void gotoProblemRead(Problem problem) {
		this.select(0);
		this.addLink(new Button("Problems", new GotoProblemListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(problem.getIssuename()));
		AppContext.addFragment(ProjectLinkUtils.generateProblemPreviewLink(
				project.getId(), problem.getId()), "Preview Problem: "
						+ problem.getIssuename());
	}

	public void gotoProblemEdit(final Problem problem) {
		this.select(0);
		this.addLink(new Button("Problems", new GotoProblemListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(problem.getIssuename(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance()
				.fireEvent(
						new ProblemEvent.GotoRead(this, problem
								.getId()));
			}
		}));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/problem/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ problem.getId()),
								"Edit Problem: " + problem.getIssuename());
	}

	public void gotoProblemAdd() {
		this.select(0);
		this.addLink(new Button("Problems", new GotoProblemListListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/problem/add/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"New Problem");
	}

	private static class GotoProblemListListener implements
	Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new ProblemEvent.GotoList(this, null));
		}
	}

	public void gotoTaskDashboard() {
		this.select(0);
		this.addLink(new Button("Task Assignments"));
		AppContext.addFragment(
				"project/task/dashboard/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Task Dashboard");
	}

	public void gotoTaskListReorder() {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Task Group: Reorder"));
		AppContext.addFragment("project/task/dashboard/reorder/"
				+ UrlEncodeDecoder.encode(project.getId()),
				"ReOrder Task Group");
	}

	public void gotoTaskGroupAdd() {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Task Group: Add"));
		AppContext.addFragment(
				"project/task/taskgroup/add/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"New TaskGroup");
	}

	public void gotoTaskGroupRead(TaskList taskList) {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink("Task Group: " + taskList.getName()));
		AppContext.addFragment(ProjectLinkUtils.generateTaskGroupPreviewLink(
				project.getId(), taskList.getId()),
				"TaskGroup: " + taskList.getName());
	}

	public void gotoTaskGroupEdit(final TaskList taskList) {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(
				"Task Group: " + taskList.getName(),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						EventBus.getInstance().fireEvent(
								new TaskListEvent.GotoRead(this, taskList
										.getId()));
					}
				}));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/task/taskgroup/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ taskList.getId()), "Edit TaskGroup: "
										+ taskList.getName());
	}

	public void gotoTaskAdd() {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Task: Add"));
		AppContext.addFragment(
				"project/task/task/add/"
						+ UrlEncodeDecoder.encode(project.getId()), "New Task");
	}

	public void gotoTaskRead(Task task) {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink("Task: " + task.getTaskname()));
		AppContext.addFragment(
				ProjectLinkUtils.generateTaskPreviewLink(project.getId(),
						task.getId()), "Task: " + task.getTaskname());
	}

	public void gotoTaskEdit(final Task task) {
		this.select(0);
		this.addLink(new Button("Task Assignments",
				new GotoTaskAssignmentDashboard()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink("Task: " + task.getTaskname(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskEvent.GotoRead(this, task.getId()));
			}
		}));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/task/task/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ task.getId()),
								"Edit Task: " + task.getTaskname());
	}

	public class GotoTaskAssignmentDashboard implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new TaskListEvent.GotoTaskListScreen(this, null));
		}
	}

	public void gotoBugDashboard() {
		this.select(0);
		this.addLink(new Button("Bugs"));
		AppContext.addFragment(
				"project/bug/dashboard/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Bug Dashboard");
	}

	public void gotoBugList() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("List"));
		AppContext.addFragment(
				"project/bug/list/" + UrlEncodeDecoder.encode(project.getId()),
				"Bug List");
	}

	public void gotoBugAdd() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/bug/add/" + UrlEncodeDecoder.encode(project.getId()),
				"New Bug");
	}

	public void gotoBugEdit(final BugWithBLOBs bug) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(bug.getSummary(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugEvent.GotoRead(this, bug.getId()));
			}
		}));
		this.addLink(new Button("Edit"));
		AppContext
		.addFragment(
				"project/bug/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ bug.getId()),
								"Edit Bug: " + bug.getSummary());
	}

	public void gotoBugRead(BugWithBLOBs bug) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(bug.getSummary()));
		AppContext.addFragment(
				ProjectLinkUtils.generateBugPreviewLink(bug.getProjectid(),
						bug.getId()), "Preview Bug: " + bug.getSummary());
	}

	public void gotoVersionList() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Versions"));
		AppContext.addFragment(
				"project/bug/version/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Version List");
	}

	public void gotoVersionAdd() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Versions", new GotoVersionListener()));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/bug/version/add/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"New Version");
	}

	public void gotoVersionEdit(final Version version) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Versions", new GotoVersionListener()));
		this.setLinkEnabled(true, 2);
		this.addLink(generateBreadcrumbLink(version.getVersionname(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugVersionEvent.GotoRead(this, version
								.getId()));
			}
		}));
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/bug/version/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ version.getId()),
								"Edit Version: " + version.getVersionname());
	}

	public void gotoVersionRead(Version version) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Versions", new GotoVersionListener()));
		this.setLinkEnabled(true, 2);
		this.addLink(generateBreadcrumbLink(version.getVersionname()));
		AppContext.addFragment(ProjectLinkUtils.generateBugVersionPreviewLink(
				project.getId(), version.getId()), "Preview Version: "
						+ version.getVersionname());
	}

	private class GotoVersionListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new BugVersionEvent.GotoList(this, null));
		}
	}

	public void gotoComponentList() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Components"));
		AppContext.addFragment(
				"project/bug/component/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Component List");
	}

	public void gotoComponentAdd() {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Components", new GotoComponentListener()));
		this.setLinkEnabled(true, 2);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/bug/component/add/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"New Component");
	}

	public void gotoComponentEdit(final Component component) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Components", new GotoComponentListener()));
		this.setLinkEnabled(true, 2);
		this.addLink(generateBreadcrumbLink(component.getComponentname(),
				new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new BugComponentEvent.GotoRead(this, component
								.getId()));
			}
		}));
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/bug/component/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ component.getId()), "Edit Component: "
										+ component.getComponentname());
	}

	public void gotoComponentRead(Component component) {
		this.select(0);
		this.addLink(new Button("Bugs", new GotoBugDashboardListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Components", new GotoComponentListener()));
		this.addLink(generateBreadcrumbLink(component.getComponentname()));
		AppContext.addFragment(
				ProjectLinkUtils.generateBugComponentPreviewLink(
						project.getId(), component.getId()),
						"Preview Component: " + component.getComponentname());
	}

	public void gotoTimeTrackingList() {
		this.select(0);
		this.addLink(new Button("Time Tracking"));
		AppContext
		.addFragment(
				"project/time/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Time Tracking");
	}

	public void gotoFileList() {
		this.select(0);
		this.addLink(new Button("Files"));
		AppContext.addFragment(
				"project/file/dashboard/"
						+ UrlEncodeDecoder.encode(project.getId()), "Files");
	}

	public void gotoStandupList() {
		this.select(0);
		this.addLink(new Button("Standups"));
		AppContext.addFragment(
				"project/standup/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Standup List");
	}

	public void gotoStandupAdd(Date date) {
		this.select(0);
		this.addLink(new Button("Standups", new GotoStandupListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));

		AppContext.addFragment(
				"project/standup/add/"
						+ UrlEncodeDecoder.encode(CurrentProjectVariables
								.getProjectId()
								+ "/"
								+ AppContext.formatDate(date)),
								"Standup Report for " + AppContext.formatDate(date));
	}

	public void gotoUserList() {
		this.select(0);
		this.addLink(new Button("Users"));
		AppContext
		.addFragment(
				"project/user/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Project Members");
	}

	public void gotoUserAdd() {
		this.select(0);
		this.addLink(new Button("Users", new GotoUserListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Invite Project Members"));
		AppContext.addFragment(
				"project/user/add/" + UrlEncodeDecoder.encode(project.getId()),
				"Invite Project Member(s)");
	}

	public void gotoUserRead(SimpleProjectMember member) {
		this.select(0);
		this.addLink(new Button("Users", new GotoUserListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(member.getMemberFullName()));
		AppContext.addFragment(
				"project/user/preview/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ member.getUsername()), "Project Member: "
										+ member.getMemberFullName());
	}

	public void gotoUserEdit(SimpleProjectMember member) {
		this.select(0);
		this.addLink(new Button("Users", new GotoUserListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(member.getMemberFullName()));
		AppContext.addFragment(
				"project/user/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ member.getId()), "Edit Project Member: "
										+ member.getMemberFullName());
	}

	public void gotoRoleList() {
		this.select(0);
		this.addLink(new Button("Roles"));
		AppContext
		.addFragment(
				"project/role/list/"
						+ UrlEncodeDecoder.encode(project.getId()),
				"Project Roles");
	}

	public void gotoRoleRead(SimpleProjectRole role) {
		this.select(0);
		this.addLink(new Button("Roles", new GotoRoleListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(role.getRolename()));
		AppContext.addFragment(
				"project/role/preview/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ role.getId()),
								"Project Role: " + role.getRolename());
	}

	public void gotoNotificationSetting(ProjectNotificationSetting notify) {
		this.select(0);
		this.addLink(new Button("Notification Setting",
				new GotoNotificationSetttingListener()));
		AppContext.addFragment("project/setting/notification/"
				+ UrlEncodeDecoder.encode(project.getId()),
				"Notification Setting");
	}

	public void gotoRoleAdd() {
		this.select(0);
		this.addLink(new Button("Roles", new GotoRoleListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(new Button("Add"));
		AppContext.addFragment(
				"project/role/add/" + UrlEncodeDecoder.encode(project.getId()),
				"New Project Role");
	}

	public void gotoRoleEdit(SimpleProjectRole role) {
		this.select(0);
		this.addLink(new Button("Roles", new GotoUserListener()));
		this.setLinkEnabled(true, 1);
		this.addLink(generateBreadcrumbLink(role.getRolename()));
		AppContext.addFragment(
				"project/role/edit/"
						+ UrlEncodeDecoder.encode(project.getId() + "/"
								+ role.getId()),
								"Edit Project Role: " + role.getRolename());
	}

	private static class GotoNotificationSetttingListener implements
	Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new ProjectNotificationEvent.GotoList(this, null));
		}
	}

	private static class GotoRoleListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new ProjectRoleEvent.GotoList(this, null));
		}
	}

	private static class GotoStandupListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new StandUpEvent.GotoList(this, null));
		}
	}

	private static class GotoUserListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new ProjectMemberEvent.GotoList(this, null));
		}
	}

	private static class GotoComponentListener implements Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new BugComponentEvent.GotoList(this, null));
		}
	}

	private static class GotoBugDashboardListener implements
	Button.ClickListener {
		private static final long serialVersionUID = 1L;

		@Override
		public void buttonClick(ClickEvent event) {
			EventBus.getInstance().fireEvent(
					new BugEvent.GotoDashboard(this, null));
		}
	}

	public void gotoProjectDashboard() {
		this.select(0);
		AppContext.addFragment(
				ProjectLinkUtils.generateProjectLink(project.getId()),
				"Dashboard");
	}

	public void gotoProjectEdit() {
		this.select(0);
		this.addLink(new Button("Edit"));
		AppContext.addFragment(
				"project/edit/" + UrlEncodeDecoder.encode(project.getId()),
				"Edit Project: " + project.getName());
	}

	@Override
	public int getComponentCount() {
		if (getCompositionRoot() != null) {
			final BreadcrumbLayout compositionRoot = (BreadcrumbLayout) getCompositionRoot();
			return compositionRoot.getComponentCount();
		}
		return super.getComponentCount();
	}

	private static Button generateBreadcrumbLink(String linkname) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname);
	}

	private static Button generateBreadcrumbLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname, listener);
	}

	private static class BreadcrumbLabelStringGenerator implements
	LabelStringGenerator {

		@Override
		public String handleText(String value) {
			if (value.length() > 35) {
				return value.substring(0, 35) + "...";
			}
			return value;
		}

	}
}
