package com.mycollab.premium.mobile.module.crm.view.lead;

import com.mycollab.mobile.form.view.DynaFormLayout;
import com.mycollab.mobile.module.crm.view.lead.LeadAddView;
import com.mycollab.mobile.module.crm.view.lead.LeadDefaultDynaFormLayoutFactory;
import com.mycollab.mobile.ui.AbstractEditItemComp;
import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleLead;
import com.mycollab.module.crm.i18n.LeadI18nEnum;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AbstractBeanFieldGroupEditFieldFactory;
import com.mycollab.vaadin.ui.IFormLayoutFactory;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewComponent
public class LeadAddViewImpl extends AbstractEditItemComp<SimpleLead> implements LeadAddView {
    private static final long serialVersionUID = -1152988648403103949L;

    @Override
    protected String initFormTitle() {
        return beanItem.getLeadName() != null ? beanItem.getLeadName()
                : UserUIContext.getMessage(LeadI18nEnum.NEW);
    }

    @Override
    protected IFormLayoutFactory initFormLayoutFactory() {
        return new DynaFormLayout(CrmTypeConstants.LEAD, LeadDefaultDynaFormLayoutFactory.getForm());
    }

    @Override
    protected AbstractBeanFieldGroupEditFieldFactory<SimpleLead> initBeanFormFieldFactory() {
        return new LeadEditFormFieldFactory<>(this.editForm);
    }

}
