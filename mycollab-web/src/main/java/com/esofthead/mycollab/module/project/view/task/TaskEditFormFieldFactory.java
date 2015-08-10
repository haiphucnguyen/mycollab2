package com.esofthead.mycollab.module.project.view.task;

import com.esofthead.mycollab.common.i18n.ErrorI18nEnum;
import com.esofthead.mycollab.module.file.AttachmentUtils;
import com.esofthead.mycollab.module.project.CurrentProjectVariables;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Task;
import com.esofthead.mycollab.module.project.ui.components.TaskCompleteStatusSelection;
import com.esofthead.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.esofthead.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.GenericBeanForm;
import com.esofthead.mycollab.vaadin.ui.form.field.AttachmentUploadField;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
class TaskEditFormFieldFactory  extends AbstractBeanFieldGroupEditFieldFactory<Task> {
    private static final long serialVersionUID = 1L;

    private AttachmentUploadField attachmentUploadField;

    TaskEditFormFieldFactory(GenericBeanForm<Task> form) {
        super(form);
    }

    @Override
    protected Field<?> onCreateField(final Object propertyId) {
        if (Task.Field.assignuser.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        } else if (Task.Field.milestoneid.equalTo(propertyId)) {
            return new MilestoneComboBox();
        } else if (Task.Field.notes.equalTo(propertyId)) {
            final RichTextArea richTextArea = new RichTextArea();
            richTextArea.setNullRepresentation("");
            return richTextArea;
        } else if (Task.Field.taskname.equalTo(propertyId)) {
            final TextField tf = new TextField();
            tf.setNullRepresentation("");
            tf.setRequired(true);
            tf.setRequiredError(AppContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL, "Name"));
            return tf;
        } else if (Task.Field.status.equalTo(propertyId)) {
            return new TaskStatusComboBox();
        } else if (Task.Field.percentagecomplete.equalTo(propertyId)) {
            return new TaskCompleteStatusSelection();
        } else if (Task.Field.priority.equalTo(propertyId)) {
            return new TaskPriorityComboBox();
        } else if (Task.Field.id.equalTo(propertyId)) {
            attachmentUploadField = new AttachmentUploadField();
            Task beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(),
                        CurrentProjectVariables.getProjectId(), ProjectTypeConstants.TASK, "" + beanItem.getId());
                attachmentUploadField.getAttachments(attachmentPath);
            }
            return attachmentUploadField;
        }
        return null;
    }

    public AttachmentUploadField getAttachmentUploadField() {
        return attachmentUploadField;
    }
}
