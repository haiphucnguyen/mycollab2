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
import com.mycollab.vaadin.web.ui.WebThemes;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.data.HasValue;
import com.vaadin.ui.DateField;
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
            desc.setRequiredIndicatorVisible(true);
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
            return new I18nValueComboBox<>(RiskConsequence.class, RiskConsequence.Catastrophic, RiskConsequence.Critical,
                    RiskConsequence.Marginal, RiskConsequence.Negligible).withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Risk.Field.probability.equalTo(propertyId)) {
            if (risk.getProbability() == null) {
                risk.setProbability(RiskProbability.Possible.name());
            }
            return new I18nValueComboBox<>(RiskProbability.class, RiskProbability.Certain, RiskProbability.Likely,
                    RiskProbability.Possible, RiskProbability.Unlikely, RiskProbability.Rare).withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Risk.Field.status.equalTo(propertyId)) {
            if (risk.getStatus() == null) {
                risk.setStatus(StatusI18nEnum.Open.name());
            }
            return new I18nValueComboBox<>(StatusI18nEnum.class, StatusI18nEnum.Open, StatusI18nEnum.Closed).withWidth(WebThemes.FORM_CONTROL_WIDTH);
        } else if (Risk.Field.priority.equalTo(propertyId)) {
            return new PriorityComboBox();
        } else if (Risk.Field.name.equalTo(propertyId)) {
            return new MTextField().withRequiredIndicatorVisible(true);
        } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
            return new MilestoneComboBox();
        } else if ("section-attachments".equals(propertyId)) {
            Risk beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppUI.getAccountId(),
                        beanItem.getProjectid(), ProjectTypeConstants.RISK, "" + beanItem.getId());
                attachmentUploadField = new AttachmentUploadField(attachmentPath);
            } else {
                attachmentUploadField = new AttachmentUploadField();
            }
            return attachmentUploadField;
        } else if (Risk.Field.startdate.equalTo(propertyId) || Risk.Field.enddate.equalTo(propertyId)
                || Risk.Field.duedate.equalTo(propertyId)) {
            return new DateField();
        }

        return null;
    }

    public AttachmentUploadField getAttachmentUploadField() {
        return attachmentUploadField;
    }
}
