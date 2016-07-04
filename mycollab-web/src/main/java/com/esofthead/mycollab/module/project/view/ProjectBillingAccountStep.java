package com.esofthead.mycollab.module.project.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.form.view.builder.DynaSectionBuilder;
import com.esofthead.mycollab.form.view.builder.TextDynaFieldBuilder;
import com.esofthead.mycollab.form.view.builder.type.DynaForm;
import com.esofthead.mycollab.form.view.builder.type.DynaSection;
import com.esofthead.mycollab.module.crm.view.account.AccountSelectionField;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.vaadin.web.ui.DefaultDynaFormLayout;
import com.esofthead.mycollab.vaadin.web.ui.DoubleField;
import com.vaadin.ui.Component;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
class ProjectBillingAccountStep  implements ProjectAddWindow.FormWizardStep {
    private Project project;
    private AdvancedEditBeanForm<Project> editForm;
    private EditFormFieldFactory editFormFieldFactory;

    ProjectBillingAccountStep(Project project) {
        editForm = new AdvancedEditBeanForm<>();
        editForm.setFormLayoutFactory(buildFormLayout());
        editFormFieldFactory = new EditFormFieldFactory(editForm);
        editForm.setBeanFormFieldFactory(editFormFieldFactory);
        editForm.setBean(project);
    }

    private IDynaFormLayout buildFormLayout() {
        DynaForm defaultForm = new DynaForm();
        DynaSection mainSection = new DynaSectionBuilder().layoutType(DynaSection.LayoutType.TWO_COLUMN).build();

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.accountid)
                .displayName(AppContext.getMessage(ProjectI18nEnum.FORM_ACCOUNT_NAME))
                .contextHelp(AppContext.getMessage(ProjectI18nEnum.FORM_ACCOUNT_NAME_HELP))
                .fieldIndex(0).colSpan(true).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.currencyid)
                .displayName(AppContext.getMessage(GenericI18Enum.FORM_CURRENCY))
                .contextHelp(AppContext.getMessage(ProjectI18nEnum.FORM_CURRENCY_HELP)).fieldIndex(1).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.targetbudget).displayName
                (AppContext.getMessage(ProjectI18nEnum.FORM_TARGET_BUDGET))
                .contextHelp(AppContext.getMessage(ProjectI18nEnum.FORM_TARGET_BUDGET_HELP)).fieldIndex(2).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.defaultbillingrate).displayName
                (AppContext.getMessage(ProjectI18nEnum.FORM_BILLING_RATE))
                .contextHelp(AppContext.getMessage(ProjectI18nEnum.FORM_BILLING_RATE_HELP)).fieldIndex(3).build());

        mainSection.fields(new TextDynaFieldBuilder().fieldName(Project.Field.defaultovertimebillingrate)
                .displayName(AppContext.getMessage(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE))
                .contextHelp(AppContext.getMessage(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE_HELP)).fieldIndex(4).build());

        defaultForm.sections(mainSection);
        return new DefaultDynaFormLayout(defaultForm);
    }

    @Override
    public boolean commit() {
        return editFormFieldFactory.commit();
    }

    @Override
    public String getCaption() {
        return AppContext.getMessage(ProjectI18nEnum.OPT_CLIENT_AND_BILLING);
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

        public EditFormFieldFactory(GenericBeanForm<Project> form) {
            super(form);
        }

        @Override
        protected Field<?> onCreateField(final Object propertyId) {
            if (Project.Field.currencyid.equalTo(propertyId)) {
                return new CurrencyComboBoxField();
            } else if (Project.Field.accountid.equalTo(propertyId)) {
                return new AccountSelectionField();
            } else if (Project.Field.targetbudget.equalTo(propertyId)
                    || Project.Field.defaultbillingrate.equalTo(propertyId)
                    || Project.Field.defaultovertimebillingrate.equalTo(propertyId)) {
                return new DoubleField();
            }
            return null;
        }
    }
}
