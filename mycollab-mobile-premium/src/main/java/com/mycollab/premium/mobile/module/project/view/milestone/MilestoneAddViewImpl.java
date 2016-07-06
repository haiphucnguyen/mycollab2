package com.mycollab.premium.mobile.module.project.view.milestone;

import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.mobile.module.project.view.milestone.MilestoneAddView;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.mobile.ui.FormSectionBuilder;
import com.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.mycollab.module.project.domain.SimpleMilestone;
import com.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.mycollab.vaadin.AppContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.AbstractFormLayoutFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.*;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class MilestoneAddViewImpl extends AbstractEditItemComp<SimpleMilestone> implements MilestoneAddView {
    private static final long serialVersionUID = 5003180627691878220L;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? AppContext.getMessage(MilestoneI18nEnum.NEW) : beanItem.getName();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new MilestoneFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleMilestone> initBeanFormFieldFactory() {
        return new MilestoneEditFormFieldFactory<>(this.editForm);
    }

    private static class MilestoneFormLayoutFactory extends AbstractFormLayoutFactory {
        private static final long serialVersionUID = 7126369624045401332L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(FormSectionBuilder.build(AppContext.getMessage(MilestoneI18nEnum.SINGLE)));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 6);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.TOP_LEFT);
            return layout;
        }

        @Override
        protected Component onAttachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("name")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_NAME), 0, 0);
            } else if (propertyId.equals("status")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_STATUS), 0, 1);
            } else if (propertyId.equals("owner")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 2);
            } else if (propertyId.equals("startdate")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_START_DATE), 0, 3);
            } else if (propertyId.equals("enddate")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_END_DATE), 0, 4);
            } else if (propertyId.equals("description")) {
                return informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 5);
            }
            return null;
        }
    }
}
