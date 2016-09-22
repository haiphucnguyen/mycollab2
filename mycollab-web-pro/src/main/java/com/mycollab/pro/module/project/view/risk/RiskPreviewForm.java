package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.OptionI18nEnum.StatusI18nEnum;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.Risk;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.OptionI18nEnum;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskConsequence;
import com.mycollab.module.project.i18n.OptionI18nEnum.RiskProbability;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.module.project.ui.form.ProjectFormAttachmentDisplayField;
import com.mycollab.module.project.ui.form.ProjectItemViewField;
import com.mycollab.module.project.view.settings.component.ProjectUserFormLinkField;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.field.DateViewField;
import com.mycollab.vaadin.ui.field.DefaultViewField;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Field;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class RiskPreviewForm extends AdvancedPreviewBeanForm<SimpleRisk> {

    @Override
    public void setBean(SimpleRisk bean) {
        setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.RISK,
                RiskDefaultFormLayoutFactory.getForm(), Risk.Field.name.name()));
        setBeanFormFieldFactory(new RiskReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class RiskReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = 1L;

        RiskReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(Object propertyId) {
            SimpleRisk risk = attachForm.getBean();
            if (Risk.Field.description.equalTo(propertyId)) {
                return new RichTextViewField(risk.getDescription());
            } else if (Risk.Field.priority.equalTo(propertyId)) {
                if (StringUtils.isNotBlank(risk.getPriority())) {
                    FontAwesome fontPriority = ProjectAssetsManager.getPriority(risk.getPriority());
                    String priorityLbl = fontPriority.getHtml() + " " + UserUIContext.getMessage(OptionI18nEnum.Priority.class, risk.getPriority());
                    DefaultViewField field = new DefaultViewField(priorityLbl, ContentMode.HTML);
                    field.addStyleName("task-" + risk.getPriority().toLowerCase());
                    return field;
                }
            } else if (Risk.Field.status.equalTo(propertyId)) {
                return new I18nFormViewField(risk.getStatus(), StatusI18nEnum.class).withStyleName(WebUIConstants.FIELD_NOTE);
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
                return new ProjectItemViewField(ProjectTypeConstants.MILESTONE, risk.getMilestoneid() + "", risk.getMilestoneName());
            } else if (Risk.Field.id.equalTo(propertyId)) {
                return new ProjectFormAttachmentDisplayField(risk.getProjectid(), ProjectTypeConstants.RISK, risk.getId());
            } else if (Risk.Field.consequence.equalTo(propertyId)) {
                return new I18nFormViewField(risk.getConsequence(), RiskConsequence.class);
            } else if (Risk.Field.probalitity.equalTo(propertyId)) {
                return new I18nFormViewField(risk.getProbalitity(), RiskProbability.class);
            }

            return null;
        }
    }
}
