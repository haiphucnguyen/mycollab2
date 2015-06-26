package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
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
