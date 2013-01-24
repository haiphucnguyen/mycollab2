package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.dialogs.ConfirmDialog;
import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.core.arguments.BooleanSearchField;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.module.project.ProjectContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.criteria.TaskListSearchCriteria;
import com.esofthead.mycollab.module.project.domain.criteria.TaskSearchCriteria;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.BeanList;
import com.esofthead.mycollab.vaadin.ui.Depot;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public class TaskListDisplayViewImpl extends AbstractView implements
		TaskListDisplayView {

	private BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList> taskLists;
	private Button reOrderBtn;

	public TaskListDisplayViewImpl() {
		super();

		constructHeader();
	}

	private void constructHeader() {
		HorizontalLayout header = new HorizontalLayout();
		header.setMargin(true);
		header.setSpacing(true);
		header.setWidth("100%");
		Label headerLbl = new Label("All Tasks");
		header.addComponent(headerLbl);
		header.setExpandRatio(headerLbl, 1.0f);

		reOrderBtn = new Button("Reorder", new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				EventBus.getInstance().fireEvent(
						new TaskListEvent.ReoderTaskList(this, null));
			}
		});
		reOrderBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(reOrderBtn);
		header.setComponentAlignment(reOrderBtn, Alignment.MIDDLE_RIGHT);

		Button newTaskListBtn = new Button("New Task List",
				new Button.ClickListener() {
					@Override
					public void buttonClick(ClickEvent event) {
						TaskListWindow taskListWindow = new TaskListWindow(
								TaskListDisplayViewImpl.this);
						TaskListDisplayViewImpl.this.getWindow().addWindow(
								taskListWindow);
					}
				});
		newTaskListBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		header.addComponent(newTaskListBtn);
		header.setComponentAlignment(newTaskListBtn, Alignment.MIDDLE_RIGHT);

		this.addComponent(header);
		taskLists = new BeanList<ProjectTaskListService, TaskListSearchCriteria, SimpleTaskList>(
				null, AppContext.getSpringBean(ProjectTaskListService.class),
				TaskListRowDisplayHandler.class);
		this.addComponent(taskLists);
	}

	@Override
	public void displayTakLists() {
		TaskListSearchCriteria criteria = new TaskListSearchCriteria();
		SimpleProject project = (SimpleProject) AppContext
				.getVariable(ProjectContants.PROJECT_NAME);
		criteria.setProjectId(new NumberSearchField(project.getId()));
		taskLists.setSearchCriteria(criteria);
	}

	@Override
	public void insertTaskList(SimpleTaskList taskList) {
		taskLists.insertItemOnTop(taskList);
	}

	public static class TaskListRowDisplayHandler implements
			BeanList.RowDisplayHandler<SimpleTaskList> {

		@Override
		public Component generateRow(SimpleTaskList taskList, int rowIndex) {
			return new TaskListDepot(taskList);
		}
	}

	static class TaskListDepot extends Depot {

		private final SimpleTaskList taskList;
		private PopupButton taskListFilterControl;
		private PopupButton taskListActionControl;
		private TaskDisplayComponent taskDisplayComponent;

		public TaskListDepot(SimpleTaskList taskListParam) {
			super(taskListParam.getName(), new HorizontalLayout(),
					new TaskDisplayComponent(taskListParam));
			this.taskList = taskListParam;
			this.addStyleName("task-list");
			initHeader();
			initContent();
		}

		private void initHeader() {
			HorizontalLayout headerLayout = (HorizontalLayout) this.headerContent;
			headerLayout.setSpacing(true);

			taskListFilterControl = new PopupButton("Filter");
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
							displayActiveTasksOnly();
						}
					});
			activeTasksFilterBtn.setStyleName("link");
			filterBtnLayout.addComponent(activeTasksFilterBtn);

			Button archievedTasksFilterBtn = new Button("Archieved Tasks Only",
					new Button.ClickListener() {

						@Override
						public void buttonClick(ClickEvent event) {
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
				@Override
				public void buttonClick(ClickEvent event) {
					taskListActionControl.setPopupVisible(false);
					EventBus.getInstance().fireEvent(
							new TaskListEvent.GotoEdit(event, taskList));
				}
			});
			editBtn.setStyleName("link");
			actionBtnLayout.addComponent(editBtn);

			Button deleteBtn = new Button("Delete", new Button.ClickListener() {
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

		private void initContent() {
			taskDisplayComponent = (TaskDisplayComponent) this.bodyContent;
			displayActiveTasksOnly();
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
			criteria.setIscompleted(new BooleanSearchField(false));
			taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayAllTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			taskDisplayComponent.setSearchCriteria(criteria);
		}

		private void displayInActiveTasks() {
			TaskSearchCriteria criteria = createBaseSearchCriteria();
			criteria.setIscompleted(new BooleanSearchField(true));
			taskDisplayComponent.setSearchCriteria(criteria);
		}
	}
}
