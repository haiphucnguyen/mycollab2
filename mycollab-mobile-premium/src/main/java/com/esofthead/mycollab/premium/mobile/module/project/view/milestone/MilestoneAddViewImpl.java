package com.esofthead.mycollab.premium.mobile.module.project.view.milestone;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.mobile.module.project.view.milestone.MilestoneAddView;
import com.esofthead.mycollab.mobile.ui.AbstractEditItemComp;
import com.esofthead.mycollab.mobile.ui.FormSectionBuilder;
import com.esofthead.mycollab.mobile.ui.grid.GridFormLayoutHelper;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.i18n.MilestoneI18nEnum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.esofthead.mycollab.vaadin.ui.IFormLayoutFactory;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Field;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 4.5.2
 */
@ViewComponent
public class MilestoneAddViewImpl extends AbstractEditItemComp<SimpleMilestone> implements MilestoneAddView {
    private static final long serialVersionUID = 5003180627691878220L;

    @Override
    protected String initFormTitle() {
        return (beanItem.getId() == null) ? AppContext.getMessage(MilestoneI18nEnum.FORM_NEW_TITLE) : beanItem.getName();
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new MilestoneFormLayoutFactory();
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleMilestone> initBeanFormFieldFactory() {
        return new MilestoneEditFormFieldFactory<>(this.editForm);
    }

    private static class MilestoneFormLayoutFactory implements IFormLayoutFactory {
        private static final long serialVersionUID = 7126369624045401332L;

        private GridFormLayoutHelper informationLayout;

        @Override
        public ComponentContainer getLayout() {
            final VerticalLayout layout = new VerticalLayout();
            layout.setMargin(false);
            layout.addComponent(FormSectionBuilder.build(AppContext.getMessage(MilestoneI18nEnum.M_FORM_READ_TITLE)));

            informationLayout = GridFormLayoutHelper.defaultFormLayoutHelper(1, 6);
            layout.addComponent(informationLayout.getLayout());
            layout.setComponentAlignment(informationLayout.getLayout(), Alignment.TOP_LEFT);
            return layout;
        }

        @Override
        public void attachField(Object propertyId, Field<?> field) {
            if (propertyId.equals("name")) {
                informationLayout.addComponent(field, AppContext.getMessage(MilestoneI18nEnum.FORM_NAME_FIELD), 0, 0);
            } else if (propertyId.equals("status")) {
                informationLayout.addComponent(field, AppContext.getMessage(MilestoneI18nEnum.FORM_STATUS_FIELD), 0, 1);
            } else if (propertyId.equals("owner")) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_ASSIGNEE), 0, 2);
            } else if (propertyId.equals("startdate")) {
                informationLayout.addComponent(field, AppContext.getMessage(MilestoneI18nEnum.FORM_START_DATE_FIELD), 0, 3);
            } else if (propertyId.equals("enddate")) {
                informationLayout.addComponent(field, AppContext.getMessage(MilestoneI18nEnum.FORM_END_DATE_FIELD), 0, 4);
            } else if (propertyId.equals("description")) {
                informationLayout.addComponent(field, AppContext.getMessage(GenericI18Enum.FORM_DESCRIPTION), 0, 5);
            }
        }
    }
}
