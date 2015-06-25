package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.vaadin.AppContext;
import com.vaadin.ui.Window;
import org.vaadin.maddon.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
class AccountInfoChangeWindow extends Window {

    private SimpleBillingAccount billingAccount = AppContext.getBillingAccount();

    private MVerticalLayout content;

    AccountInfoChangeWindow() {
        super("Change account info");
        this.setModal(true);
        this.setResizable(false);
        this.setWidth("600px");

    }
}
