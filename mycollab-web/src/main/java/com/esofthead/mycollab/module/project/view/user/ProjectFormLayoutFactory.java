/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.esofthead.mycollab.module.project.view.user;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Project;
import com.esofthead.mycollab.module.project.i18n.ProjectI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.esofthead.mycollab.vaadin.ui.AddViewLayout;
import com.esofthead.mycollab.vaadin.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public abstract class ProjectFormLayoutFactory extends AbstractFormLayoutFactory {
    private static final long serialVersionUID = 1L;

    private final String title;

    private ProjectInformationLayout projectInformationLayout;

    public ProjectFormLayoutFactory(final String title) {
        this.title = title;
    }

    @Override
    public ComponentContainer getLayout() {
        final AddViewLayout projectAddLayout = new AddViewLayout(this.title,
                ProjectAssetsManager.getAsset(ProjectTypeConstants.PROJECT));

        this.projectInformationLayout = new ProjectInformationLayout();

        final Layout topPanel = this.createTopPanel();
        if (topPanel != null) {
            projectAddLayout.addHeaderRight(topPanel);
        }

        projectAddLayout.addBody(this.projectInformationLayout.getLayout());

        final Layout bottomPanel = this.createBottomPanel();
        if (bottomPanel != null) {
            projectAddLayout.addBottomControls(bottomPanel);
        }

        return projectAddLayout;
    }

    @Override
    protected void onAttachField(final Object propertyId, final Field<?> field) {
        this.projectInformationLayout.attachField(propertyId, field);
    }

    protected abstract Layout createTopPanel();

    protected abstract Layout createBottomPanel();

    public static class ProjectInformationLayout extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = 1L;
        private GridFormLayoutHelper informationLayout;
        private GridFormLayoutHelper financialLayout;
        private GridFormLayoutHelper descriptionLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();

            final Label organizationHeader = new Label(AppContext.getMessage(ProjectI18nEnum.SECTION_PROJECT_INFO));
            organizationHeader.setStyleName("h2");
            layout.addComponent(organizationHeader);

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 2);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.BOTTOM_CENTER);

            final Label financialHeader = new Label(AppContext.getMessage(ProjectI18nEnum.SECTION_FINANCE_SCHEDULE));
            financialHeader.setStyleName("h2");
            layout.addComponent(financialHeader);

            financialLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 5);
            layout.addComponent(financialLayout.getLayout());
            layout.setComponentAlignment(financialLayout.getLayout(), Alignment.BOTTOM_CENTER);

            final Label descHeader = new Label(AppContext.getMessage(ProjectI18nEnum.SECTION_DESCRIPTION));
            descHeader.setStyleName("h2");
            layout.addComponent(descHeader);

            descriptionLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 1);
            layout.addComponent(descriptionLayout.getLayout());
            layout.setComponentAlignment(descriptionLayout.getLayout(), Alignment.BOTTOM_CENTER);
            return layout;
        }

        @Override
        protected void onAttachField(final Object propertyId, final Field<?> field) {
            if (propertyId.equals("name")) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_NAME), 0, 0);
            } else if (propertyId.equals("homepage")) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_HOME_PAGE), 1, 0);
            } else if (propertyId.equals("shortname")) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_SHORT_NAME), 0, 1);
            } else if (propertyId.equals("projectstatus")) {
                informationLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_STATUS), 1, 1);
            } else if (propertyId.equals("planstartdate")) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_PLAN_START_DATE), 0, 0);
            } else if (Project.Field.account.equalTo(propertyId)) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_ACCOUNT_NAME), 1, 0);
            } else if (propertyId.equals("planenddate")) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_PLAN_END_DATE), 0, 1);
            } else if (propertyId.equals("defaultbillingrate")) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_BILLING_RATE), 1, 1);
            } else if (propertyId.equals("actualstartdate")) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_ACTUAL_START_DATE), 0, 2);
            } else if (Project.Field.defaultovertimebillingrate.equalTo(propertyId)) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_OVERTIME_BILLING_RATE), 1, 2);
            } else if (propertyId.equals("actualenddate")) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_ACTUAL_END_DATE), 0, 3);
            } else if (Project.Field.targetbudget.equalTo(propertyId)) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_TARGET_BUDGET), 1, 3);
            } else if (Project.Field.currencyid.equalTo(propertyId)) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_CURRENCY), 0, 4);
            } else if (Project.Field.actualbudget.equalTo(propertyId)) {
                financialLayout.addComponent(field, AppContext.getMessage(ProjectI18nEnum.FORM_ACTUAL_BUDGET), 1, 4);
            } else if (propertyId.equals("description")) {
                descriptionLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 0, 2, "100%");
            }
        }
    }
}
