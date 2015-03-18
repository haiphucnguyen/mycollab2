package com.esofthead.mycollab.premium.module.project.view.problem;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.module.project.ProjectTypeConstants;
import com.esofthead.mycollab.module.project.domain.Problem;
import com.esofthead.mycollab.module.project.i18n.ProblemI18nEnum;
import com.esofthead.mycollab.module.project.ui.ProjectAssetsManager;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.*;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 2.0
 */
public class MassUpdateProblemWindow extends MassUpdateWindow<Problem> {
    private static final long serialVersionUID = 1L;

    public MassUpdateProblemWindow(final String title,
                                   final ProblemListPresenter presenter) {
        super(title, ProjectAssetsManager.getAsset(ProjectTypeConstants.PROBLEM), new Problem(),
                presenter);
    }

    @Override
    protected IFormLayoutFactory buildFormLayoutFactory() {
        return new MassUpdateProblemFormLayoutFactory();
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<Problem> buildBeanFormFieldFactory() {
        return new ProblemEditFormFieldFactory(updateForm, false);
    }

    private class MassUpdateProblemFormLayoutFactory implements
            IFormLayoutFactory {
        private static final long serialVersionUID = 1L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout formLayout = new VerticalLayout();

            final Label organizationHeader = new Label("Problem Information");
            organizationHeader.setStyleName(UIConstants.H2_STYLE2);
            formLayout.addComponent(organizationHeader);

            this.informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(2, 6);
            formLayout.addComponent(this.informationLayout.getLayout());
            formLayout.addComponent(buildButtonControls());
            return formLayout;
        }

        // Raised By, Assign To, Date Due, Status, Probability
        @Override
        public void attachField(final Object propertyId, final Field<?> field) {
            if (propertyId.equals("raisedbyuser")) {
                this.informationLayout.addComponent(field,
                        AppContext.getMessage(ProblemI18nEnum.FORM_RAISED_BY),
                        0, 0);
            } else if (propertyId.equals("assigntouser")) {
                this.informationLayout.addComponent(field, AppContext
                        .getMessage(GenericI18Enum.FORM_ASSIGNEE), 1, 0);
            } else if (propertyId.equals("datedue")) {
                this.informationLayout.addComponent(field,
                        AppContext.getMessage(ProblemI18nEnum.FORM_DATE_DUE),
                        0, 1);
            } else if (propertyId.equals("priority")) {
                this.informationLayout.addComponent(field,
                        AppContext.getMessage(ProblemI18nEnum.FORM_PRIORITY),
                        1, 1);
            } else if (propertyId.equals("status")) {
                this.informationLayout.addComponent(field,
                        AppContext.getMessage(ProblemI18nEnum.FORM_STATUS), 0,
                        2);
            }
        }
    }
}
