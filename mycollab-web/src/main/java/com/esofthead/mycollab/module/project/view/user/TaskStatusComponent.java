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
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectResources;
import com.esofthead.mycollab.module.project.domain.ProjectGenericTask;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectGenericTaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.ProjectEvent;
import com.esofthead.mycollab.module.project.service.ProjectGenericTaskService;
import com.esofthead.mycollab.module.project.view.parameters.BugScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProblemScreenData;
import com.esofthead.mycollab.module.project.view.parameters.ProjectScreenData;
import com.esofthead.mycollab.module.project.view.parameters.RiskScreenData;
import com.esofthead.mycollab.module.project.view.parameters.TaskScreenData;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.PageActionChain;
import com.esofthead.mycollab.vaadin.ui.CommonUIFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultBeanPagedList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.utils.LabelStringGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskStatusComponent extends Depot {
	private static final long serialVersionUID = 1L;
	private DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask> taskList;
	private static LabelStringGenerator menuLinkGenerator = new TaskStatusLinkLabelStringGenerator();

	public TaskStatusComponent() {
		super("Openned Tasks", new VerticalLayout());

		taskList = new DefaultBeanPagedList<ProjectGenericTaskService, ProjectGenericTaskSearchCriteria, ProjectGenericTask>(
				AppContext.getSpringBean(ProjectGenericTaskService.class),
				ActivityStreamRowDisplayHandler.class, 10);
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
		taskList.setSearchCriteria(searchCriteria);
	}

	public static class ActivityStreamRowDisplayHandler implements
			DefaultBeanPagedList.RowDisplayHandler<ProjectGenericTask> {

		@Override
		public Component generateRow(final ProjectGenericTask genericTask,
				int rowIndex) {
			CssLayout layout = new CssLayout();
			layout.setWidth("100%");
			layout.setStyleName("activity-stream");

			CssLayout header = new CssLayout();
			header.setStyleName("stream-content");

			Button taskLink = generateProjectLink(genericTask.getName(),
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							String type = genericTask.getType();
							int typeid = genericTask.getTypeId();
							int projectid = genericTask.getProjectId();

							if (ProjectContants.PROBLEM.equals(type)) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectid),
										new ProblemScreenData.Read(typeid));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if (ProjectContants.RISK.equals(type)) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectid),
										new RiskScreenData.Read(typeid));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if (ProjectContants.TASK.equals(type)) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectid),
										new TaskScreenData.Read(typeid));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							} else if (ProjectContants.BUG.equals(type)) {
								PageActionChain chain = new PageActionChain(
										new ProjectScreenData.Goto(projectid),
										new BugScreenData.Read(typeid));
								EventBus.getInstance().fireEvent(
										new ProjectEvent.GotoMyProject(this,
												chain));
							}
						}
					});
			taskLink.setIcon(ProjectResources.getIconResource16size(genericTask
					.getType()));
			if (genericTask.getDueDate() != null
					&& (genericTask.getDueDate().before(new GregorianCalendar()
							.getTime()))) {
				taskLink.addStyleName(UIConstants.LINK_OVERDUE);
			}
			taskLink.addStyleName("link");
			header.addComponent(taskLink);

			Label projectLbl = new Label(" in project ");
			projectLbl.setWidth(Sizeable.SIZE_UNDEFINED, 0);
			header.addComponent(projectLbl);

			Button projectLink = generateProjectLink(
					genericTask.getProjectName(), new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							EventBus.getInstance()
									.fireEvent(
											new ProjectEvent.GotoMyProject(
													this,
													new PageActionChain(
															new ProjectScreenData.Goto(
																	genericTask
																			.getProjectId()))));
						}
					});
			projectLink.setIcon(ProjectResources
					.getIconResource16size(ProjectContants.PROJECT));
			projectLink.setStyleName("link");
			header.addComponent(projectLink);

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

	private static Button generateProjectLink(String linkname,
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
