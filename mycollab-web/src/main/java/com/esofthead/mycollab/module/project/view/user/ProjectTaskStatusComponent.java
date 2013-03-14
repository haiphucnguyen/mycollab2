/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.user;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.BugEvent;
import com.esofthead.mycollab.module.project.events.ProblemEvent;
import com.esofthead.mycollab.module.project.events.RiskEvent;
import com.esofthead.mycollab.module.project.events.TaskEvent;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
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
	private static LabelStringGenerator menuLinkGenerator = new TaskStatusLinkLabelStringGenerator();

	public ProjectTaskStatusComponent() {
		super("My Openned Tasks", new VerticalLayout());

		taskList = new DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask>(
				AppContext.getSpringBean(ProjectGenericTaskService.class),
				TaskRowDisplayHandler.class, 10);
		this.bodyContent.addComponent(new LazyLoadWrapper(taskList));
		this.addStyleName("activity-panel");
		((VerticalLayout) this.bodyContent).setMargin(false);
	}

	public void showProjectTasksByStatus() {
		ProjectGenericTaskSearchCriteria searchCriteria = new ProjectGenericTaskSearchCriteria();
		searchCriteria.setsAccountId(new NumberSearchField(AppContext
				.getAccountId()));
		searchCriteria.setIsOpenned(new SearchField());
		searchCriteria.setAssignUser(new StringSearchField(SearchField.AND,
				AppContext.getUsername()));
		searchCriteria.setProjectId(new NumberSearchField(
				CurrentProjectVariables.getProjectId()));
		taskList.setSearchCriteria(searchCriteria);
	}

	public static class TaskRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<ProjectGenericTask> {

		@Override
		public Component generateRow(final ProjectGenericTask genericTask,
				int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			HorizontalLayout header = new HorizontalLayout();
			header.setSpacing(true);

			Button taskLink = generateActivationLink(genericTask.getName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(Button.ClickEvent event) {
							String type = genericTask.getType();
							int typeid = genericTask.getTypeId();
							if (ProjectContants.PROBLEM.equals(type)) {
								EventBus.getInstance()
										.fireEvent(
												new ProblemEvent.GotoRead(this,
														typeid));
							} else if (ProjectContants.RISK.equals(type)) {
								EventBus.getInstance().fireEvent(
										new RiskEvent.GotoRead(this, typeid));
							} else if (ProjectContants.TASK.equals(type)) {
								EventBus.getInstance().fireEvent(
										new TaskEvent.GotoRead(this, typeid));
							} else if (ProjectContants.BUG.equals(type)) {
								EventBus.getInstance().fireEvent(
										new BugEvent.GotoRead(this, typeid));
							}
						}
					});
			taskLink.setIcon(ProjectResources.getIconResource16size(genericTask
					.getType()));
			taskLink.addStyleName("link");
			
			if (genericTask.getDueDate() != null
					&& (genericTask.getDueDate()
							.before(new GregorianCalendar().getTime()))) {
				taskLink.addStyleName(UIConstants.LINK_OVERDUE);
			}
			
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

	private static Button generateActivationLink(String linkname,
			Button.ClickListener listener) {
		return CommonUIFactory.createButtonTooltip(
				menuLinkGenerator.handleText(linkname), linkname, listener);
	}

	private static class TaskStatusLinkLabelStringGenerator implements
			LabelStringGenerator {

		@Override
		public String handleText(String value) {
			int limitValue = 45;
			if (ScreenSize.hasSupport1024Pixels()) {
				limitValue = 35;
			}
			if (value.length() > limitValue) {
				return value.substring(0, limitValue) + "...";
			}
			return value;
		}

	}
}
