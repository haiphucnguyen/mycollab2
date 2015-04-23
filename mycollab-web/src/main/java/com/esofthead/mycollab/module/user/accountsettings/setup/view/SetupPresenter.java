package com.esofthead.mycollab.module.user.accountsettings.setup.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.0.5
 */
public class SetupPresenter extends AbstractPresenter<SetupView> {
    public SetupPresenter() {
        super(SetupView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        if (AppContext.isAdmin()) {
            AccountModule accountContainer = (AccountModule) container;
            accountContainer.gotoSubView(SettingUIConstants.SETUP);
            view.displaySetup();

            AccountSettingBreadcrumb breadcrumb = ViewManager
                    .getCacheComponent(AccountSettingBreadcrumb.class);
            breadcrumb.gotoSetup();
        } else {
            NotificationUtil.showMessagePermissionAlert();
        }

    }
}
