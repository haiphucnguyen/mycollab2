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
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
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
import com.vaadin.ui.VerticalLayout;
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

		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleTask task) {
		this.task = task;
		previewForm.setItemDataSource(new BeanItem<Task>(task));
	}

	@Override
	public HasPreviewFormHandlers<Task> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends TaskFormComponent {
		private static final long serialVersionUID = 1L;

		@Override
		TaskFormLayoutFactory getFormLayoutFactory() {
			return new FormLayoutFactory();
		}

		@Override
		protected void taskDoPrint() {
			// Create a window that contains what you want to print
			Window window = new Window("Window to Print");

			TaskReadViewImpl printView = new TaskReadViewImpl.PrintView();
			printView.previewItem(task);
			window.addComponent(printView);

			// Add the printing window as a new application-level window
			getApplication().addWindow(window);

			// Open it as a popup window with no decorations
			getWindow().open(new ExternalResource(window.getURL()), "_blank",
					1100, 200, // Width and height
					Window.BORDER_NONE); // No decorations

			// Print automatically when the window opens.
			// This call will block until the print dialog exits!
			window.executeJavaScript("print();");

			// Close the window automatically after printing
			window.executeJavaScript("self.close();");
		}

		@Override
		protected void taskShowHistory() {
			TaskHistoryLogWindow historyLog = new TaskHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK, task.getId());
			getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(task.getTaskname());

				if (task.getPercentagecomplete() != null
						&& 100d == task.getPercentagecomplete()) {
					addTitleStyle(UIConstants.LINK_COMPLETED);
				} else {
					if ("Pending".equals(task.getStatus())) {
						addTitleStyle(UIConstants.LINK_PENDING);
					} else if ((task.getEnddate() != null && (task.getEnddate()
							.before(new GregorianCalendar().getTime())))
							|| (task.getActualenddate() != null && (task
									.getActualenddate()
									.before(new GregorianCalendar().getTime())))
							|| (task.getDeadline() != null && (task
									.getDeadline()
									.before(new GregorianCalendar().getTime())))) {
						addTitleStyle(UIConstants.LINK_OVERDUE);
					}
				}
			}

			@Override
			protected ComponentContainer createTopPanel() {
				return (new PreviewFormControlsGenerator<Task>(PreviewForm.this))
						.createButtonControls(
								ProjectRolePermissionCollections.TASKS,
								ModuleNameConstants.PRJ);
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				TabSheet tabTaskDetail = new TabSheet();
				tabTaskDetail.setWidth("100%");
				tabTaskDetail.setStyleName(UIConstants.WHITE_TABSHEET);

				CommentDisplay commentList = new CommentDisplay(
						CommentTypeConstants.PRJ_TASK, task.getId(), true,
						true, ProjectTaskNotificationService.class);
				tabTaskDetail.addTab(commentList, "Comments");

				TaskHistoryList historyList = new TaskHistoryList(task.getId());
				historyList.setMargin(true);
				tabTaskDetail.addTab(historyList, "History");

				TaskFollowerSheet followerSheet = new TaskFollowerSheet();
				tabTaskDetail.addTab(followerSheet, "Follower");

				TaskTimeSheet timesheet = new TaskTimeSheet();
				tabTaskDetail.addTab(timesheet, "Time");

				return tabTaskDetail;
			}
		}

		class TaskFollowerSheet extends VerticalLayout {

		}

		class TaskTimeSheet extends VerticalLayout {

		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends TaskReadViewImpl {

		public PrintView() {

			previewForm = new AdvancedPreviewBeanForm<Task>() {
				@Override
				public void setItemDataSource(Item newDataSource) {
					BeanItem<SimpleTask> beanItem = (BeanItem<SimpleTask>) newDataSource;
					task = beanItem.getBean();

					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(Item item,
								Object propertyId, Component uiContext) {

							if (propertyId.equals("assignuser")) {
								return new ProjectUserFormLinkField(task
										.getAssignuser(), task
										.getAssignUserFullName());
							} else if (propertyId.equals("taskListName")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										task.getTaskListName());
							} else if (propertyId.equals("startdate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(task
												.getStartdate()));
							} else if (propertyId.equals("enddate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(task.getEnddate()));
							} else if (propertyId.equals("actualstartdate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(task
												.getActualstartdate()));
							} else if (propertyId.equals("actualenddate")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(task
												.getActualenddate()));
							} else if (propertyId.equals("deadline")) {
								return new DefaultFormViewFieldFactory.FormViewField(
										AppContext.formatDate(task
												.getDeadline()));
							} else if (propertyId.equals("tasklistid")) {
								return new DefaultFormViewFieldFactory.FormLinkViewField(
										task.getTaskListName(),
										new Button.ClickListener() {
											@Override
											public void buttonClick(
													Button.ClickEvent event) {
												EventBus.getInstance()
														.fireEvent(
																new TaskListEvent.GotoRead(
																		this,
																		task.getTasklistid()));
											}
										});
							} else if (propertyId.equals("id")) {
								return new FormAttachmentDisplayField(
										AttachmentConstants.PROJECT_TASK_TYPE,
										task.getId());
							} else if (propertyId.equals("priority")) {
								if (StringUtil.isNotNullOrEmpty(task
										.getPriority())) {
									ThemeResource iconPriority = TaskPriorityComboBox
											.getIconResourceByPriority(task
													.getPriority());
									Embedded iconEmbedded = new Embedded(null,
											iconPriority);
									Label lbPriority = new Label(task
											.getPriority());

									FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
									containerField
											.addComponentField(iconEmbedded);
									lbPriority.setWidth("220px");
									containerField
											.addComponentField(lbPriority);
									return containerField;
								}
							} else if (propertyId.equals("notes")) {
								return new FormViewField(task.getNotes(),
										Label.CONTENT_XHTML);
							}
							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(previewForm);

		}

		class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(task.getTaskname());
			}

			@Override
			protected ComponentContainer createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected ComponentContainer createBottomPanel() {
				return new CommentListDepot(CommentTypeConstants.PRJ_TASK,
						task.getId(), false, false);
			}
		}
	}

	@Override
	public SimpleTask getItem() {
		return task;
	}
}
