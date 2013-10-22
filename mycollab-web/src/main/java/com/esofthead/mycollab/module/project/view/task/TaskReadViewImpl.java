/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskReadViewImpl extends AbstractView implements TaskReadView {

	private static final long serialVersionUID = 1L;

	protected AdvancedPreviewBeanForm<Task> previewForm;

	protected SimpleTask task;

	public TaskReadViewImpl() {
		super();
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
		this.setMargin(true);
	}

	@Override
	public SimpleTask getItem() {
		return this.task;
	}

	@Override
	public HasPreviewFormHandlers<Task> getPreviewFormHandlers() {
		return this.previewForm;
	}

	@Override
	public void previewItem(final SimpleTask task) {
		this.task = task;
		this.previewForm.setItemDataSource(new BeanItem<Task>(task));
	}

	private class PreviewForm extends TaskFormComponent {
		private static final long serialVersionUID = 1L;

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(TaskReadViewImpl.this.task.getTaskname());

				if (TaskReadViewImpl.this.task.getPercentagecomplete() != null
						&& 100d == TaskReadViewImpl.this.task
								.getPercentagecomplete()) {
					this.addTitleStyle(UIConstants.LINK_COMPLETED);
				} else {
					if ("Pending"
							.equals(TaskReadViewImpl.this.task.getStatus())) {
						this.addTitleStyle(UIConstants.LINK_PENDING);
					} else if ((TaskReadViewImpl.this.task.getEnddate() != null && (TaskReadViewImpl.this.task
							.getEnddate().before(new GregorianCalendar()
							.getTime())))
							|| (TaskReadViewImpl.this.task.getActualenddate() != null && (TaskReadViewImpl.this.task
									.getActualenddate()
									.before(new GregorianCalendar().getTime())))
							|| (TaskReadViewImpl.this.task.getDeadline() != null && (TaskReadViewImpl.this.task
									.getDeadline()
									.before(new GregorianCalendar().getTime())))) {
						this.addTitleStyle(UIConstants.LINK_OVERDUE);
					}
				}
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				final TabSheet tabTaskDetail = new TabSheet();
				tabTaskDetail.setWidth("100%");

				final CommentDisplay commentList = new CommentDisplay(
						CommentType.PRJ_TASK,
						TaskReadViewImpl.this.task.getId(),
						CurrentProjectVariables.getProjectId(), true, true,
						ProjectTaskRelayEmailNotificationAction.class);
				commentList.setMargin(true);
				tabTaskDetail.addTab(commentList, "Comments");

				final TaskHistoryList historyList = new TaskHistoryList(
						TaskReadViewImpl.this.task.getId());
				historyList.setMargin(true);
				tabTaskDetail.addTab(historyList, "History");

				final TaskFollowersSheet followerSheet = new TaskFollowersSheet(
						TaskReadViewImpl.this.task);
				tabTaskDetail.addTab(followerSheet, "Follower");

				final TaskTimeLogSheet timesheet = new TaskTimeLogSheet(
						TaskReadViewImpl.this.task);
				tabTaskDetail.addTab(timesheet, "Time");

				return tabTaskDetail;
			}

			@Override
			protected ComponentContainer createTopPanel() {
				ProjectPreviewFormControlsGenerator<Task> taskPreviewForm = new ProjectPreviewFormControlsGenerator<Task>(
						PreviewForm.this);
				final HorizontalLayout topPanel = taskPreviewForm
						.createButtonControls(
								ProjectRolePermissionCollections.TASKS, true);
				topPanel.setMargin(true);

				final Button quickActionStatusBtn = new Button("",
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if (task.getPercentagecomplete() == 100d) {
									task.setPercentagecomplete(0d);
									ProjectTaskService service = ApplicationContextUtil
											.getSpringBean(ProjectTaskService.class);
									service.updateWithSession(task,
											AppContext.getUsername());
									FormLayoutFactory.this
											.removeTitleStyleName(UIConstants.LINK_COMPLETED);
									FormLayoutFactory.this.setPercent("0.0");
								} else {
									task.setPercentagecomplete(100d);
									ProjectTaskService service = ApplicationContextUtil
											.getSpringBean(ProjectTaskService.class);
									service.updateWithSession(task,
											AppContext.getUsername());
									FormLayoutFactory.this
											.addTitleStyleName("headerName");
									FormLayoutFactory.this
											.addTitleStyleName(UIConstants.LINK_COMPLETED);
									FormLayoutFactory.this.setPercent("100.0");
								}
							}
						});
				quickActionStatusBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				if (task.getPercentagecomplete() == 100d) {
					quickActionStatusBtn.setCaption("ReOpen");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/reopenTask.png"));
					taskPreviewForm.addQuickActionButton(quickActionStatusBtn);
				} else {
					quickActionStatusBtn.setCaption("Close");
					quickActionStatusBtn.setIcon(MyCollabResource
							.newResource("icons/16/project/closeTask.png"));
					taskPreviewForm.addQuickActionButton(quickActionStatusBtn);
				}
				return topPanel;
			}
		}

		@Override
		TaskFormLayoutFactory getFormLayoutFactory() {
			return new FormLayoutFactory();
		}

		@Override
		protected void taskDoPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final TaskReadViewImpl printView = new TaskReadViewImpl.PrintView();
			printView.previewItem(TaskReadViewImpl.this.task);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			this.getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			this.getWindow().open(new ExternalResource(window.getURL()),
					"_blank", 1100, 200, // Width and height
					Window.BORDER_NONE); // No decorations

			// Print automatically when the window opens.
			// This call will block until the print dialog exits!
			window.executeJavaScript("print();");

			// Close the window automatically after printing
			window.executeJavaScript("self.close();");
		}

		@Override
		protected void taskShowHistory() {
			final TaskHistoryLogWindow historyLog = new TaskHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK,
					TaskReadViewImpl.this.task.getId());
			this.getWindow().addWindow(historyLog);
		}
	}

	public static class PrintView extends TaskReadViewImpl {
		private static final long serialVersionUID = 1L;

		public PrintView() {
			super();
		}

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.task.getTaskname());
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				return new CommentListDepot(CommentType.PRJ_TASK,
						PrintView.this.task.getId(),
						CurrentProjectVariables.getProjectId(), false, false);
			}

			@Override
			protected ComponentContainer createTopPanel() {
				return new HorizontalLayout();
			}
		}
	}
}
