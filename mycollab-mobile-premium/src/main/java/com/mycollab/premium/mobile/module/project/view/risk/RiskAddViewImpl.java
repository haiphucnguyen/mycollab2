package com.mycollab.premium.mobile.module.project.view.risk;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.project.ui.form.field.ProjectFormAttachmentUploadField;
import com.mycollab.mobile.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.mobile.module.project.view.settings.ProjectMemberSelectionField;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.addon.touchkit.ui.DatePicker;
import com.vaadin.ui.Field;
import com.vaadin.ui.TextArea;

/**
 * @author MyCollab Ltd
 * @since 5.4.3
 */
@ViewComponent
public class RiskAddViewImpl extends AbstractEditItemComp<SimpleRisk> implements RiskAddView {
    private static final long serialVersionUID = 6835605062072536907L;

    private ProjectFormAttachmentUploadField attachmentUploadField;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? UserUIContext.getMessage(RiskI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    public void editItem(SimpleRisk item) {
        attachmentUploadField = new ProjectFormAttachmentUploadField();
        if (item.getId() != null) {
            attachmentUploadField.getAttachments(item.getProjectid(), ProjectTypeConstants.RISK, item.getId());
        }
        super.editItem(item);
        editForm.addComponent(attachmentUploadField);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(ProjectTypeConstants.RISK, RiskDefaultFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleRisk> initBeanFormFieldFactory() {
        return new RiskEditFormFieldFactory(this.editForm);
    }

    private class RiskEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = -1508613237858970400L;

        RiskEditFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            if (Risk.Field.assignuser.equalTo(propertyId)) {
                return new ProjectMemberSelectionField();
            } else if (Risk.Field.createduser.equalTo(propertyId)) {
                return new ProjectMemberSelectionField();
            } else if (Risk.Field.description.equalTo(propertyId)) {
                final TextArea textArea = new TextArea();
                textArea.setNullRepresentation("");
                return textArea;
            } else if (Risk.Field.startdate.equalTo(propertyId) || Risk.Field.enddate.equalTo(propertyId) ||
                    Risk.Field.duedate.equalTo(propertyId)) {
                return new DatePicker();
            } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                final MilestoneComboBox milestoneBox = new MilestoneComboBox();
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
