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
1 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.file.AttachmentType;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.ui.components.DefaultProjectFormViewFieldFactory.ProjectFormAttachmentDisplayField;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.server.Resource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;

/**
 * 
 * @author haiphucnguyen
 */
public abstract class TaskFormComponent extends AdvancedPreviewBeanForm<Task> {
	private static final long serialVersionUID = 1L;
	private SimpleTask task;

	@Override
	public void setItemDataSource(final Item newDataSource) {
		final BeanItem<SimpleTask> beanItem = (BeanItem<SimpleTask>) newDataSource;
		this.task = beanItem.getBean();

		this.setFormLayoutFactory(this.getFormLayoutFactory());

		this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(final Item item,
					final Object propertyId, final Component uiContext) {

				if (propertyId.equals("assignuser")) {
					return new ProjectUserFormLinkField(task.getAssignuser(),
							task.getAssignUserAvatarId(), task
									.getAssignUserFullName());
				} else if (propertyId.equals("taskListName")) {
					return new DefaultFormViewFieldFactory.FormViewField(task
							.getTaskListName());
				} else if (propertyId.equals("startdate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(task.getStartdate()));
				} else if (propertyId.equals("enddate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(task.getEnddate()));
				} else if (propertyId.equals("actualstartdate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(task.getActualstartdate()));
				} else if (propertyId.equals("actualenddate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(task.getActualenddate()));
				} else if (propertyId.equals("deadline")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(task.getDeadline()));
				} else if (propertyId.equals("tasklistid")) {
					return new DefaultFormViewFieldFactory.FormLinkViewField(
							task.getTaskListName(), new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(
										final Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskListEvent.GotoRead(this,
													task.getTasklistid()));
								}
							});
				} else if (propertyId.equals("id")) {
					return new ProjectFormAttachmentDisplayField(task
							.getProjectid(), AttachmentType.PROJECT_TASK_TYPE,
							task.getId());
				} else if (propertyId.equals("priority")) {
					if (StringUtils.isNotNullOrEmpty(task.getPriority())) {
						final Resource iconPriority = TaskPriorityComboBox
								.getIconResourceByPriority(task.getPriority());
						final Embedded iconEmbedded = new Embedded(null,
								iconPriority);
						final Label lbPriority = new Label(task.getPriority());

						final FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
						containerField.addComponentField(iconEmbedded);
						containerField.getLayout().setComponentAlignment(
								iconEmbedded, Alignment.MIDDLE_LEFT);
						lbPriority.setWidth("220px");
						containerField.addComponentField(lbPriority);
						containerField.getLayout().setExpandRatio(lbPriority,
								1.0f);
						return containerField;
					}
				} else if (propertyId.equals("notes")) {
					return new FormDetectAndDisplayUrlViewField(task.getNotes());
				}
				return null;
			}
		});
		super.setItemDataSource(newDataSource);
	}

	@Override
	protected void doPrint() {
		this.taskDoPrint();
	}

	@Override
	protected void showHistory() {
		this.taskShowHistory();
	}

	abstract protected void taskDoPrint();

	abstract protected void taskShowHistory();

	abstract TaskFormLayoutFactory getFormLayoutFactory();
}
