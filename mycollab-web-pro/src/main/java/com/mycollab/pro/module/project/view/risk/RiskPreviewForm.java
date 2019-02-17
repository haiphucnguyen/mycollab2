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
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupViewFieldFactory;
import com.mycollab.vaadin.ui.GenericBeanForm;
import com.mycollab.vaadin.ui.field.I18nFormViewField;
import com.mycollab.vaadin.ui.field.RichTextViewField;
import com.mycollab.vaadin.ui.field.StyleViewField;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import org.apache.commons.lang3.StringUtils;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class RiskPreviewForm extends AdvancedPreviewBeanForm<SimpleRisk> {

    @Override
    public void setBean(SimpleRisk bean) {
        setFormLayoutFactory(new DefaultDynaFormLayout(ProjectTypeConstants.RISK,
                RiskDefaultFormLayoutFactory.getReadForm(), Risk.Field.name.name()));
        setBeanFormFieldFactory(new RiskReadFormFieldFactory(this));
        super.setBean(bean);
    }

    private static class RiskReadFormFieldFactory extends AbstractBeanFieldGroupViewFieldFactory<SimpleRisk> {
        private static final long serialVersionUID = 1L;

        RiskReadFormFieldFactory(GenericBeanForm<SimpleRisk> form) {
            super(form);
        }

        @Override
        protected HasValue<?> onCreateField(Object propertyId) {
            SimpleRisk risk = attachForm.getBean();
            if (Risk.Field.description.equalTo(propertyId)) {
                return new RichTextViewField();
            } else if (Risk.Field.priority.equalTo(propertyId)) {
                if (StringUtils.isNotBlank(risk.getPriority())) {
                    VaadinIcons fontPriority = ProjectAssetsManager.getPriority(risk.getPriority());
                    String priorityLbl = fontPriority.getHtml() + " " + UserUIContext.getMessage(OptionI18nEnum.Priority.class, risk.getPriority());
                    StyleViewField field = new StyleViewField(priorityLbl);
                    field.addStyleName("priority-" + risk.getPriority().toLowerCase());
                    return field;
                }
            } else if (Risk.Field.status.equalTo(propertyId)) {
                return new I18nFormViewField(StatusI18nEnum.class).withStyleName(WebThemes.FIELD_NOTE);
            } else if (Risk.Field.response.equalTo(propertyId)) {
                return new RichTextViewField();
            } else if (Risk.Field.milestoneid.equalTo(propertyId)) {
                return new ProjectItemViewField(ProjectTypeConstants.MILESTONE, risk.getMilestoneid(), risk.getMilestoneName());
            } else if ("section-attachments".equals(propertyId)) {
                return new ProjectFormAttachmentDisplayField(risk.getProjectid(), ProjectTypeConstants.RISK, risk.getId());
            } else if (Risk.Field.consequence.equalTo(propertyId)) {
                return new I18nFormViewField(RiskConsequence.class);
            } else if (Risk.Field.probability.equalTo(propertyId)) {
                return new I18nFormViewField(RiskProbability.class);
            }

            return null;
        }
    }
}
