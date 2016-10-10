package com.mycollab.premium.mobile.module.project.view.task;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.eventmanager.EventBusFactory;
import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.events.TicketEvent;
import com.mycollab.mobile.module.project.ui.PriorityListSelect;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.milestone.MilestoneListSelect;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberListSelect;
import com.mycollab.mobile.module.project.view.task.TaskAddView;
import com.mycollab.mobile.module.project.view.task.TaskDefaultFormLayoutFactory;
import com.mycollab.mobile.module.project.view.task.TaskPercentageCompleteListSelect;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleTask;
import com.mycollab.module.project.domain.Task;
import com.mycollab.module.project.i18n.TaskI18nEnum;
import com.mycollab.module.project.i18n.TicketI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import org.vaadin.viritin.fields.MTextField;

/**
 * @author MyCollab Ltd.
 * @since 4.5.0
 */
@ViewComponent
public class TaskAddViewImpl extends AbstractEditItemComp<SimpleTask> implements TaskAddView {
    private static final long serialVersionUID = 6835605062072536907L;

    private ProjectFormAttachmentUploadField attachmentUploadField;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? UserUIContext.getMessage(TaskI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    public void editItem(SimpleTask item) {
        attachmentUploadField = new ProjectFormAttachmentUploadField();
        if (item.getId() != null) {
            attachmentUploadField.getAttachments(item.getProjectid(), ProjectTypeConstants.TASK, item.getId());
        }
        super.editItem(item);
        editForm.addComponent(attachmentUploadField);
    }

    @Override
    protected String getBackTitle() {
        return UserUIContext.getMessage(TicketI18nEnum.LIST);
    }

    @Override
    protected void doBackAction() {
        EventBusFactory.getInstance().post(new TicketEvent.GotoDashboard(this, null));
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.TASK, TaskDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleTask> initBeanFormFieldFactory() {
        return new TaskEditFormFieldFactory(this.editForm);
    }

    private class TaskEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleTask> {
        private static final long serialVersionUID = -1508613237858970400L;

        TaskEditFormFieldFactory(GenericBeanForm<SimpleTask> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            if (Task.Field.assignuser.equalTo(propertyId)) {
                return new ProjectMemberListSelect();
            } else if (Task.Field.description.equalTo(propertyId)) {
                final TextArea textArea = new TextArea();
                textArea.setNullRepresentation("");
                return textArea;
            } else if (Task.Field.name.equalTo(propertyId)) {
                return new MTextField().withNullRepresentation("").withRequired(true)
                        .withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                                UserUIContext.getMessage(GenericI18Enum.FORM_NAME)));
            } else if (Task.Field.percentagecomplete.equalTo(propertyId)) {
                return new TaskPercentageCompleteListSelect();
            } else if (Task.Field.priority.equalTo(propertyId)) {
                return new PriorityListSelect();
            } else if (Task.Field.startdate.equalTo(propertyId) || Task.Field.enddate.equalTo(propertyId) ||
                    Task.Field.duedate.equalTo(propertyId)) {
                return new DatePicker();
            } else if (Task.Field.status.equalTo(propertyId)) {
                return new TaskStatusListSelect();
            } else if (Task.Field.milestoneid.equalTo(propertyId)) {
                final MilestoneListSelect milestoneBox = new MilestoneListSelect();
                milestoneBox.addValueChangeListener(valueChangeEvent -> {
                    String milestoneName = milestoneBox.getItemCaption(milestoneBox.getValue());
                    beanItem.setMilestoneName(milestoneName);
                });
                return milestoneBox;
            }
            return null;
        }

    }

    @Override
    public ProjectFormAttachmentUploadField getAttachUploadField() {
        return attachmentUploadField;
    }

}
