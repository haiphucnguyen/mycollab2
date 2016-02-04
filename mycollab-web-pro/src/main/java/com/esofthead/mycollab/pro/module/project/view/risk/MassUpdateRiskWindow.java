package com.esofthead.mycollab.pro.module.project.view.risk;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Risk;
import com.esofthead.mycollab.module.project.i18n.RiskI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.MassUpdateCommand;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.FormContainer;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.esofthead.mycollab.vaadin.web.ui.MassUpdateWindow;
import com.esofthead.mycollab.vaadin.web.ui.grid.GridFormLayoutHelper;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class MassUpdateRiskWindow extends MassUpdateWindow<Risk> {
    private static final long serialVersionUID = 1L;

    public MassUpdateRiskWindow(String title, MassUpdateCommand<Risk> massUpdatePresenter) {
        super(title, ProjectAssetsManager.getAsset(ProjectTypeConstants.RISK), new Risk(), massUpdatePresenter);
    }

    @Override
    protected IFormLayoutFactory buildFormLayoutFactory() {
        return new MassUpdateRiskFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<Risk> buildBeanFormFieldFactory() {
        return new RiskEditFormFieldFactory(updateForm);
    }

    private class MassUpdateRiskFormLayoutFactory implements IFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            FormContainer formLayout = new FormContainer();

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 6);
            formLayout.addSection("Risk Information", informationLayout.getLayout());

            formLayout.addComponent(buildButtonControls());

            return formLayout;
        }

        // Raised By, Assign To, Date Due, Status, Consequence, Probability
        @Override
        public void attachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("raisedbyuser")) {
                informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_RAISED_BY), 0, 0);
            } else if (propertyId.equals("assigntouser")) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 0);
            } else if (propertyId.equals("consequence")) {
                informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_CONSEQUENCE), 0, 1);
            } else if (propertyId.equals("datedue")) {
                informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_DATE_DUE), 1, 1);
            } else if (propertyId.equals("probalitity")) {
                informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_PROBABILITY), 0, 2);
            } else if (propertyId.equals("status")) {
                informationLayout.addComponent(field, AppContext.getMessage(RiskI18nEnum.FORM_STATUS), 1, 2);
            }
        }
    }
}
