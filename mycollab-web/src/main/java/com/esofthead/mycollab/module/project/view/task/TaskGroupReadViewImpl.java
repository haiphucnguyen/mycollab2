/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.CommentTypeConstants;
import com.esofthead.mycollab.common.ui.components.CommentListDepot;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
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
import com.esofthead.mycollab.vaadin.ui.PreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskGroupReadViewImpl extends AbstractView implements
		TaskGroupReadView {

	private static final long serialVersionUID = 1L;
	private PreviewForm previewForm;
	private SimpleTaskList taskList;

	public TaskGroupReadViewImpl() {
		super();
		previewForm = new PreviewForm();
		this.addComponent(previewForm);
	}

	@Override
	public void previewItem(SimpleTaskList taskList) {
		this.taskList = taskList;
		previewForm.setItemDataSource(new BeanItem<TaskList>(taskList));
	}

	@Override
	public HasPreviewFormHandlers<SimpleTaskList> getPreviewFormHandlers() {
		return previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleTaskList> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field onCreateField(Item item, Object propertyId,
						Component uiContext) {
					if (propertyId.equals("milestoneid")) {
						return new FormLinkViewField(taskList
								.getMilestoneName(),
								new Button.ClickListener() {
									private static final long serialVersionUID = 1L;

									@Override
									public void buttonClick(ClickEvent event) {
										EventBus.getInstance()
												.fireEvent(
														new MilestoneEvent.GotoRead(
																this,
																taskList.getMilestoneid()));
									}
								});
					} else if (propertyId.equals("owner")) {
						return new ProjectUserFormLinkField(
								taskList.getOwner(), taskList
										.getOwnerFullName());
					} else if (propertyId.equals("percentageComplete")) {
						FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
						ProgressIndicator progressField = new ProgressIndicator(
								new Float(
										taskList.getPercentageComplete() / 100));
						progressField.setPollingInterval(1000 * 60 * 60 * 24);
						progressField.setWidth("100px");
						fieldContainer.addComponentField(progressField);
						return fieldContainer;
					} else if (propertyId.equals("numOpenTasks")) {
						FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
						ProgressIndicator progressField = new ProgressIndicator(
								new Float(1
										- (float) taskList.getNumOpenTasks()
										/ taskList.getNumAllTasks()));
						progressField.setPollingInterval(1000 * 60 * 60 * 24);
						progressField.setWidth("100px");
						fieldContainer.addComponentField(progressField);

						Label numTaskLbl = new Label("("
								+ taskList.getNumOpenTasks() + "/"
								+ taskList.getNumAllTasks() + ")");
						fieldContainer.addComponentField(numTaskLbl);
						return fieldContainer;
					}

					return null;
				}
			});
			super.setItemDataSource(newDataSource);
		}

		class FormLayoutFactory extends TaskGroupFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(taskList.getName());
			}

			@Override
			protected Layout createTopPanel() {
				return (new PreviewFormControlsGenerator<SimpleTaskList>(
						PreviewForm.this)).createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				VerticalLayout relatedItemsPanel = new VerticalLayout();
				relatedItemsPanel.addComponent(new CommentListDepot(
						CommentTypeConstants.PRJ_TASK_LIST, taskList.getId()));
				relatedItemsPanel.addComponent(new TaskDepot());
				return relatedItemsPanel;
			}
		}
	}

	@SuppressWarnings("serial")
	private class TaskDepot extends Depot {

		private TaskDisplayComponent taskDisplayComponent;

		public TaskDepot() {
			super("Tasks", new HorizontalLayout(), new TaskDisplayComponent(
					taskList, false));
			this.addStyleName("task-list");
			initHeader();
			taskDisplayComponent = (TaskDisplayComponent) this.bodyContent;
		}

		private void initHeader() {
			HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			final PopupButton taskListFilterControl;
			taskListFilterControl = new PopupButton("Active Tasks");
			taskListFilterControl.setWidth("120px");
			taskListFilterControl.addStyleName("link");

			VerticalLayout filterBtnLayout = new VerticalLayout();
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("200px");

			Button allTasksFilterBtn = new Button("All Tasks",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("All Tasks");
							displayAllTasks();
						}
					});
			allTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(allTasksFilterBtn);

			Button activeTasksFilterBtn = new Button("Active Tasks Only",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setPopupVisible(false);
							taskListFilterControl.setCaption("Active Tasks");
							displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			Button archievedTasksFilterBtn = new Button("Archieved Tasks Only",
					new Button.ClickListener() {
						@Override
						public void buttonClick(ClickEvent event) {
							taskListFilterControl.setCaption("Archieved Tasks");
							taskListFilterControl.setPopupVisible(false);
							displayInActiveTasks();
						}
					});
			archievedTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(archievedTasksFilterBtn);
			taskListFilterControl.addComponent(filterBtnLayout);
			headerLayout.addComponent(taskListFilterControl);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(CurrentProjectVariables
					.getProjectId()));
			criteria.setTaskListId(new NumberSearchField(taskList.getId()));
			return criteria;
		}

		private void displayActiveTasksOnly() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setStatus(new StringSearchField("Open"));
			taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayAllTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayInActiveTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setStatus(new StringSearchField("Closed"));
			taskDisplayComponent.setSearchCriteria(criteria);
		}
	}

	@Override
	public SimpleTaskList getItem() {
		return taskList;
	}
}
