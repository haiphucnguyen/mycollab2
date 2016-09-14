package com.mycollab.pro.module.project.view.risk;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.project.ProjectTypeConstants;
import com.mycollab.module.project.domain.SimpleRisk;
import com.mycollab.module.project.i18n.RiskI18nEnum;
import com.mycollab.module.project.ui.ProjectAssetsManager;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.MassUpdateCommand;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.FormContainer;
import com.mycollab.vaadin.web.ui.MassUpdateWindow;
import com.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class MassUpdateRiskWindow extends MassUpdateWindow<SimpleRisk> {
    private static final long serialVersionUID = 1L;

    public MassUpdateRiskWindow(String title, MassUpdateCommand<SimpleRisk> massUpdatePresenter) {
        super(title, ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK), new SimpleRisk(), massUpdatePresenter);
    }

    @Override
    protected AbstractFormLayoutFactory buildFormLayoutFactory() {
        return new MassUpdateRiskFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleRisk> buildBeanFormFieldFactory() {
        return new RiskEditFormFieldFactory(updateForm, false);
    }

    private class MassUpdateRiskFormLayoutFactory extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            FormContainer formLayout = new FormContainer();
            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 6);
            formLayout.addSection(AppContext.getMessage(RiskI18nEnum.SECTION_RISK_INFORMATION), informationLayout.getLayout());
            formLayout.addComponent(buildButtonControls());
            return formLayout;
        }

        // Raised By, Assign To, Date Due, Status, Consequence, Probability
        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("raisedbyuser")) {
                return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY), 0, 0);
            } else if (propertyId.equals("assigntouser")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 0);
            } else if (propertyId.equals("consequence")) {
                return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE), 0, 1);
            } else if (propertyId.equals("datedue")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DUE_DATE), 1, 1);
            } else if (propertyId.equals("probalitity")) {
                return informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY), 0, 2);
            } else if (propertyId.equals("status")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_STATUS), 1, 2);
            }
            return null;
        }
    }
}
