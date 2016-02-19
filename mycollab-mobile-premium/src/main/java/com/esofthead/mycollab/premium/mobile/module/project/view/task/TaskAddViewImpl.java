package com.esofthead.mycollab.premium.mobile.module.project.view.task;

import com.esofthead.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.esofthead.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
import com.esofthead.mycollab.mobile.module.project.view.task.TaskAddView;
import com.esofthead.mycollab.mobile.module.project.view.task.TaskFormLayoutFactory;
import com.esofthead.mycollab.mobile.module.project.view.task.TaskPercentageCompleteComboBox;
import com.esofthead.mycollab.mobile.module.project.view.task.TaskPriorityComboBox;
import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.i18n.TaskI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.5.0
 */

@ViewComponent
public class TaskAddViewImpl extends AbstractEditItemComp<SimpleTask> implements TaskAddView {
	private static final long serialVersionUID = 6835605062072536907L;

	private ProjectFormAttachmentUploadField attachmentUploadField;

	@Override
	protected String initFormTitle() {
		return (beanItem.getId() == null) ? AppContext.getMessage(TaskI18nEnum.FORM_NEW_TASK_TITLE) : beanItem.getTaskname();
	}

	@Override
	public void editItem(SimpleTask item) {
		attachmentUploadField = new ProjectFormAttachmentUploadField();
		if (item.getId() != null) {
			attachmentUploadField.getAttachments(item.getProjectid(), ProjectTypeConstants.TASK, item.getId());
		}
		super.editItem(item);
		this.editForm.addComponent(attachmentUploadField);
	}

	@Override
	protected IFormLayoutFactory initFormLayoutFactory() {
		return new TaskFormLayoutFactory();
	}

	@Override
	protected AbstractBeanFieldGroupEditFieldFactory<SimpleTask> initBeanFormFieldFactory() {
		return new TaskEditFormFieldFactory(this.editForm);
	}

	static class TaskEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleTask> {
		private static final long serialVersionUID = -1508613237858970400L;

		TaskEditFormFieldFactory(GenericBeanForm<SimpleTask> form) {
			super(form);
		}

		@Override
		protected Field<?> onCreateField(Object propertyId) {
			if (propertyId.equals("assignuser")) {
				return new ProjectMemberSelectionField();
			} else if (propertyId.equals("notes")) {
				final TextArea textArea = new TextArea();
				textArea.setNullRepresentation("");
				return textArea;
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
			} else if (propertyId.equals("startdate") || propertyId.equals("actualstartdate") || propertyId.equals("enddate")
					|| propertyId.equals("actualenddate") || propertyId.equals("deadline")) {
				return new DatePicker();
			}
			return null;
		}

	}

	@Override
	public ProjectFormAttachmentUploadField getAttachUploadField() {
		return attachmentUploadField;
	}

}