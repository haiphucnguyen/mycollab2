package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskConsequence;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskProbability;
import com.mycollab.module.project.ui.components.PriorityComboBox;
import com.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.data.HasValue;
import com.vaadin.ui.RichTextArea;
import org.vaadin.viritin.fields.MTextField;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
class RiskEditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<SimpleRisk> {
    private static final long serialVersionUID = 1L;

    private AttachmentUploadField attachmentUploadField;

    RiskEditFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
        super(form);
    }

    @Override
    protected HasValue<?> onCreateField(Object propertyId) {
        Risk risk = attachForm.getBean();
        if (Risk.Field.description.equalTo(propertyId)) {
            final RichTextArea desc = new RichTextArea();
//            if (isValidateForm) {
//                desc.setRequired(true);
//                desc.setRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
//                        UserUIContext.getMessage(GenericI18Enum.FORM_DESCRIPTION)));
//            }
            return desc;
        } else if (Risk.Field.createduser.equalTo(propertyId)) {
            if (risk.getCreateduser() == null) {
                risk.setCreateduser(UserUIContext.getUsername());
            }
            return new ProjectMemberSelectionField();
        } else if (Risk.Field.assignuser.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        } else if (Risk.Field.response.equalTo(propertyId)) {
            return new RichTextArea();
        } else if (Risk.Field.consequence.equalTo(propertyId)) {
            if (risk.getConsequence() == null) {
                risk.setConsequence(RiskConsequence.Marginal.name());
            }
            return new I18nValueComboBox(RiskConsequence.class, RiskConsequence.Catastrophic, RiskConsequence.Critical,
                    RiskConsequence.Marginal, RiskConsequence.Negligible);
        } else if (Risk.Field.probability.equalTo(propertyId)) {
            if (risk.getProbability() == null) {
                risk.setProbability(RiskProbability.Possible.name());
            }
            return new I18nValueComboBox(RiskProbability.class, RiskProbability.Certain, RiskProbability.Likely,
                    RiskProbability.Possible, RiskProbability.Unlikely, RiskProbability.Rare);
        } else if (Risk.Field.status.equalTo(propertyId)) {
            if (risk.getStatus() == null) {
                risk.setStatus(StatusI18nEnum.Open.name());
            }
            return new I18nValueComboBox(StatusI18nEnum.class, StatusI18nEnum.Open, StatusI18nEnum.Closed);
        } else if (Risk.Field.priority.equalTo(propertyId)) {
            return new PriorityComboBox();
        } else if (Risk.Field.name.equalTo(propertyId)) {
            MTextField field = new MTextField();
//            if (isValidateForm) {
//                field.withRequired(true).withRequiredError(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
//                        UserUIContext.getMessage(GenericI18Enum.FORM_NAME)));
//            }
            return field;
        } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
            return new MilestoneComboBox();
        } else if (Risk.Field.id.equalTo(propertyId)) {
            Risk beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(),
                        beanItem.getProjectid(), ProjectTypeConstants.RISK, "" + beanItem.getId());
                attachmentUploadField = new AttachmentUploadField(attachmentPath);
            } else {
                attachmentUploadField = new AttachmentUploadField();
            }
            return attachmentUploadField;
        }

        return null;
    }

    public AttachmentUploadField getAttachmentUploadField() {
        return attachmentUploadField;
    }
}
