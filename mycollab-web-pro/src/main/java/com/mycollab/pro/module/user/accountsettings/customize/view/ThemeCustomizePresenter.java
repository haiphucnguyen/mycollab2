package com.mycollab.pro.module.user.accountsettings.customize.view;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.module.user.accountsettings.customize.view.IThemeCustomizePresenter;
import com.mycollab.module.user.accountsettings.customize.view.IThemeCustomizeView;
import com.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.mycollab.module.user.accountsettings.view.AccountModule;
import com.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.mycollab.module.user.accountsettings.view.event.SettingEvent;
import com.mycollab.module.user.accountsettings.view.event.SettingEvent.ResetTheme;
import com.mycollab.module.user.accountsettings.view.event.SettingEvent.SaveTheme;
import com.mycollab.module.user.domain.AccountTheme;
import com.mycollab.module.user.service.AccountThemeService;
import com.mycollab.module.user.ui.SettingUIConstants;
import com.mycollab.security.BooleanPermissionFlag;
import com.mycollab.security.RolePermissionCollections;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.*;
import com.mycollab.vaadin.mvp.ScreenData;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.mvp.ViewPermission;
import com.mycollab.vaadin.web.ui.AbstractPresenter;
import com.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.vaadin.ui.HasComponents;
import com.vaadin.ui.UI;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
@ViewPermission(permissionId = RolePermissionCollections.ACCOUNT_THEME, impliedPermissionVal = BooleanPermissionFlag.TRUE)
public class ThemeCustomizePresenter extends AbstractPresenter<IThemeCustomizeView> implements IThemeCustomizePresenter {
    private static final long serialVersionUID = 5330315328389778202L;

    private AccountThemeService themeService;

    public ThemeCustomizePresenter() {
        super(IThemeCustomizeView.class);
        themeService = AppContextUtil.getSpringBean(AccountThemeService.class);
    }

    @Override
    protected void postInitView() {
        EventBusFactory.getInstance().register(new ApplicationEventListener<SettingEvent.SaveTheme>() {

            @Subscribe
            @Override
            public void handle(SaveTheme event) {
                if (event.getData() instanceof AccountTheme) {
                    saveTheme((AccountTheme) event.getData());
                    Utils.reloadPage();
                }
            }
        });
        EventBusFactory.getInstance().register(new ApplicationEventListener<SettingEvent.ResetTheme>() {

            @Subscribe
            @Override
            public void handle(ResetTheme event) {
                ConfirmDialogExt.show(UI.getCurrent(), UserUIContext.getMessage(AdminI18nEnum.ACTION_RESET_DEFAULT_THEME),
                        UserUIContext.getMessage(AdminI18nEnum.OPT_CONFIRM_RESET_DEFAULT_THEME),
                        UserUIContext.getMessage(GenericI18Enum.ACTION_YES),
                        UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                        confirmDialog -> {
                            if (confirmDialog.isConfirmed()) {
                                themeService.removeTheme(AppUI.getAccountId());
                                Utils.reloadPage();
                            }
                        });
            }
        });
    }

    @Override
    protected void onGo(HasComponents container, ScreenData<?> data) {
        AccountModule accountModule = (AccountModule) container;
        accountModule.gotoSubView(SettingUIConstants.THEME_CUSTOMIZE, view);

        AccountTheme accountTheme;
        if (data == null || data.getParams() == null) {
            accountTheme = themeService.findTheme(AppUI.getAccountId());
        } else {
            accountTheme = (AccountTheme) data.getParams();
        }

        if (accountTheme == null) {
            accountTheme = themeService.findDefaultTheme(AppUI.getAccountId());
        }

        if (accountTheme == null) {
            accountTheme = new AccountTheme();
        }

        view.customizeTheme(accountTheme);
        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoMakeTheme();
    }

    private void saveTheme(AccountTheme accountTheme) {
        if (accountTheme.getSaccountid() == null) {
            accountTheme.setSaccountid(AppUI.getAccountId());
            accountTheme.setId(null);
            accountTheme.setIsdefault(Boolean.FALSE);
            themeService.saveWithSession(accountTheme, UserUIContext.getUsername());
        } else {
            accountTheme.setIsdefault(Boolean.FALSE);
            themeService.updateWithSession(accountTheme, UserUIContext.getUsername());
        }
    }
}
