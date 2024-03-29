package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.security.BooleanPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd
 * @since 5.3.5
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_BILLING, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class BillingHistoryPresenter extends AbstractPresenter<BillingHistoryView> {
    private static final long serialVersionUID = 1L;

    public BillingHistoryPresenter() {
        super(BillingHistoryView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        IBillingContainer accountContainer = (IBillingContainer) container;
        accountContainer.setContent(view);

        view.lazyLoadView();
        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoBillingHistory();
    }
}
