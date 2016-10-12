package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.ui.PriorityListSelect;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.BugDefaultFormLayoutFactory;
import com.mycollab.mobile.module.project.view.bug.BugSeverityListSelect;
import com.mycollab.mobile.module.project.view.milestone.MilestoneListSelect;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberListSelect;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.i18n.BugI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.BugSeverity;
import com.mycollab.module.project.i18n.OptionI18nEnum.Priority;
import com.mycollab.module.tracker.domain.BugWithBLOBs;
import com.mycollab.module.tracker.domain.SimpleBug;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.addon.touchkit.ui.NumberField;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;
import org.vaadin.viritin.fields.MTextField;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class BugAddViewImpl extends AbstractEditItemComp<SimpleBug> implements BugAddView {
    private static final long serialVersionUID = -688386159095055595L;

    private ProjectFormAttachmentUploadField attachmentUploadField;

    @Override
    protected String initFormTitle() {
        return beanItem.getId() == null ? UserUIContext.getMessage(BugI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    public void editItem(SimpleBug item) {
        attachmentUploadField = new ProjectFormAttachmentUploadField();
        if (item.getId() != null) {
            attachmentUploadField.getAttachments(item.getProjectid(), ProjectTypeConstants.BUG, item.getId());
        }
        super.editItem(item);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.BUG, BugDefaultFormLayoutFactory.getForm(), "status");
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleBug> initBeanFormFieldFactory() {
        return new EditFormFieldFactory(this.editForm);
    }

    private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleBug> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<SimpleBug> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (BugWithBLOBs.Field.environment.equalTo(propertyId) || BugWithBLOBs.Field.description.equalTo(propertyId)) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            } else if (BugWithBLOBs.Field.startdate.equalTo(propertyId) || BugWithBLOBs.Field.enddate.equalTo(propertyId)
                    || BugWithBLOBs.Field.duedate.equalTo(propertyId)) {
                return new DatePicker();
            } else if (BugWithBLOBs.Field.priority.equalTo(propertyId)) {
                if (beanItem.getPriority() == null) {
                    beanItem.setPriority(Priority.Medium.name());
                }
                return new PriorityListSelect();
            } else if (BugWithBLOBs.Field.assignuser.equalTo(propertyId)) {
                return new ProjectMemberListSelect();
            } else if (BugWithBLOBs.Field.severity.equalTo(propertyId)) {
                if (beanItem.getSeverity() == null) {
                    beanItem.setSeverity(BugSeverity.Major.name());
                }
                return new BugSeverityListSelect();
            } else if (BugWithBLOBs.Field.name.equalTo(propertyId)) {
                final MTextField tf = new MTextField();
                if (isValidateForm) {
                    tf.withNullRepresentation("").withRequired(true).withRequiredError(UserUIContext.getMessage
                            (ErrorI18nEnum.FIELD_MUST_NOT_NULL, UserUIContext.getMessage(BugI18nEnum.FORM_SUMMARY)));
                }

                return tf;
            } else if (BugWithBLOBs.Field.milestoneid.equalTo(propertyId)) {
                final MilestoneListSelect milestoneBox = new MilestoneListSelect();
                milestoneBox.addValueChangeListener(valueChangeEvent -> {
                    String milestoneName = milestoneBox.getItemCaption(milestoneBox.getValue());
                    beanItem.setMilestoneName(milestoneName);
                });
                return milestoneBox;
            } else if (BugWithBLOBs.Field.estimatetime.equalTo(propertyId) || BugWithBLOBs.Field.estimateremaintime.equalTo(propertyId)) {
                return new NumberField();
            }

            return null;
        }
    }

    @Override
    public ProjectFormAttachmentUploadField getAttachUploadField() {
        return attachmentUploadField;
    }
}
