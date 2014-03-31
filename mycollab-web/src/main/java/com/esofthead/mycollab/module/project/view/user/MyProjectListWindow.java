package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.ProjectStatusConstants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.MilestoneScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectMemberScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskGroupScreenData;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.ProgressBarIndicator;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class MyProjectListWindow extends Window {
	private static final long serialVersionUID = -3927621612074942453L;
	ProjectPagedList projectList;

	public MyProjectListWindow() {
		super("My Project");

		VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("myprojectlist");
		layout.setMargin(true);
		layout.setWidth("550px");

		projectList = new ProjectPagedList();
		projectList.setWidth("100%");
		layout.addComponent(projectList);
		this.setContent(layout);
		this.setModal(true);
		this.center();
	}

	private class ProjectPagedList extends
	BeanList<ProjectService, ProjectSearchCriteria, SimpleProject> {
		private static final long serialVersionUID = 1L;

		public ProjectPagedList() {
			super(null, ApplicationContextUtil
					.getSpringBean(ProjectService.class),
					ProjectRowDisplayHandler.class);
		}
	}

	public class ProjectRowDisplayHandler implements
	BeanList.RowDisplayHandler<SimpleProject> {
		private static final long serialVersionUID = 1L;

		@Override
		public Component generateRow(final SimpleProject project,
				final int rowIndex) {
			final CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			final CssLayout header = new CssLayout();
			header.setStyleName("stream-content");
			header.setWidth("100%");

			final HorizontalLayout projectLayout = new HorizontalLayout();
			projectLayout.setWidth("100%");
			projectLayout.addStyleName("project-status");

			final CssLayout linkWrapper = new CssLayout();
			linkWrapper.setWidth("200px");
			linkWrapper.addStyleName("projectlink-wrapper");
			final VerticalLayout linkIconFix = new VerticalLayout();
			linkIconFix.setWidth("100%");
			final ButtonLink projectLink = new ButtonLink(project.getName(),
					new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(final ClickEvent event) {
					closeWindowParent(event);
					EventBus.getInstance().fireEvent(
							new ProjectEvent.GotoMyProject(this,
									new PageActionChain(
											new ProjectScreenData.Goto(
													project.getId()))));
				}
			}, false);
			projectLink.addStyleName("project-name");
			linkIconFix.addComponent(projectLink);
			linkIconFix.setExpandRatio(projectLink, 1.0f);

			ButtonLink projectMember = new ButtonLink(project.getNumActiveMembers() + " member" + (project.getNumActiveMembers() > 1 ? "s" : ""), new Button.ClickListener() {
				private static final long serialVersionUID = -7865685578305013464L;

				@Override
				public void buttonClick(ClickEvent event) {
					closeWindowParent(event);
					EventBus.getInstance().fireEvent(
							new ProjectEvent.GotoMyProject(this, 
									new PageActionChain(
											new ProjectScreenData.Goto(
													project.getId()), new ProjectMemberScreenData.Search(null))));
				}
			}, false);
			linkIconFix.addComponent(projectMember);
			linkIconFix.addComponent(new Label("Created on: " + AppContext.formatDate(project.getCreatedtime())));

			projectLink.setWidth("100%");
			linkWrapper.addComponent(linkIconFix);
			projectLayout.addComponent(linkWrapper);

			final VerticalLayout projectStatusLayout = new VerticalLayout();
			projectStatusLayout.setSpacing(true);
			projectStatusLayout.setMargin(true);

			final HorizontalLayout taskStatus = new HorizontalLayout();
			taskStatus.setWidth("100%");
			taskStatus.setSpacing(true);
			final ButtonLink taskLbl = new ButtonLink("Tasks :", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					closeWindowParent(event);
					EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, 
							new PageActionChain(
									new ProjectScreenData.Goto(project.getId()),
									new TaskGroupScreenData.GotoDashboard())));					
				}
			}, false);
			final Image taskIcon = new Image(null,
					MyCollabResource.newResource("icons/16/project/task.png"));
			taskStatus.addComponent(taskIcon);
			taskLbl.setWidth("45px");
			taskStatus.addComponent(taskLbl);
			final ProgressBarIndicator progressTask = new ProgressBarIndicator(
					project.getNumTasks(), project.getNumOpenTasks());
			progressTask.setWidth("100%");
			taskStatus.addComponent(progressTask);
			taskStatus.setExpandRatio(progressTask, 1.0f);
			projectStatusLayout.addComponent(taskStatus);

			final HorizontalLayout bugStatus = new HorizontalLayout();
			bugStatus.setWidth("100%");
			bugStatus.setSpacing(true);
			final ButtonLink bugLbl = new ButtonLink("Bugs :", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					closeWindowParent(event);
					EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, 
							new PageActionChain(
									new ProjectScreenData.Goto(project.getId()),
									new BugScreenData.GotoDashboard())));					
				}
			}, false);

			final Image bugIcon = new Image(null,
					MyCollabResource.newResource("icons/16/project/bug.png"));
			bugStatus.addComponent(bugIcon);
			bugLbl.setWidth("45px");
			bugStatus.addComponent(bugLbl);
			final ProgressBarIndicator progressBug = new ProgressBarIndicator(
					project.getNumBugs(), project.getNumOpenBugs());
			progressBug.setWidth("100%");
			bugStatus.addComponent(progressBug);
			bugStatus.setExpandRatio(progressBug, 1.0f);
			projectStatusLayout.addComponent(bugStatus);

			HorizontalLayout phaseStatus = new HorizontalLayout();
			phaseStatus.setWidth("100%");
			phaseStatus.setSpacing(true);
			phaseStatus.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
			Image phaseIcon = new Image(null, MyCollabResource.newResource("icons/16/project/milestone.png"));
			phaseStatus.addComponent(phaseIcon);
			ButtonLink phaseLbl = new ButtonLink("Phases: ", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					closeWindowParent(event);
					EventBus.getInstance().fireEvent(new ProjectEvent.GotoMyProject(this, 
							new PageActionChain(
									new ProjectScreenData.Goto(project.getId()),
									new MilestoneScreenData.Search(null))));
				}
			}, false);
			phaseStatus.addComponent(phaseLbl);
			Label phaseProgress = new Label(project.getNumClosedPhase() + " Closed - " + project.getNumInProgressPhase() + " In Progress - " + project.getNumFuturePhase() + " Future");
			phaseStatus.addComponent(phaseProgress);
			phaseStatus.setExpandRatio(phaseProgress, 1.0f);
			projectStatusLayout.addComponent(phaseStatus);

			projectLayout.addComponent(projectStatusLayout);
			projectStatusLayout.setWidth("100%");

			projectLayout.setExpandRatio(projectStatusLayout, 1.0f);

			header.addComponent(projectLayout);

			layout.addComponent(header);
			return layout;
		}

		protected void closeWindowParent(ClickEvent evt) {
			Component parent = evt.getButton().getParent();
			while (parent != null) {
				if (parent instanceof Window) {
					((Window) parent).close();
					return;
				}
				parent = parent.getParent();
			}
		}
	}

	@Override
	public void attach() {
		super.attach();

		final ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
		searchCriteria.setInvolvedMember(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		searchCriteria.setProjectStatuses(new SetSearchField<String>(
				new String[] { ProjectStatusConstants.OPEN }));
		this.projectList.setSearchCriteria(searchCriteria);
	}



}
