package com.mycollab.ondemand.module.user.accountsettings.billing.view;

import com.mycollab.module.user.accountsettings.billing.view.IBillingContainer;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.security.BooleanPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.HasComponents;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewPermission(permissionId = RolePermissionCollections.INSTANCE.getACCOUNT_BILLING(), impliedPermissionVal = BooleanPermissionFlag.Companion.getTRUE())
public class CancelAccountPresenter extends AbstractPresenter<CancelAccountView> {
    private static final long serialVersionUID = 1L;

    public CancelAccountPresenter() {
        super(CancelAccountView.class);
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        IBillingContainer accountContainer = (IBillingContainer) container;
        accountContainer.setContent(view);

        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoCancelAccountPage();
    }
}
