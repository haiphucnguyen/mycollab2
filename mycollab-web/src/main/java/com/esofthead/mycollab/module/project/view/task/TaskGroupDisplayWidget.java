package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class TaskGroupDisplayWidget
		extends
		BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList> {
	private static final long serialVersionUID = 1L;

	public TaskGroupDisplayWidget() {
		super(null, AppContext.getSpringBean(ProjectTaskListService.class),
				TaskListRowDisplayHandler.class, true);
	}

	public static class TaskListRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleTaskList> {

		@Override
		public Component generateRow(SimpleTaskList taskList, int rowIndex) {
			return new TaskListDepot(taskList);
		}
	}

	static class TaskListDepot extends Depot {
		private static final long serialVersionUID = 1L;
		private final SimpleTaskList taskList;
		private PopupButton taskListFilterControl;
		private PopupButton taskListActionControl;
		private final TaskDisplayComponent taskDisplayComponent;

		public TaskListDepot(SimpleTaskList taskListParam) {
			super(taskListParam.getName(), new HorizontalLayout(),
					new TaskDisplayComponent(taskListParam, true));
			this.taskList = taskListParam;
			this.addStyleName("task-list");
			initHeader();
			taskDisplayComponent = (TaskDisplayComponent) this.bodyContent;
		}

		private void initHeader() {
			HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			taskListFilterControl = new PopupButton("Active Tasks");
			taskListFilterControl.setWidth("90px");
			taskListFilterControl.addStyleName("link");

			VerticalLayout filterBtnLayout = new VerticalLayout();
			filterBtnLayout.setMargin(true);
			filterBtnLayout.setSpacing(true);
			filterBtnLayout.setWidth("200px");

			Button allTasksFilterBtn = new Button("All Tasks",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

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
						private static final long serialVersionUID = 1L;

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
						private static final long serialVersionUID = 1L;

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

			taskListActionControl = new PopupButton("Action");
			taskListActionControl.addStyleName("link");
			headerLayout.addComponent(taskListActionControl);

			VerticalLayout actionBtnLayout = new VerticalLayout();
			actionBtnLayout.setMargin(true);
			actionBtnLayout.setSpacing(true);
			actionBtnLayout.setWidth("200px");
			taskListActionControl.addComponent(actionBtnLayout);

			Button readBtn = new Button("View", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					taskListActionControl.setPopupVisible(false);
					EventBus.getInstance()
							.fireEvent(
									new TaskListEvent.GotoRead(event, taskList
											.getId()));
				}
			});
			readBtn.setStyleName("link");
			actionBtnLayout.addComponent(readBtn);

			Button editBtn = new Button("Edit", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					taskListActionControl.setPopupVisible(false);
					EventBus.getInstance().fireEvent(
							new TaskListEvent.GotoEdit(event, taskList));
				}
			});
			editBtn.setStyleName("link");
			actionBtnLayout.addComponent(editBtn);

			Button closeBtn = new Button("Close", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					taskListActionControl.setPopupVisible(false);
					taskList.setStatus("Closed");
					ProjectTaskListService taskListService = AppContext
							.getSpringBean(ProjectTaskListService.class);
					taskListService.updateWithSession(taskList,
							AppContext.getUsername());
					LazyLoadWrapper parentComp = (LazyLoadWrapper) TaskListDepot.this
							.getParent();
					((ComponentContainer) parentComp.getParent())
							.removeComponent(parentComp);
				}
			});
			closeBtn.setStyleName("link");
			actionBtnLayout.addComponent(closeBtn);

			Button deleteBtn = new Button("Delete", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					taskListActionControl.setPopupVisible(false);
					ConfirmDialog.show(
							TaskListDepot.this.getWindow(),
							"Please Confirm:",
							"Are you sure to delete task group '"
									+ taskList.getName() + "' ?", "Yes", "No",
							new ConfirmDialog.Listener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void onClose(ConfirmDialog dialog) {
									if (dialog.isConfirmed()) {
										ProjectTaskListService taskListService = AppContext
												.getSpringBean(ProjectTaskListService.class);
										taskListService.removeWithSession(
												taskList.getId(),
												AppContext.getUsername());
										EventBus.getInstance()
												.fireEvent(
														new TaskListEvent.GotoTaskListScreen(
																this, null));
									}
								}
							});
				}
			});
			deleteBtn.setStyleName("link");
			actionBtnLayout.addComponent(deleteBtn);
		}

		private TaskSearchCriteria createBaseSearchCriteria() {
			SimpleProject project = (SimpleProject) AppContext
					.getVariable(ProjectContants.PROJECT_NAME);
			TaskSearchCriteria criteria = new TaskSearchCriteria();
			criteria.setProjectid(new NumberSearchField(project.getId()));
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

}
