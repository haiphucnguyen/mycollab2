/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.common.ui.components.CommentListDepot.CommentDisplay;
import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.file.AttachmentConstants;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskNotificationService;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskReadViewImpl extends AbstractView implements TaskReadView {

	private class PreviewForm extends TaskFormComponent {
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
				// tabTaskDetail.setStyleName(UIConstants.WHITE_TABSHEET);

				final CommentDisplay commentList = new CommentDisplay(
						CommentTypeConstants.PRJ_TASK,
						TaskReadViewImpl.this.task.getId(), true, true,
						ProjectTaskNotificationService.class);
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
				final HorizontalLayout topPanel = (new ProjectPreviewFormControlsGenerator<Task>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.TASKS, true);
				topPanel.setMargin(true);
				return topPanel;
			}
		}

		private static final long serialVersionUID = 1L;

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

	@SuppressWarnings("serial")
	public static class PrintView extends TaskReadViewImpl {

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.task.getTaskname());
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				return new CommentListDepot(CommentTypeConstants.PRJ_TASK,
						PrintView.this.task.getId(), false, false);
			}

			@Override
			protected ComponentContainer createTopPanel() {
				return new HorizontalLayout();
			}
		}

		public PrintView() {

			this.previewForm = new AdvancedPreviewBeanForm<Task>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					final BeanItem<SimpleTask> beanItem = (BeanItem<SimpleTask>) newDataSource;
					PrintView.this.task = beanItem.getBean();

					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {

							if (propertyId.equals("assignuser")) {
								return new ProjectUserFormLinkField(
										PrintView.this.task.getAssignuser(),
										PrintView.this.task
												.getAssignUserFullName());
							} else if (propertyId.equals("taskListName")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										PrintView.this.task.getTaskListName());
							} else if (propertyId.equals("startdate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext
												.formatDate(PrintView.this.task
														.getStartdate()));
							} else if (propertyId.equals("enddate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext
												.formatDate(PrintView.this.task
														.getEnddate()));
							} else if (propertyId.equals("actualstartdate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext
												.formatDate(PrintView.this.task
														.getActualstartdate()));
							} else if (propertyId.equals("actualenddate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext
												.formatDate(PrintView.this.task
														.getActualenddate()));
							} else if (propertyId.equals("deadline")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext
												.formatDate(PrintView.this.task
														.getDeadline()));
							} else if (propertyId.equals("tasklistid")) {
								return new DefaultFormViewFieldFactory.FormLinkViewField(
										PrintView.this.task.getTaskListName(),
										new Button.ClickListener() {
											@Override
											public void buttonClick(
													final Button.ClickEvent event) {
												EventBus.getInstance()
														.fireEvent(
																new TaskListEvent.GotoRead(
																		this,
																		PrintView.this.task
																				.getTasklistid()));
											}
										});
							} else if (propertyId.equals("id")) {
								return new FormAttachmentDisplayField(
										AttachmentConstants.PROJECT_TASK_TYPE,
										PrintView.this.task.getId());
							} else if (propertyId.equals("priority")) {
								if (StringUtil
										.isNotNullOrEmpty(PrintView.this.task
												.getPriority())) {
									final ThemeResource iconPriority = TaskPriorityComboBox
											.getIconResourceByPriority(PrintView.this.task
													.getPriority());
									final Embedded iconEmbedded = new Embedded(
											null, iconPriority);
									final Label lbPriority = new Label(
											PrintView.this.task.getPriority());

									final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
									containerField
											.addComponentField(iconEmbedded);
									lbPriority.setWidth("220px");
									containerField
											.addComponentField(lbPriority);
									return containerField;
								}
							} else if (propertyId.equals("notes")) {
								return new FormViewField(PrintView.this.task
										.getNotes(), Label.CONTENT_XHTML);
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);

		}
	}

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
}
