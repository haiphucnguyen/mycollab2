package com.esofthead.mycollab.module.project.view.user;

import java.util.List;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.localization.ProjectCommonI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.ButtonLink;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProgressBar;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

public class MyProjectListComponent extends Depot {
	private static final long serialVersionUID = 1L;

	ProjectPagedList projectList;

	public MyProjectListComponent() {
		super(LocalizationHelper
				.getMessage(ProjectCommonI18nEnum.MY_PROJECTS_TITLE),
				new VerticalLayout());

		this.projectList = new ProjectPagedList();
		this.bodyContent.addComponent(new LazyLoadWrapper(this.projectList));
		this.addStyleName("activity-panel");
		this.addStyleName("myprojectlist");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjects(final List<Integer> prjKeys) {
		final ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
		searchCriteria.setInvolvedMember(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		this.projectList.setSearchCriteria(searchCriteria);
	}

	static class ProjectPagedList extends
			AbstractBeanPagedList<ProjectSearchCriteria, SimpleProject> {
		private static final long serialVersionUID = 1L;
		private final ProjectService projectService;

		public ProjectPagedList() {
			super(ProjectRowDisplayHandler.class, Integer.MAX_VALUE);

			this.projectService = AppContext
					.getSpringBean(ProjectService.class);
		}

		@Override
		public void doSearch() {
			this.totalCount = Integer.MAX_VALUE;

			final List<SimpleProject> currentListData = this.projectService
					.findPagableListByCriteria(this.searchRequest);
			this.listContainer.removeAllComponents();
			int i = 0;
			try {
				for (final SimpleProject item : currentListData) {
					final AbstractBeanPagedList.RowDisplayHandler<SimpleProject> rowHandler = this.rowDisplayHandler
							.newInstance();
					final Component row = rowHandler.generateRow(item, i);
					this.listContainer.addComponent(row);
					i++;
				}
			} catch (final Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	public static class ProjectRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleProject> {

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
			linkWrapper.setWidth("145px");
			linkWrapper.setHeight("100%");
			linkWrapper.addStyleName("projectlink-wrapper");
			final ButtonLink projectLink = new ButtonLink(project.getName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(final ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new ProjectEvent.GotoMyProject(this,
											new PageActionChain(
													new ProjectScreenData.Goto(
															project.getId()))));
						}
					});
			linkWrapper.addComponent(projectLink);
			projectLayout.addComponent(linkWrapper);

			final VerticalLayout projectStatusLayout = new VerticalLayout();
			projectStatusLayout.setSpacing(true);

			final HorizontalLayout taskStatus = new HorizontalLayout();
			taskStatus.setWidth("100%");
			taskStatus.setSpacing(true);
			// final ProgressIndicator progressTask = new ProgressIndicator(
			// new Float((float) (project.getNumTasks() - project
			// .getNumOpenTasks()) / project.getNumTasks()));
			// progressTask.setPollingInterval(1000000000);
			// progressTask.setWidth("100%");
			// taskStatus.addComponent(progressTask);
			// taskStatus.setExpandRatio(progressTask, 1.0f);
			// final Label taskStatusLabel = new Label(project.getNumOpenTasks()
			// + "/" + project.getNumTasks());
			// taskStatusLabel.setWidth("90px");
			final ProgressBar progressTask = new ProgressBar(
					project.getNumTasks(), project.getNumOpenTasks());
			progressTask.setWidth("100%");
			taskStatus.addComponent(progressTask);
			projectStatusLayout.addComponent(taskStatus);

			final HorizontalLayout bugStatus = new HorizontalLayout();
			bugStatus.setWidth("100%");
			bugStatus.setSpacing(true);
			// final ProgressIndicator progressBug = new ProgressIndicator(
			// new Float((float) (project.getNumBugs() - project
			// .getNumOpenBugs()) / project.getNumBugs()));
			// progressBug.setPollingInterval(1000000000);
			// progressBug.setWidth("100%");
			// bugStatus.addComponent(progressBug);
			// bugStatus.setExpandRatio(progressBug, 1.0f);
			// final Label bugStatusLabel = new Label(project.getNumOpenTasks()
			// + "/" + project.getNumBugs());
			// bugStatusLabel.setWidth("90px");
			// bugStatus.addComponent(bugStatusLabel);
			final ProgressBar progressBug = new ProgressBar(
					project.getNumBugs(), project.getNumOpenBugs());
			progressBug.setWidth("100%");
			bugStatus.addComponent(progressBug);
			projectStatusLayout.addComponent(bugStatus);

			projectLayout.addComponent(projectStatusLayout);
			projectStatusLayout.setWidth("100%");

			projectLayout.setExpandRatio(projectStatusLayout, 1.0f);

			header.addComponent(projectLayout);

			layout.addComponent(header);
			return layout;
		}
	}

}
