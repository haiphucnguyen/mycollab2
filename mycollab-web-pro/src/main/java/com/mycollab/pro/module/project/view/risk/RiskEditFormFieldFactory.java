package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.module.file.AttachmentUtils;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.view.milestone.MilestoneComboBox;
import com.mycollab.module.project.view.settings.component.ProjectMemberSelectionField;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.I18nValueComboBox;
import com.mycollab.vaadin.web.ui.ValueComboBox;
import com.mycollab.vaadin.web.ui.field.AttachmentUploadField;
import com.vaadin.data.Property;
import com.vaadin.ui.Field;
import com.vaadin.ui.RichTextArea;
import com.vaadin.ui.TextField;
import org.vaadin.teemu.ratingstars.RatingStars;

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
    protected Field<?> onCreateField(Object propertyId) {
        Risk risk = attachForm.getBean();
        if (Risk.Field.description.equalTo(propertyId)) {
            final RichTextArea desc = new RichTextArea();
            desc.setRequired(true);
            desc.setNullRepresentation("");
            desc.setRequiredError("Description must be not empty");
            return desc;
        } else if (Risk.Field.raisedbyuser.equalTo(propertyId)) {
            if (risk.getRaisedbyuser() == null) {
                risk.setRaisedbyuser(AppContext.getUsername());
            }
            return new ProjectMemberSelectionField();
        } else if (Risk.Field.assigntouser.equalTo(propertyId)) {
            return new ProjectMemberSelectionField();
        } else if (Risk.Field.response.equalTo(propertyId)) {
            return new RichTextArea();
        } else if (Risk.Field.consequence.equalTo(propertyId)) {
            if (risk.getConsequence() == null) {
                risk.setConsequence("Marginal");
            }
            return new ValueComboBox(false, "Catastrophic", "Critical", "Marginal", "Negligible");
        } else if (Risk.Field.probalitity.equalTo(propertyId)) {
            if (risk.getProbalitity() == null) {
                risk.setProbalitity("Possible");
            }
            return new ValueComboBox(false, "Certain", "Likely", "Possible", "Unlikely", "Rare");
        } else if (Risk.Field.status.equalTo(propertyId)) {
            if (risk.getStatus() == null) {
                risk.setStatus(StatusI18nEnum.Open.name());
            }
            return new I18nValueComboBox(false, StatusI18nEnum.Open, StatusI18nEnum.Closed);
        } else if (Risk.Field.level.equalTo(propertyId)) {
            final RatingStars ratingField = new RatingStars();
            ratingField.setMaxValue(5);
            ratingField.setImmediate(true);
            ratingField.setDescription("Risk level");
            if (risk.getLevel() != null) {
                ratingField.setValue(risk.getLevel());
            }
            ratingField.setValueCaption(RiskAddViewImpl.getValueCaptions().values().toArray(new String[5]));

            ratingField.addValueChangeListener(new Property.ValueChangeListener() {
                private static final long serialVersionUID = -3277119031169194273L;

                @Override
                public void valueChange(final Property.ValueChangeEvent event) {
                    final Double value = (Double) event.getProperty().getValue();
                    final RatingStars changedRs = (RatingStars) event.getProperty();

                    // reset value captions
                    changedRs.setValueCaption(RiskAddViewImpl.getValueCaptions().values().toArray(new String[5]));
                    // set "Your Rating" caption
                    if (value == null) {
                        changedRs.setValue(3d);
                    } else {
                        changedRs.setValueCaption((int) Math.round(value), "Your Rating");
                    }

                }
            });
            return ratingField;
        } else if (Risk.Field.riskname.equalTo(propertyId)) {
            final TextField tf = new TextField();
            tf.setNullRepresentation("");
            tf.setRequired(true);
            tf.setRequiredError("Name must be not empty");
            return tf;
        } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
            return new MilestoneComboBox();
        } else if (Risk.Field.id.equalTo(propertyId)) {
            attachmentUploadField = new AttachmentUploadField();
            Risk beanItem = attachForm.getBean();
            if (beanItem.getId() != null) {
                String attachmentPath = AttachmentUtils.getProjectEntityAttachmentPath(AppContext.getAccountId(),
                        beanItem.getProjectid(), ProjectTypeConstants.RISK, "" + beanItem.getId());
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