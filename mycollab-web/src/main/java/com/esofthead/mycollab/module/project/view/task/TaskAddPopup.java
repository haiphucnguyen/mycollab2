/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import org.vaadin.easyuploads.MultiFileUploadExt;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.ui.components.TaskPercentageCompleteComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.shell.view.ScreenSize;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.AttachmentPanel;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GridFormLayoutHelper;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author haiphucnguyen
 */
public class TaskAddPopup extends CustomComponent {
	private static final long serialVersionUID = 1L;
	private final TabSheet taskContainer;
	private final SimpleTask task;
	private final TaskNoteLayout taskNoteComponent;

	public TaskAddPopup(final TaskDisplayComponent taskDisplayComp,
			final TaskList taskList) {

		final VerticalLayout taskLayout = new VerticalLayout();
		taskLayout.addStyleName("taskadd-popup");

		final VerticalLayout popupHeader = new VerticalLayout();
		popupHeader.setWidth("100%");
		popupHeader.addStyleName("popup-header");

		final Label titleLbl = new Label("Add New Task");
		titleLbl.addStyleName("bold");
		popupHeader.addComponent(titleLbl);
		taskLayout.addComponent(popupHeader);

		this.task = new SimpleTask();
		this.taskContainer = new TabSheet();
		final TaskInformationLayout taskInformationLayout = new TaskInformationLayout();
		taskInformationLayout.setWidth("100%");
		this.taskContainer.addTab(taskInformationLayout, LocalizationHelper
				.getMessage(GenericI18Enum.INFORMATION_WINDOW_TITLE));

		this.taskNoteComponent = new TaskNoteLayout();
		this.taskContainer.addTab(this.taskNoteComponent, "Note & Attachments");

		taskLayout.addComponent(this.taskContainer);

		final HorizontalLayout controlsLayout = new HorizontalLayout();
		controlsLayout.setSpacing(true);

		final Button cancelBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						taskDisplayComp.closeTaskAdd();
					}
				});

		cancelBtn.setStyleName("link");
		controlsLayout.addComponent(cancelBtn);
		controlsLayout
				.setComponentAlignment(cancelBtn, Alignment.MIDDLE_CENTER);

		final Button saveBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_SAVE_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(final ClickEvent event) {
						final ProjectTaskService taskService = ApplicationContextUtil
								.getSpringBean(ProjectTaskService.class);

						TaskAddPopup.this.task.setTasklistid(taskList.getId());
						TaskAddPopup.this.task
								.setProjectid(CurrentProjectVariables
										.getProjectId());
						TaskAddPopup.this.task.setSaccountid(AppContext
								.getAccountId());
						TaskAddPopup.this.task
								.setNotes(TaskAddPopup.this.taskNoteComponent
										.getNote());
						if (taskInformationLayout
								.validateForm(TaskAddPopup.this.task)) {
							taskService.saveWithSession(TaskAddPopup.this.task,
									AppContext.getUsername());
							TaskAddPopup.this.taskNoteComponent
									.saveContentsToRepo(TaskAddPopup.this.task
											.getId());
							taskDisplayComp
									.saveTaskSuccess(TaskAddPopup.this.task);
							taskDisplayComp.closeTaskAdd();
						}
					}
				});
		saveBtn.setIcon(MyCollabResource.newResource("icons/16/save.png"));
		saveBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
		controlsLayout.addComponent(saveBtn);
		controlsLayout.setComponentAlignment(saveBtn, Alignment.MIDDLE_CENTER);
		controlsLayout.addStyleName("popup-footer");
		controlsLayout.setMargin(true);

		taskLayout.addComponent(controlsLayout);

		this.setCompositionRoot(taskLayout);
	}

	private class TaskInformationLayout extends AdvancedEditBeanForm<Task> {
		private static final long serialVersionUID = 1L;

		public TaskInformationLayout() {
			this.setFormLayoutFactory(new TaskLayout());
			this.setFormFieldFactory(new EditFormFieldFactory());
			this.setItemDataSource(new BeanItem<Task>(TaskAddPopup.this.task));
		}
	}

	private static class TaskLayout implements IFormLayoutFactory {
		private static final long serialVersionUID = 1L;
		private GridFormLayoutHelper informationLayout;

		@Override
		public Layout getLayout() {
			this.informationLayout = new GridFormLayoutHelper(2, 5);

			if (ScreenSize.hasSupport1024Pixels()) {
				this.informationLayout = new GridFormLayoutHelper(2, 5,
						UIConstants.DEFAULT_CONTROL_WIDTH_1024_RESOLUTION,
						"150px");
			}

			final VerticalLayout layout = new VerticalLayout();
			this.informationLayout.getLayout().addStyleName(
					"colored-gridlayout");
			this.informationLayout.getLayout().setMargin(false);
			this.informationLayout.getLayout().setWidth("100%");
			layout.addComponent(this.informationLayout.getLayout());
			return layout;
		}

		@Override
		public void attachField(final Object propertyId, final Field field) {
			if (propertyId.equals("taskname")) {
				this.informationLayout.addComponent(field, "Task Name", 0, 0,
						2, "100%");
			} else if (propertyId.equals("startdate")) {
				this.informationLayout.addComponent(field, "Start Date", 0, 1);
			} else if (propertyId.equals("enddate")) {
				this.informationLayout.addComponent(field, "End Date", 0, 2);
			} else if (propertyId.equals("actualstartdate")) {
				this.informationLayout.addComponent(field, "Actual Start Date",
						1, 1);
			} else if (propertyId.equals("actualenddate")) {
				this.informationLayout.addComponent(field, "Actual End Date",
						1, 2);
			} else if (propertyId.equals("deadline")) {
				this.informationLayout.addComponent(field, "Deadline", 0, 3);
			} else if (propertyId.equals("priority")) {
				this.informationLayout.addComponent(field, "Priority", 1, 3);
			} else if (propertyId.equals("assignuser")) {
				this.informationLayout.addComponent(field, LocalizationHelper
						.getMessage(GenericI18Enum.FORM_ASSIGNEE_FIELD), 0, 4);
			} else if (propertyId.equals("percentagecomplete")) {
				this.informationLayout.addComponent(field, "Complete(%)", 1, 4);
			}
		}
	}

	private class TaskNoteLayout extends VerticalLayout {
		private static final long serialVersionUID = 1L;
		private final RichTextArea noteArea;
		private final AttachmentPanel attachmentPanel;

		public TaskNoteLayout() {
			this.setSpacing(true);
			this.setMargin(true);
			this.noteArea = new RichTextArea();
			this.noteArea.setWidth("800px");
			this.noteArea.setHeight("200px");
			this.addComponent(this.noteArea);

			this.attachmentPanel = new AttachmentPanel();
			this.addComponent(this.attachmentPanel);
			final MultiFileUploadExt uploadExt = new MultiFileUploadExt(
					this.attachmentPanel);
			this.addComponent(uploadExt);
			this.setComponentAlignment(uploadExt, Alignment.MIDDLE_LEFT);
		}

		public String getNote() {
			return (String) this.noteArea.getValue();
		}

		void saveContentsToRepo(final Integer typeid) {
			String attachmentPath = AttachmentUtils
					.getProjectEntityAttachmentPath(AppContext.getAccountId(),
							CurrentProjectVariables.getProjectId(),
							AttachmentType.PROJECT_TASK_TYPE, typeid);
			this.attachmentPanel.saveContentsToRepo(attachmentPath);
		}
	}

	private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

		private static final long serialVersionUID = 1L;

		@Override
		protected Field onCreateField(final Item item, final Object propertyId,
				final com.vaadin.ui.Component uiContext) {
			if (propertyId.equals("assignuser")) {
				return new ProjectMemberComboBox();
			} else if (propertyId.equals("taskname")) {
				final TextField tf = new TextField();
				tf.setNullRepresentation("");
				tf.setRequired(true);
				tf.setRequiredError("Please enter a Task Name");
				return tf;
			} else if (propertyId.equals("percentagecomplete")) {
				if (TaskAddPopup.this.task.getPercentagecomplete() == null) {
					TaskAddPopup.this.task.setPercentagecomplete(0d);
				}

				return new TaskPercentageCompleteComboBox();
			} else if ("priority".equals(propertyId)) {
				if (TaskAddPopup.this.task.getPriority() == null) {
					TaskAddPopup.this.task
							.setPriority(TaskPriorityComboBox.PRIORITY_MEDIUM);
				}
				return new TaskPriorityComboBox();
			}
			return null;
		}
	}
}
