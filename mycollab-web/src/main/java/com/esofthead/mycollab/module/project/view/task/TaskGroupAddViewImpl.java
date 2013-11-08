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

import java.util.Collection;

import com.esofthead.mycollab.module.project.domain.SimpleTaskList;
import com.esofthead.mycollab.module.project.domain.TaskList;
import com.esofthead.mycollab.module.project.ui.components.ProjectMilestoneComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberComboBox;
import com.esofthead.mycollab.vaadin.events.HasEditFormHandlers;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.AdvancedEditBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultEditFormFieldFactory;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory.FormContainerHorizontalViewField;
import com.esofthead.mycollab.vaadin.ui.EditFormControlsGenerator;
import com.esofthead.mycollab.vaadin.ui.ProgressPercentageIndicator;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Field;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
	private final EditForm editForm;
	private TaskList taskList;

	public TaskGroupAddViewImpl() {
		super();
		this.editForm = new EditForm();
		this.addComponent(this.editForm);
		this.setMargin(true);
	}

	@Override
	public void editItem(final TaskList item) {
		this.taskList = item;
		this.editForm.setItemDataSource(new BeanItem<TaskList>(this.taskList));
	}

	private class EditForm extends AdvancedEditBeanForm<TaskList> {

		private static final long serialVersionUID = 1L;

		@Override
		public void setItemDataSource(final Item newDataSource,
				final Collection<?> propertyIds) {
			this.setFormLayoutFactory(new FormLayoutFactory());
			this.setFormFieldFactory(new EditFormFieldFactory());
			super.setItemDataSource(newDataSource, propertyIds);
		}

		private class FormLayoutFactory extends TaskGroupFormLayoutFactory {

			private static final long serialVersionUID = 1L;

			public FormLayoutFactory() {
				super(
						(TaskGroupAddViewImpl.this.taskList.getName() != null) ? TaskGroupAddViewImpl.this.taskList
								.getName() : "Create Task Group");
			}

			private Layout createButtonControls() {
				final HorizontalLayout controlPanel = new HorizontalLayout();
				final Layout controlButtons = (new EditFormControlsGenerator<TaskList>(
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
				if ("owner".equals(propertyId)) {
					return new ProjectMemberComboBox();
				} else if ("milestoneid".equals(propertyId)) {
					return new ProjectMilestoneComboBox();
				} else if ("description".equals(propertyId)) {
					return new RichTextArea();
				}

				if ("name".equals(propertyId)) {
					final TextField tf = new TextField();
					tf.setNullRepresentation("");
					tf.setRequired(true);
					tf.setRequiredError("Please enter a Name");
					return tf;
				} else if (propertyId.equals("percentageComplete")) {
					final double percentage = (TaskGroupAddViewImpl.this.taskList instanceof SimpleTaskList) ? ((SimpleTaskList) TaskGroupAddViewImpl.this.taskList)
							.getPercentageComplete() : 0;
					final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
					final ProgressPercentageIndicator progressField = new ProgressPercentageIndicator(
							percentage);
					fieldContainer.addComponentField(progressField);
					return fieldContainer;
				} else if (propertyId.equals("numOpenTasks")) {
					final int openTask = (TaskGroupAddViewImpl.this.taskList instanceof SimpleTaskList) ? ((SimpleTaskList) TaskGroupAddViewImpl.this.taskList)
							.getNumOpenTasks() : 0;
					final int allTasks = (TaskGroupAddViewImpl.this.taskList instanceof SimpleTaskList) ? ((SimpleTaskList) TaskGroupAddViewImpl.this.taskList)
							.getNumAllTasks() : 0;
					final FormContainerHorizontalViewField fieldContainer = new FormContainerHorizontalViewField();
					final Label numTaskLbl = new Label("(" + openTask + "/"
							+ allTasks + ")");
					fieldContainer.addComponentField(numTaskLbl);
					return fieldContainer;
				}

				return null;
			}
		}
	}

	@Override
	public HasEditFormHandlers<TaskList> getEditFormHandlers() {
		return this.editForm;
	}
}
