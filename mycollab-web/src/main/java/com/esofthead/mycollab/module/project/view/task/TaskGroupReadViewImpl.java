/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskGroupReadViewImpl extends AbstractView implements
		TaskGroupReadView {

	private static final long serialVersionUID = 1L;
	protected AdvancedPreviewBeanForm<SimpleTaskList> previewForm;
	protected SimpleTaskList taskList;

	public TaskGroupReadViewImpl() {
		super();
		this.previewForm = new PreviewForm();
		this.addComponent(this.previewForm);
		this.setMargin(true);
	}

	@Override
	public void previewItem(final SimpleTaskList taskList) {
		this.taskList = taskList;
		this.previewForm.setItemDataSource(new BeanItem<TaskList>(taskList));
	}

	@Override
	public HasPreviewFormHandlers<SimpleTaskList> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleTaskList> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(final Item item,
						final Object propertyId, final Component uiContext) {

					if (propertyId.equals("milestoneid")) {
						return new FormLinkViewField(
								TaskGroupReadViewImpl.this.taskList
										.getMilestoneName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(
											final ClickEvent event) {
										EventBus.getInstance()
												.fireEvent(
														new MilestoneEvent.GotoRead(
																this,
																TaskGroupReadViewImpl.this.taskList
																		.getMilestoneid()));
									}
								});
					} else if (propertyId.equals("owner")) {
						return new ProjectUserFormLinkField(
								TaskGroupReadViewImpl.this.taskList.getOwner(),
								TaskGroupReadViewImpl.this.taskList
										.getOwnerAvatarId(),
								TaskGroupReadViewImpl.this.taskList
										.getOwnerFullName());
					} else if (propertyId.equals("percentageComplete")) {
						final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
						final ProgressPercentageIndicator progressField = new ProgressPercentageIndicator(
								TaskGroupReadViewImpl.this.taskList
										.getPercentageComplete());
						fieldContainer.addComponentField(progressField);
						return fieldContainer;
					} else if (propertyId.equals("description")) {
						return new FormViewField(
								TaskGroupReadViewImpl.this.taskList
										.getDescription(), Label.CONTENT_XHTML);
					} else if (propertyId.equals("numOpenTasks")) {
						final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
						final Label numTaskLbl = new Label("("
								+ TaskGroupReadViewImpl.this.taskList
										.getNumOpenTasks()
								+ "/"
								+ TaskGroupReadViewImpl.this.taskList
										.getNumAllTasks() + ")");
						fieldContainer.addComponentField(numTaskLbl);
						return fieldContainer;
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);

		}

		@Override
		protected void doPrint() {
			// Create a window that contains what you want to print
			final Window window = new Window("Window to Print");

			final TaskGroupReadViewImpl printView = new TaskGroupReadViewImpl.PrintView();
			printView.previewItem(TaskGroupReadViewImpl.this.taskList);
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
		protected void showHistory() {
			final TaskListHistoryLogWindow historyLog = new TaskListHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK_LIST,
					TaskGroupReadViewImpl.this.taskList.getId());
			this.getWindow().addWindow(historyLog);
		}

		class FormLayoutFactory extends TaskGroupFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(TaskGroupReadViewImpl.this.taskList.getName());
				if ("Closed".equals(TaskGroupReadViewImpl.this.taskList
						.getStatus())) {
					this.addTitleStyle(UIConstants.LINK_COMPLETED);
				}
			}

			@Override
			protected Layout createTopPanel() {
				return (new ProjectPreviewFormControlsGenerator<SimpleTaskList>(
						PreviewForm.this)).createButtonControls(
						ProjectRolePermissionCollections.TASKS, true);
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				final CommentListDepot commentList = new CommentListDepot(
						CommentTypeConstants.PRJ_TASK_LIST,
						TaskGroupReadViewImpl.this.taskList.getId(), true, true);
				commentList.setWidth("100%");
				relatedItemsPanel.addComponent(commentList);
				relatedItemsPanel.addComponent(new TaskDepot());
				return relatedItemsPanel;
			}
		}
	}

	@SuppressWarnings("serial")
	public static class PrintView extends TaskGroupReadViewImpl {

		public PrintView() {
			this.previewForm = new AdvancedPreviewBeanForm<SimpleTaskList>() {
				@Override
				public void setItemDataSource(final Item newDataSource) {
					this.setFormLayoutFactory(new FormLayoutFactory());
					this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
						private static final long serialVersionUID = 1L;

						@Override
						protected Field onCreateField(final Item item,
								final Object propertyId,
								final Component uiContext) {

							if (propertyId.equals("milestoneid")) {
								return new FormLinkViewField(
										PrintView.this.taskList
												.getMilestoneName(),
										new Button.ClickListener() {
											private static final long serialVersionUID = 1L;

											@Override
											public void buttonClick(
													final ClickEvent event) {
												EventBus.getInstance()
														.fireEvent(
																new MilestoneEvent.GotoRead(
																		this,
																		PrintView.this.taskList
																				.getMilestoneid()));
											}
										});
							} else if (propertyId.equals("owner")) {
								return new ProjectUserFormLinkField(
										PrintView.this.taskList.getOwner(),
										PrintView.this.taskList
												.getOwnerAvatarId(),
										PrintView.this.taskList
												.getOwnerFullName());
							} else if (propertyId.equals("percentageComplete")) {
								final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
								final ProgressPercentageIndicator progressField = new ProgressPercentageIndicator(
										PrintView.this.taskList
												.getPercentageComplete());
								fieldContainer.addComponentField(progressField);
								return fieldContainer;
							} else if (propertyId.equals("description")) {
								return new FormViewField(
										PrintView.this.taskList
												.getDescription(),
										Label.CONTENT_XHTML);
							} else if (propertyId.equals("numOpenTasks")) {
								final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
								final Label numTaskLbl = new Label("("
										+ PrintView.this.taskList
												.getNumOpenTasks()
										+ "/"
										+ PrintView.this.taskList
												.getNumAllTasks() + ")");
								fieldContainer.addComponentField(numTaskLbl);
								return fieldContainer;
							}

							return null;
						}
					});
					super.setItemDataSource(newDataSource);
				}
			};

			this.addComponent(this.previewForm);
		}

		class FormLayoutFactory extends TaskGroupFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(PrintView.this.taskList.getName());
			}

			@Override
			protected Layout createTopPanel() {
				return new HorizontalLayout();
			}

			@Override
			protected Layout createBottomPanel() {
				final VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.addComponent(new CommentListDepot(
						CommentTypeConstants.PRJ_TASK_LIST,
						PrintView.this.taskList.getId(), false, false));
				relatedItemsPanel.addComponent(new TaskDepot());
				return relatedItemsPanel;
			}
		}
	}

	@SuppressWarnings("serial")
	public class TaskDepot extends Depot {

		private final TaskDisplayComponent taskDisplayComponent;

		public TaskDepot() {
			super("Tasks", new HorizontalLayout(), new TaskDisplayComponent(
					TaskGroupReadViewImpl.this.taskList, false));
			this.addStyleName("task-list");
			this.initHeader();
			this.taskDisplayComponent = (TaskDisplayComponent) this.bodyContent;
		}

		private void initHeader() {
			final HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			final PopupButton taskListFilterControl;
			taskListFilterControl = new PopupButton("Active Tasks");
			taskListFilterControl.setWidth("120px");
			taskListFilterControl.addStyleName("link");

			final VerticalLayout filterBtnLayout = new VerticalLayout();
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("200px");

			final Button allTasksFilterBtn = new Button("All Tasks",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("All Tasks");
							TaskDepot.this.displayAllTasks();
						}
					});
			allTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(allTasksFilterBtn);

			final Button activeTasksFilterBtn = new Button("Active Tasks Only",
					new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("Active Tasks");
							TaskDepot.this.displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			final Button archievedTasksFilterBtn = new Button(
					"Archieved Tasks Only", new Button.ClickListener() {
						@Override
						public void buttonClick(final ClickEvent event) {
							taskListFilterControl.setCaption("Archieved Tasks");
							taskListFilterControl.setPopupVisible(false);
							TaskDepot.this.displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			taskListFilterControl.addComponent(filterBtnLayout);
			headerLayout.addComponent(taskListFilterControl);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			final TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setTaskListId(new NumberSearchField(
					TaskGroupReadViewImpl.this.taskList.getId()));
			return criteria;
		}

		private void displayActiveTasksOnly() {
			final TaskSearchCriteria criteria = this.createBaseSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Open", "Pending" }));
			this.taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayAllTasks() {
			final TaskSearchCriteria criteria = this.createBaseSearchCriteria();
			this.taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayInActiveTasks() {
			final TaskSearchCriteria criteria = this.createBaseSearchCriteria();
			criteria.setStatuses(new SetSearchField<String>(SearchField.AND,
					new String[] { "Closed" }));
			this.taskDisplayComponent.setSearchCriteria(criteria);
		}
	}

	@Override
	public SimpleTaskList getItem() {
		return this.taskList;
	}
}
