/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class ProjectTaskStatusComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> taskList;

	public ProjectTaskStatusComponent() {
		super("My Openned Tasks", new VerticalLayout());

		taskList = new DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask>(
				AppContext.getSpringBean(ProjectGenericTaskService.class),
				TaskStatusComponent.ActivityStreamRowDisplayHandler.class, 15);
		this.bodyContent.addComponent(new LazyLoadWrapper(taskList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectTasksByStatus() {
		ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setsAccountId(new NumberSearchField(AppContext
				.getAccountId()));
		searchCriteria.setStatuses(new SetSearchField<String>(SearchField.AND,
				new String[] { ProjectGenericTaskSearchCriteria.OPEN_STATUS }));
		searchCriteria.setAssignUser(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		taskList.setSearchCriteria(searchCriteria);
	}

	public static class ActivityStreamRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<ProjectGenericTask> {

		@Override
		public Component generateRow(ProjectGenericTask genericTask,
				int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			HorizontalLayout header = new HorizontalLayout();
			header.setSpacing(true);

			Button taskLink = new Button(genericTask.getName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {

						}
					});
			taskLink.setIcon(ProjectResources.getIconResource16size(genericTask
					.getType()));
			taskLink.setStyleName("link");
			header.addComponent(taskLink);

			layout.addComponent(header);

			CssLayout body = new CssLayout();
			body.setStyleName("activity-date");
			Label dateLbl = new Label("Last updated on "
					+ DateTimeUtils.getStringDateFromNow(genericTask
							.getLastUpdatedTime()));
			body.addComponent(dateLbl);

			layout.addComponent(body);

			return layout;
		}
	}
}
