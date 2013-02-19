/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import java.util.Collection;

import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.ui.components.ProjectMilestoneComboBox;
import com.esofthead.mycollab.module.project.view.people.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Field;
import com.vaadin.ui.Layout;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * 
 * @author haiphucnguyen
 */
@ViewComponent
public class TaskGroupAddViewImpl extends AbstractView implements
		TaskGroupAddView {
	private static final long serialVersionUID = 1L;
	private EditForm editForm;
	private TaskList taskList;

	public TaskGroupAddViewImpl() {
		super();
		editForm = new EditForm();
		this.addComponent(editForm);
	}

	@Override
	public void editItem(TaskList item) {
		this.taskList = item;
		editForm.setItemDataSource(new BeanItem<TaskList>(taskList));
	}

	private class EditForm extends AdvancedEditBeanForm<TaskList> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(Item newDataSource,
				Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends TaskGroupFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super((taskList.getName() != null) ? taskList.getName()
						: "Create Task Group");
			}

			private Layout createButtonControls() {
				return (new EditFormControlsGenerator<TaskList>(EditForm.this))
						.createButtonControls();
			}

			@Override
			protected Layout createTopPanel() {
				return createButtonControls();
			}

			@Override
			protected Layout createBottomPanel() {
				return createButtonControls();
			}
		}

		private class EditFormFieldFactory extends DefaultEditFormFieldFactory {

			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					com.vaadin.ui.Component uiContext) {
				if ("owner".equals(propertyId)) {
					return new ProjectMemberComboBox();
				} else if ("milestoneid".equals(propertyId)) {
					return new ProjectMilestoneComboBox();
				} else if ("description".equals(propertyId)) {
					return new RichTextArea();
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

	@Override
	public HasEditFormHandlers<TaskList> getEditFormHandlers() {
		return editForm;
	}
}
