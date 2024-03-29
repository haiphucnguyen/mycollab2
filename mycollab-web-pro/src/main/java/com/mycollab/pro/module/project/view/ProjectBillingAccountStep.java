package com.mycollab.pro.module.project.view;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.form.view.LayoutType;
import com.mycollab.form.view.builder.DynaSectionBuilder;
import com.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.mycollab.form.view.builder.type.DynaForm;
import com.mycollab.form.view.builder.type.DynaSection;
import com.mycollab.module.project.domain.Project;
import com.mycollab.module.project.i18n.ProjectI18nEnum;
import com.mycollab.module.project.view.AbstractProjectAddWindow;
import com.mycollab.pro.module.project.view.client.ClientSelectionField;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.*;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.data.HasValue;
import com.vaadin.ui.Component;
import org.vaadin.viritin.fields.DoubleField;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ProjectBillingAccountStep implements AbstractProjectAddWindow.FormWizardStep {
    private Project project;
    private AdvancedEditBeanForm<Project> editForm;
    private EditFormFieldFactory editFormFieldFactory;

    ProjectBillingAccountStep(Project project) {
        this.project = project;
        editForm = new AdvancedEditBeanForm<>();
        editForm.setFormLayoutFactory(buildFormLayout());
        editFormFieldFactory = new EditFormFieldFactory(editForm);
        editForm.setBeanFormFieldFactory(editFormFieldFactory);
        editForm.setBean(project);
    }

    private IDynaFormLayout buildFormLayout() {
        DynaForm defaultForm = new DynaForm();
        DynaSection mainSection = new DynaSectionBuilder().layoutType(LayoutType.TWO_COLUMN).build();

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.clientid)
                .displayName(ProjectI18nEnum.FORM_ACCOUNT_NAME)
                .contextHelp(ProjectI18nEnum.FORM_ACCOUNT_NAME_HELP)
                .fieldIndex(0).colSpan(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.currencyid)
                .displayName(GenericI18Enum.FORM_CURRENCY)
                .contextHelp(ProjectI18nEnum.FORM_CURRENCY_HELP).fieldIndex(1).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.targetbudget).displayName(ProjectI18nEnum.FORM_TARGET_BUDGET)
                .contextHelp(ProjectI18nEnum.FORM_TARGET_BUDGET_HELP).fieldIndex(2).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.defaultbillingrate).displayName(ProjectI18nEnum.FORM_BILLING_RATE)
                .contextHelp(ProjectI18nEnum.FORM_BILLING_RATE_HELP).fieldIndex(3).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.defaultovertimebillingrate)
                .displayName(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE)
                .contextHelp(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE_HELP).fieldIndex(4).build());

        defaultForm.sections(mainSection);
        return new DefaultDynaFormLayout(defaultForm);
    }

    @Override
    public boolean commit() {
        return editFormFieldFactory.commit();
    }

    @Override
    public String getCaption() {
        return UserUIContext.getMessage(ProjectI18nEnum.OPT_CLIENT_AND_BILLING);
    }

    @Override
    public Component getContent() {
        return editForm;
    }

    @Override
    public boolean onAdvance() {
        return true;
    }

    @Override
    public boolean onBack() {
        return true;
    }

    private class EditFormFieldFactory extends AbstractBeanFieldGroupEditFieldFactory<Project> {
        private static final long serialVersionUID = 1L;

        EditFormFieldFactory(GenericBeanForm<Project> form) {
            super(form);
        }

        @Override
        protected HasValue<?> onCreateField(final Object propertyId) {
            if (Project.Field.currencyid.equalTo(propertyId)) {
                return new CurrencyComboBoxField();
            } else if (Project.Field.clientid.equalTo(propertyId)) {
                return new ClientSelectionField();
            } else if (Project.Field.targetbudget.equalTo(propertyId)
                    || Project.Field.defaultbillingrate.equalTo(propertyId)
                    || Project.Field.defaultovertimebillingrate.equalTo(propertyId)) {
                return new DoubleField().withWidth(WebThemes.FORM_CONTROL_WIDTH);
            }
            return null;
        }
    }
}
