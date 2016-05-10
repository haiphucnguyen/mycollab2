package com.esofthead.mycollab.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_THEME, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class GeneralSettingPresenter extends AbstractPresenter<GeneralSettingView> {
    public GeneralSettingPresenter() {
        super(GeneralSettingView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        SettingContainer customizeContainer = (SettingContainer) container;
        customizeContainer.gotoSubView("General Settings");
        view.displayView();
        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoGeneralSetting();
    }
}
