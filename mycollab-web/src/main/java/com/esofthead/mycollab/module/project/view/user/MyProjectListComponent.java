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
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class MyProjectListComponent extends Depot {
	private static final long serialVersionUID = 1L;

	ProjectPagedList projectList;

	public MyProjectListComponent() {
		super(LocalizationHelper
				.getMessage(ProjectCommonI18nEnum.MY_PROJECTS_TITLE),
				new VerticalLayout());

		projectList = new ProjectPagedList();
		this.bodyContent.addComponent(new LazyLoadWrapper(projectList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjects(List<Integer> prjKeys) {
		ProjectSearchCriteria searchCriteria = new ProjectSearchCriteria();
		searchCriteria.setInvolvedMember(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		projectList.setSearchCriteria(searchCriteria);
	}

	static class ProjectPagedList extends
			AbstractBeanPagedList<ProjectSearchCriteria, SimpleProject> {
		private static final long serialVersionUID = 1L;
		private final ProjectService projectService;

		public ProjectPagedList() {
			super(ProjectRowDisplayHandler.class, Integer.MAX_VALUE);

			projectService = AppContext.getSpringBean(ProjectService.class);
		}

		@Override
		public void doSearch() {
			totalCount = Integer.MAX_VALUE;

			List<SimpleProject> currentListData = projectService
					.findPagableListByCriteria(searchRequest);
			listContainer.removeAllComponents();
			int i = 0;
			try {
				for (SimpleProject item : currentListData) {
					AbstractBeanPagedList.RowDisplayHandler<SimpleProject> rowHandler = rowDisplayHandler
							.newInstance();
					Component row = rowHandler.generateRow(item, i);
					listContainer.addComponent(row);
					i++;
				}
			} catch (Exception e) {
				throw new MyCollabException(e);
			}
		}
	}

	public static class ProjectRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<SimpleProject> {

		@Override
		public Component generateRow(final SimpleProject project, int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");

			HorizontalLayout projectLayout = new HorizontalLayout();
			projectLayout.setWidth("100%");
			ButtonLink projectLink = new ButtonLink(project.getName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance().fireEvent(
									new ProjectEvent.GotoMyProject(this,
											new PageActionChain(
													new ProjectScreenData.Goto(
															project.getId()))));
						}
					});
			projectLayout.addComponent(projectLink);

			VerticalLayout projectStatusLayout = new VerticalLayout();
			projectStatusLayout.addComponent(new Label(project
					.getNumOpenTasks() + "/" + project.getNumTasks()));
			projectStatusLayout.addComponent(new Label(project
					.getNumOpenTasks() + "/" + project.getNumBugs()));
			projectLayout.addComponent(projectStatusLayout);
			header.addComponent(projectLayout);

			layout.addComponent(header);
			return layout;
		}
	}

}
