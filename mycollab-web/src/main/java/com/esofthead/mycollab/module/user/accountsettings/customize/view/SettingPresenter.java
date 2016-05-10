package com.esofthead.mycollab.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.SettingExtScreenData;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.vaadin.mvp.IPresenter;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_THEME, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class SettingPresenter extends AbstractPresenter<SettingContainer> {
    private static final long serialVersionUID = 1L;

    public SettingPresenter() {
        super(SettingContainer.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;
        accountContainer.gotoSubView(SettingUIConstants.GENERAL_SETTING);

        IPresenter<?> presenter;
        if (data instanceof SettingExtScreenData.GeneralSetting || data == null) {
            presenter = PresenterResolver.getPresenter(GeneralSettingPresenter.class);
        } else if (data instanceof SettingExtScreenData.ThemeCustomize) {
            presenter = PresenterResolver.getPresenter(IThemeCustomizePresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view, data);
    }

}
