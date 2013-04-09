/*
1 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.core.utils.StringUtil;
import com.esofthead.mycollab.module.file.AttachmentConstants;
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
import com.vaadin.terminal.ThemeResource;
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
	public void setItemDataSource(Item newDataSource) {
		BeanItem<SimpleTask> beanItem = (BeanItem<SimpleTask>) newDataSource;
		task = beanItem.getBean();

		this.setFormLayoutFactory(getFormLayoutFactory());
		this.setFormFieldFactory(new DefaultFormViewFieldFactory() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Field onCreateField(Item item, Object propertyId,
					Component uiContext) {

				if (propertyId.equals("assignuser")) {
					return new ProjectUserFormLinkField(task.getAssignuser(),
							task.getAssignUserFullName());
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
								public void buttonClick(Button.ClickEvent event) {
									EventBus.getInstance().fireEvent(
											new TaskListEvent.GotoRead(this,
													task.getTasklistid()));
								}
							});
				} else if (propertyId.equals("id")) {
					return new FormAttachmentDisplayField(
							AttachmentConstants.PROJECT_TASK_TYPE, task.getId());
				} else if (propertyId.equals("priority")) {
					if (StringUtil.isNotNullOrEmpty(task.getPriority())) {
						ThemeResource iconPriority = TaskPriorityComboBox
								.getIconResourceByPriority(task.getPriority());
						Embedded iconEmbedded = new Embedded(null, iconPriority);
						Label lbPriority = new Label(task.getPriority());

						FormContainerHorizontalViewField containerField = new FormContainerHorizontalViewField();
						containerField.addComponentField(iconEmbedded);
						lbPriority.setWidth("220px");
						containerField.addComponentField(lbPriority);
						return containerField;
					}
				} else if (propertyId.equals("notes")) {
					return new FormViewField(task.getNotes(),
							Label.CONTENT_XHTML);
				}
				return null;
			}
		});
		super.setItemDataSource(newDataSource);
	}

	@Override
	protected void doPrint() {
		taskDoPrint();
	}

	@Override
	protected void showHistory() {
		taskShowHistory();
	}

	abstract protected void taskDoPrint();

	abstract protected void taskShowHistory();

	abstract TaskFormLayoutFactory getFormLayoutFactory();
}
