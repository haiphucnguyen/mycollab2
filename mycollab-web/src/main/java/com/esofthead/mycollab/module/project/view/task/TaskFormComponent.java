/*
1 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.events.TaskListEvent;
import com.esofthead.mycollab.module.project.view.people.component.ProjectUserFormLinkField;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.ui.DefaultFormViewFieldFactory;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Resource;
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
					return new ProjectUserFormLinkField(
							TaskFormComponent.this.task.getAssignuser(),
							TaskFormComponent.this.task.getAssignUserAvatarId(),
							TaskFormComponent.this.task.getAssignUserFullName());
				} else if (propertyId.equals("taskListName")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							TaskFormComponent.this.task.getTaskListName());
				} else if (propertyId.equals("startdate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(TaskFormComponent.this.task
									.getStartdate()));
				} else if (propertyId.equals("enddate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(TaskFormComponent.this.task
									.getEnddate()));
				} else if (propertyId.equals("actualstartdate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(TaskFormComponent.this.task
									.getActualstartdate()));
				} else if (propertyId.equals("actualenddate")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(TaskFormComponent.this.task
									.getActualenddate()));
				} else if (propertyId.equals("deadline")) {
					return new DefaultFormViewFieldFactory.FormViewField(
							AppContext.formatDate(TaskFormComponent.this.task
									.getDeadline()));
				} else if (propertyId.equals("tasklistid")) {
					return new DefaultFormViewFieldFactory.FormLinkViewField(
							TaskFormComponent.this.task.getTaskListName(),
							new Button.ClickListener() {
								private static final long serialVersionUID = 1L;

								@Override
								public void buttonClick(
										final Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskListEvent.GotoRead(this,
													TaskFormComponent.this.task
															.getTasklistid()));
								}
							});
				} else if (propertyId.equals("id")) {
					return new FormAttachmentDisplayField(
							AttachmentUtils.PROJECT_TASK_TYPE,
							TaskFormComponent.this.task.getId());
				} else if (propertyId.equals("priority")) {
					if (StringUtil.isNotNullOrEmpty(TaskFormComponent.this.task
							.getPriority())) {
						final Resource iconPriority = TaskPriorityComboBox
								.getIconResourceByPriority(TaskFormComponent.this.task
										.getPriority());
						final Embedded iconEmbedded = new Embedded(null,
								iconPriority);
						final Label lbPriority = new Label(
								TaskFormComponent.this.task.getPriority());

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
					return new FormViewField(TaskFormComponent.this.task
							.getNotes(), Label.CONTENT_XHTML);
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
