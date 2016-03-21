package com.esofthead.mycollab.pro.module.project.view.client;

import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.esofthead.mycollab.module.crm.view.account.AccountReadFormFieldFactory;
import com.esofthead.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.esofthead.mycollab.vaadin.web.ui.DynaFormLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ClientPreviewForm extends AdvancedPreviewBeanForm<SimpleAccount> {
    @Override
    public void setBean(SimpleAccount bean) {
        this.setFormLayoutFactory(new DynaFormLayout(CrmTypeConstants.ACCOUNT, AccountDefaultDynaFormLayoutFactory.getForm()));
        this.setBeanFormFieldFactory(new AccountReadFormFieldFactory(this));
        super.setBean(bean);
    }
}
