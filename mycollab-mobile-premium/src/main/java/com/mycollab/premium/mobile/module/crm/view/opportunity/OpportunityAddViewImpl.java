package com.mycollab.premium.mobile.module.crm.view.opportunity;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.opportunity.OpportunityAddView;
import com.mycollab.mobile.module.crm.view.opportunity.OpportunityDefaultDynaFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleOpportunity;
import com.mycollab.module.crm.i18n.OpportunityI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd
 * @since 4.0.0
 */
@ViewComponent
public class OpportunityAddViewImpl extends AbstractEditItemComp<SimpleOpportunity> implements OpportunityAddView {
    private static final long serialVersionUID = -7666059081043542816L;

    @Override
    protected String initFormTitle() {
        return beanItem.getOpportunityname() != null ? beanItem.getOpportunityname() : UserUIContext.getMessage(OpportunityI18nEnum.NEW);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.INSTANCE.getOPPORTUNITY(), OpportunityDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleOpportunity> initBeanFormFieldFactory() {
        return new OpportunityEditFormFieldFactory<>(this.editForm);
    }

}
