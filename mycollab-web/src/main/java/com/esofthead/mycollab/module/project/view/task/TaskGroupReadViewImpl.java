/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.common.CommentType;
import com.esofthead.mycollab.common.ModuleNameConstants;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchField;
import com.esofthead.mycollab.core.arguments.SetSearchField;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.ProjectRolePermissionCollections;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.MilestoneEvent;
import com.esofthead.mycollab.module.project.ui.components.CommentListDepot;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.HasPreviewFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormDetectAndDisplayUrlViewField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormLinkViewField;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.ProjectPreviewFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class TaskGroupReadViewImpl extends AbstractPageView implements
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
		this.previewForm.setBean(taskList);
	}

	@Override
	public HasPreviewFormHandlers<SimpleTaskList> getPreviewFormHandlers() {
		return this.previewForm;
	}

	private class PreviewForm extends AdvancedPreviewBeanForm<SimpleTaskList> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setBean(final SimpleTaskList newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setBeanFormFieldFactory(new AbstractBeanFieldGroupViewFieldFactory<SimpleTaskList>(
					PreviewForm.this) {
				private static final long serialVersionUID = 1L;

				@Override
				protected Field<?> onCreateField(final Object propertyId) {

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
						return new FormDetectAndDisplayUrlViewField(
								TaskGroupReadViewImpl.this.taskList
										.getDescription());
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
			super.setBean(newDataSource);

		}

		@Override
		public void showHistory() {
			final TaskListHistoryLogWindow historyLog = new TaskListHistoryLogWindow(
					ModuleNameConstants.PRJ, ProjectContants.TASK_LIST,
					TaskGroupReadViewImpl.this.taskList.getId());
			UI.getCurrent().addWindow(historyLog);
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
						CommentType.PRJ_TASK_LIST,
						TaskGroupReadViewImpl.this.taskList.getId(),
						CurrentProjectVariables.getProjectId(), true, true);
				commentList.setWidth("100%");
				relatedItemsPanel.addComponent(commentList);
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
			taskListFilterControl.setContent(filterBtnLayout);
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
