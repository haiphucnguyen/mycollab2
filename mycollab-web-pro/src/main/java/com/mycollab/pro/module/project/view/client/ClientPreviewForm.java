package com.mycollab.pro.module.project.view.client;

import com.mycollab.module.crm.CrmTypeConstants;
import com.mycollab.module.crm.domain.SimpleAccount;
import com.mycollab.module.crm.view.account.AccountDefaultDynaFormLayoutFactory;
import com.mycollab.module.crm.view.account.AccountReadFormFieldFactory;
import com.mycollab.vaadin.web.ui.AdvancedPreviewBeanForm;
import com.mycollab.vaadin.web.ui.DefaultDynaFormLayout;

/**
 * @author MyCollab Ltd
 * @since 5.2.10
 */
public class ClientPreviewForm extends AdvancedPreviewBeanForm<SimpleAccount> {
    @Override
    public void setBean(SimpleAccount bean) {
        this.setFormLayoutFactory(new DefaultDynaFormLayout(CrmTypeConstants.INSTANCE.getACCOUNT(), AccountDefaultDynaFormLayoutFactory.getForm()));
        this.setBeanFormFieldFactory(new AccountReadFormFieldFactory(this));
        super.setBean(bean);
    }
}
