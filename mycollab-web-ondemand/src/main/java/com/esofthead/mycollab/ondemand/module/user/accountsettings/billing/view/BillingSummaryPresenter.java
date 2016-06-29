package com.esofthead.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.esofthead.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_BILLING, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class BillingSummaryPresenter extends AbstractPresenter<BillingSummaryView> {
    private static final long serialVersionUID = 1L;

    public BillingSummaryPresenter() {
        super(BillingSummaryView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        IBillingContainer accountContainer = (IBillingContainer) container;
        accountContainer.removeAllComponents();
        accountContainer.addComponent(view);

        view.loadCurrentPlan();
        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoBillingPage();
    }
}
