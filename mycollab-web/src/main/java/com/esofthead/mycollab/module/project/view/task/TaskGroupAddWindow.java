/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.GregorianCalendar;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.localization.TaskI18nEnum;
import com.esofthead.mycollab.module.project.service.ProjectTaskListService;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
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
	private final TaskGroupDisplayView taskView;
	private SimpleTaskList taskList;
	private TaskGroupAddWindow.TaskListForm taskListForm;

	public TaskGroupAddWindow(final TaskGroupDisplayView taskView,
			final SimpleTaskList taskList) {
		super(LocalizationHelper.getMessage(TaskI18nEnum.NEW_TASKGROUP_TITLE));
		this.taskView = taskView;
		this.taskList = taskList;
		this.initUI();
	}

	public TaskGroupAddWindow(final TaskGroupDisplayView taskView) {
		super(LocalizationHelper.getMessage(TaskI18nEnum.NEW_TASKGROUP_TITLE));
		this.taskList = new SimpleTaskList();
		this.taskView = taskView;

		this.initUI();
	}

	private void initUI() {
		this.setWidth("800px");
		this.taskListForm = new TaskGroupAddWindow.TaskListForm();
		this.taskListForm.setItemDataSource(new BeanItem(this.taskList));
		this.addComponent(this.taskListForm);
		((VerticalLayout) this.getContent()).setMargin(false);

		this.center();
	}

	private void notifyToReloadTaskList() {
		this.taskView.insertTaskList(this.taskList);
	}

	private class TaskListForm extends AdvancedEditBeanForm<TaskList> {
		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new TaskGroupAddWindow.TaskListForm.TaskListFormLayoutFactory());
			this.setFormFieldFactory(new TaskGroupAddWindow.TaskListForm.EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class TaskListFormLayoutFactory implements IFormLayoutFactory {
			private static final long serialVersionUID = 1L;
			private GridFormLayoutHelper informationLayout;

			@Override
			public Layout getLayout() {
				final VerticalLayout taskListAddLayout = new VerticalLayout();
				taskListAddLayout.setMargin(false);
				taskListAddLayout.setWidth("100%");

				this.informationLayout = new GridFormLayoutHelper(2, 3, "100%",
						"167px", Alignment.MIDDLE_LEFT);

				final VerticalLayout bodyLayout = new VerticalLayout();
				this.informationLayout.getLayout().setMargin(false);
				this.informationLayout.getLayout().setWidth("100%");
				this.informationLayout.getLayout().addStyleName(
						"colored-gridlayout");
				bodyLayout.addComponent(this.informationLayout.getLayout());

				taskListAddLayout.addComponent(bodyLayout);
				final Layout bottomPanel = this.createBottomPanel();
				taskListAddLayout.addComponent(bottomPanel);
				taskListAddLayout.setComponentAlignment(bottomPanel,
						Alignment.MIDDLE_CENTER);
				return taskListAddLayout;
			}

			private Layout createBottomPanel() {
				final HorizontalLayout layout = new HorizontalLayout();
				layout.setSpacing(true);
				layout.setMargin(true);
				layout.setStyleName("addNewControl");
				final Button saveBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								if (TaskGroupAddWindow.TaskListForm.this
										.validateForm(TaskGroupAddWindow.this.taskList)) {
									TaskListFormLayoutFactory.this
											.saveTaskList();
									TaskGroupAddWindow.this.close();
								}
							}
						});
				saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(saveBtn);

				final Button saveAndNewBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_SAVE_NEW_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								if (TaskGroupAddWindow.TaskListForm.this
										.validateForm(TaskGroupAddWindow.this.taskList)) {
									TaskListFormLayoutFactory.this
											.saveTaskList();
									TaskGroupAddWindow.this.taskList = new SimpleTaskList();
								}
							}
						});
				saveAndNewBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(saveAndNewBtn);

				final Button cancelBtn = new Button(
						LocalizationHelper
								.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
						new Button.ClickListener() {
							private static final long serialVersionUID = 1L;

							@Override
							public void buttonClick(final ClickEvent event) {
								TaskGroupAddWindow.this.close();
							}
						});
				cancelBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
				layout.addComponent(cancelBtn);

				return layout;
			}

			private void saveTaskList() {
				final ProjectTaskListService taskListService = AppContext
						.getSpringBean(ProjectTaskListService.class);
				TaskGroupAddWindow.this.taskList.setSaccountid(AppContext
						.getAccountId());
				TaskGroupAddWindow.this.taskList
						.setCreatedtime(new GregorianCalendar().getTime());
				TaskGroupAddWindow.this.taskList.setStatus("Open");
				TaskGroupAddWindow.this.taskList
						.setProjectid(CurrentProjectVariables.getProjectId());
				taskListService.saveWithSession(
						TaskGroupAddWindow.this.taskList,
						AppContext.getUsername());
				TaskGroupAddWindow.this.notifyToReloadTaskList();
			}

			@Override
			public void attachField(final Object propertyId, final Field field) {
				if (propertyId.equals("name")) {
					this.informationLayout.addComponent(field, "Name", 0, 0, 2,
							"100%");
				} else if (propertyId.equals("description")) {
					this.informationLayout.addComponent(field, "Description",
							0, 1, 2, "100%");
				} else if (propertyId.equals("owner")) {
					this.informationLayout
							.addComponent(
									field,
									LocalizationHelper
											.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD),
									0, 2);
				} else if (propertyId.equals("milestoneid")) {
					this.informationLayout.addComponent(field,
							"Related Milestone", 1, 2);
				}
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("description")) {
					final TextArea area = new TextArea();
					area.setNullRepresentation("");
					return area;
				} else if (propertyId.equals("owner")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("milestoneid")) {
					return new MilestoneComboBox();
				}

				if ("name".equals(propertyId)) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter task group name");
					return tf;
				}

				return null;
			}
		}
	}
}
