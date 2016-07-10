package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.mvp.view.AbstractLazyPageView;
import com.mycollab.vaadin.ui.ELabel;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@ViewComponent
public class BillingHistoryViewImpl extends AbstractLazyPageView implements BillingHistoryView {
    @Override
    protected void displayView() {
        ELabel billingHeader = ELabel.h2("Billing Overview");
    }
}
