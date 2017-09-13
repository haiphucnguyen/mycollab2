package com.mycollab.premium.module.user.accountsettings.billing.view;

import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.module.user.accountsettings.billing.view.IBillingPresenter;
import com.mycollab.module.user.accountsettings.view.AccountModule;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

import javax.annotation.Priority;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Priority(1)
public class BillingPresenter extends AbstractPresenter<IBillingContainer> implements IBillingPresenter {
    private static final long serialVersionUID = 1L;

    public BillingPresenter() {
        super(IBillingContainer.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;
        accountContainer.gotoSubView(SettingUIConstants.INSTANCE.getBILLING());
        ((BillingContainer) view).display();

        AccountSettingBreadcrumb breadcrumb = ViewManager.INSTANCE.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoBillingPage();
    }
}