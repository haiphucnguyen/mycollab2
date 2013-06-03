/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.LocalizationHelper;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskGroupAddWindow extends Window {
	private static final long serialVersionUID = 1L;
	private TaskGroupDisplayView taskView;
	private SimpleTaskList taskList;
	private TaskGroupAddWindow.TaskListForm taskListForm;

	public TaskGroupAddWindow(TaskGroupDisplayView taskView,
			SimpleTaskList taskList) {
		this.taskView = taskView;
		this.taskList = taskList;
		initUI();
	}

	public TaskGroupAddWindow(TaskGroupDisplayView taskView) {
		taskList = new SimpleTaskList();
		this.taskView = taskView;
		initUI();
	}

	private void initUI() {
		this.setWidth("800px");
		taskListForm = new TaskGroupAddWindow.TaskListForm();
		taskListForm.setItemDataSource(new BeanItem(taskList));
		this.addComponent(taskListForm);

		center();
	}

	private void notifyToReloadTaskList() {
		taskView.insertTaskList(taskList);
	}

	private class TaskListForm extends AdvancedEditBeanForm<TaskList> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource) {
			this.setFormLayoutFactory(new TaskGroupAddWindow.TaskListForm.TaskListFormLayoutFactory());
			this.setFormFieldFactory(new TaskGroupAddWindow.TaskListForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class TaskListFormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				AddViewLayout taskListAddLayout = new AddViewLayout(
						LocalizationHelper
								.getMessage(TaskI18nEnum.NEW_TASKGROUP_TITLE),
						new ThemeResource("icons/48/project/tasklist.png"));

				informationLayout = new GridFormLayoutHelper(2, 3);

				VerticalLayout bodyLayout = new VerticalLayout();
				Label organizationHeader = new Label("Task List Information");
				organizationHeader.setStyleName("h2");
				bodyLayout.addComponent(organizationHeader);
				bodyLayout.addComponent(informationLayout.getLayout());

				taskListAddLayout.addBody(bodyLayout);
				taskListAddLayout.addBottomControls(createBottomPanel());
				return taskListAddLayout;
			}

			private Layout createBottomPanel() {
				HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setMargin(true);
				layout.setStyleName("addNewControl");
				Button saveBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if (TaskGroupAddWindow.TaskListForm.this
										.validateForm(taskList)) {
									saveTaskList();
									TaskGroupAddWindow.this.close();
								}
							}
						});
				saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(saveBtn);

				Button saveAndNewBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_NEW_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								if (TaskGroupAddWindow.TaskListForm.this
										.validateForm(taskList)) {
									saveTaskList();
									taskList = new SimpleTaskList();
								}
							}
						});
				saveAndNewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(saveAndNewBtn);

				Button cancelBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(ClickEvent event) {
								TaskGroupAddWindow.this.close();
							}
						});
				cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(cancelBtn);

				return layout;
			}

			private void saveTaskList() {
				ProjectTaskListService taskListService = AppContext
						.getSpringBean(ProjectTaskListService.class);
				taskList.setSaccountid(AppContext.getAccountId());
				taskList.setCreatedtime(new GregorianCalendar().getTime());
				taskList.setStatus("Open");
				taskList.setProjectid(CurrentProjectVariables.getProjectId());
				taskListService.saveWithSession(taskList,
						AppContext.getUsername());
				notifyToReloadTaskList();
			}

			@Override
			public void attachField(Object propertyId, Field field) {
				if (propertyId.equals("name")) {
					informationLayout.addComponent(field, "Name", 0, 0, 2,
							"100%");
				} else if (propertyId.equals("description")) {
					informationLayout.addComponent(field, "Description", 0, 1,
							2, "100%");
				} else if (propertyId.equals("owner")) {
					informationLayout.addComponent(field, "Responsible User",
							0, 2);
				} else if (propertyId.equals("milestoneid")) {
					informationLayout.addComponent(field, "Related Milestone",
							1, 2);
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					TextArea area = new TextArea();
					area.setNullRepresentation("");
					return area;
				} else if (propertyId.equals("owner")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("milestoneid")) {
					return new MilestoneComboBox();
				}

				if ("name".equals(propertyId)) {
					TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				}

				return null;
			}
		}
	}
}
