package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.OptionI18nEnum;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.ui.form.ProjectFormAttachmentDisplayField;
import com.mycollab.module.project.ui.form.ProjectItemViewField;
import com.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.field.DateViewField;
import com.mycollab.vaadin.web.ui.field.I18nFormViewField;
import com.mycollab.vaadin.web.ui.field.RichTextViewField;
import com.vaadin.ui.Field;
import org.vaadin.teemu.ratingstars.RatingStars;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class RiskPreviewForm extends AdvancedPreviewBeanForm<SimpleRisk> {

    @Override
    public void setBean(SimpleRisk bean) {
        setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.RISK,
                RiskDefaultFormLayoutFactory.getForm(), Risk.Field.riskname.name()));
        setBeanFormFieldFactory(new RiskReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class RiskReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = 1L;

        public RiskReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            SimpleRisk risk = attachForm.getBean();
            if (Risk.Field.description.equalTo(propertyId)) {
                return new RichTextViewField(risk.getDescription());
            } else if (Risk.Field.level.equalTo(propertyId)) {
                RatingStars tinyRs = new RatingStars();
                tinyRs.setValue(risk.getLevel());
                tinyRs.setReadOnly(true);
                return tinyRs;
            } else if (Risk.Field.status.equalTo(propertyId)) {
                return new I18nFormViewField(risk.getStatus(), OptionI18nEnum.StatusI18nEnum.class).withStyleName
                        (UIConstants.FIELD_NOTE);
            } else if (Risk.Field.datedue.equalTo(propertyId)) {
                return new DateViewField(risk.getDatedue());
            } else if (Risk.Field.startdate.equalTo(propertyId)) {
                return new DateViewField(risk.getStartdate());
            } else if (Risk.Field.enddate.equalTo(propertyId)) {
                return new DateViewField(risk.getEnddate());
            } else if (Risk.Field.raisedbyuser.equalTo(propertyId)) {
                return new ProjectUserFormLinkField(risk.getRaisedbyuser(), risk.getRaisedByUserAvatarId(),
                        risk.getRaisedByUserFullName());
            } else if (Risk.Field.assigntouser.equalTo(propertyId)) {
                return new ProjectUserFormLinkField(risk.getAssigntouser(), risk.getAssignToUserAvatarId(),
                        risk.getAssignedToUserFullName());
            } else if (Risk.Field.response.equalTo(propertyId)) {
                return new RichTextViewField(risk.getResponse());
            } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                return new ProjectItemViewField(ProjectTypeConstants.MILESTONE, risk.getMilestoneid() + "",
                        risk.getMilestoneName());
            } else if (Risk.Field.id.equalTo(propertyId)) {
                return new ProjectFormAttachmentDisplayField(risk.getProjectid(), ProjectTypeConstants.RISK, risk.getId());
            }

            return null;
        }
    }
}
