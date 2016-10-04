package com.mycollab.premium.mobile.module.project.view.bug;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.ui.PriorityComboBox;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.bug.BugAddView;
import com.mycollab.mobile.module.project.view.bug.BugDefaultFormLayoutFactory;
import com.mycollab.mobile.module.project.view.bug.BugSeverityComboBox;
import com.mycollab.mobile.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
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
        return new DynaFormLayout(ProjectTypeConstants.BUG, BugDefaultFormLayoutFactory.getForm());
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
            if (propertyId.equals("environment")) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            } else if (propertyId.equals("description")) {
                final TextArea field = new TextArea("", "");
                field.setNullRepresentation("");
                return field;
            } else if (propertyId.equals("duedate") || propertyId.equals("startdate") || propertyId.equals("enddate")) {
                return new DatePicker();
            } else if (propertyId.equals("priority")) {
                if (beanItem.getPriority() == null) {
                    beanItem.setPriority(Priority.Medium.name());
                }
                return new PriorityComboBox();
            } else if (BugWithBLOBs.Field.assignuser.equalTo(propertyId)) {
                return new ProjectMemberSelectionField();
            } else if (propertyId.equals("severity")) {
                if (beanItem.getSeverity() == null) {
                    beanItem.setSeverity(BugSeverity.Major.name());
                }
                return new BugSeverityComboBox();
            } else if (BugWithBLOBs.Field.name.equalTo(propertyId)) {
                final MTextField tf = new MTextField();
                if (isValidateForm) {
                    tf.withNullRepresentation("").withRequired(true).withRequiredError(UserUIContext.getMessage
                            (ErrorI18nEnum.FIELD_MUST_NOT_NULL, UserUIContext.getMessage(BugI18nEnum.FORM_SUMMARY)));
                }

                return tf;
            } else if (BugWithBLOBs.Field.milestoneid.equalTo(propertyId)) {
                final MilestoneComboBox milestoneBox = new MilestoneComboBox();
                milestoneBox.addValueChangeListener(valueChangeEvent -> {
                    String milestoneName = milestoneBox.getItemCaption(milestoneBox.getValue());
                    beanItem.setMilestoneName(milestoneName);
                });
                return milestoneBox;
            } else if (propertyId.equals("estimatetime") || (propertyId.equals("estimateremaintime"))) {
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
