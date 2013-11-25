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

import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.ui.components.ProjectTaskListComboBox;
import com.esofthead.mycollab.module.project.ui.components.TaskPercentageCompleteComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.AttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormAttachmentUploadField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskAddViewImpl extends AbstractView implements TaskAddView {

	private static final long serialVersionUID = 1L;
	private final EditForm editForm;
	private Task task;
	private FormAttachmentUploadField attachmentUploadField;

	public TaskAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public void editItem(final Task item) {
		this.task = item;
		this.editForm.setItemDataSource(new BeanItem<Task>(this.task));
	}

	@Override
	public AttachmentUploadField getAttachUploadField() {
		return this.attachmentUploadField;
	}

	private class EditForm extends AdvancedEditBeanForm<Task> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource);
		}

		private class FormLayoutFactory extends TaskFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(TaskAddViewImpl.this.task.getId() == null) ? "Create Task"
								: TaskAddViewImpl.this.task.getTaskname());
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<Task>(
						EditForm.this)).createButtonControls();
				controlButtons.setSizeUndefined();
				controlPanel.addComponent(controlButtons);
				controlPanel.setWidth("100%");
				controlPanel.setComponentAlignment(controlButtons,
						Alignment.MIDDLE_CENTER);
				return controlPanel;
			}

			@Override
			protected Layout createTopPanel() {
				return this.createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return this.createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId,
					final com.vaadin.ui.Component uiContext) {
				if (propertyId.equals("assignuser")) {
					return new ProjectMemberComboBox();
				} else if (propertyId.equals("tasklistid")) {
					return new ProjectTaskListComboBox();
				} else if (propertyId.equals("notes")) {
					final RichTextArea richTextArea = new RichTextArea();
					richTextArea.setNullRepresentation("");
					return richTextArea;
				} else if ("name".equals(propertyId)) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				} else if ("percentagecomplete".equals(propertyId)) {
					return new TaskPercentageCompleteComboBox();
				} else if ("priority".equals(propertyId)) {
					return new TaskPriorityComboBox();
				} else if (propertyId.equals("id")) {
					TaskAddViewImpl.this.attachmentUploadField = new FormAttachmentUploadField();
					if (TaskAddViewImpl.this.task.getId() != null) {
						String attachmentPath = AttachmentUtils
								.getProjectEntityAttachmentPath(
										AppContext.getAccountId(),
										CurrentProjectVariables.getProjectId(),
										AttachmentType.PROJECT_TASK_TYPE,
										TaskAddViewImpl.this.task.getId());
						TaskAddViewImpl.this.attachmentUploadField
								.getAttachments(attachmentPath);
					}
					return TaskAddViewImpl.this.attachmentUploadField;
				}
				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<Task> getEditFormHandlers() {
		return this.editForm;
	}
}
