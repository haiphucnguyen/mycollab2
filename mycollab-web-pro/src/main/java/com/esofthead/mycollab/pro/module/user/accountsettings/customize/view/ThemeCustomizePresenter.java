/**
 * This file is part of mycollab-web-premium.
 *
 * mycollab-web-premium is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web-premium is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web-premium.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.pro.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.IThemeCustomizePresenter;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.IThemeCustomizeView;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.AccountSettingContainer;
import com.esofthead.mycollab.module.user.accountsettings.localization.AdminI18nEnum;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent.ResetTheme;
import com.esofthead.mycollab.module.user.accountsettings.view.events.SettingEvent.SaveTheme;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.security.BooleanPermissionFlag;
import com.esofthead.mycollab.security.RolePermissionCollections;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.mvp.ViewPermission;
import com.esofthead.mycollab.vaadin.web.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.web.ui.ConfirmDialogExt;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Page;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;

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
            private static final long serialVersionUID = -1060182248184670399L;

            @Subscribe
            @Override
            public void handle(SaveTheme event) {
                if (event.getData() instanceof AccountTheme) {
                    saveTheme((AccountTheme) event.getData());
                    Page.getCurrent().getJavaScript().execute("window.location.reload();");
                }
            }
        });
        EventBusFactory.getInstance().register(new ApplicationEventListener<SettingEvent.ResetTheme>() {
            private static final long serialVersionUID = 1594676526731151824L;

            @Subscribe
            @Override
            public void handle(ResetTheme event) {
                ConfirmDialogExt.show(UI.getCurrent(), AppContext.getMessage(AdminI18nEnum.ACTION_RESET_DEFAULT_THEME),
                        AppContext.getMessage(AdminI18nEnum.OPT_CONFIRM_RESET_DEFAULT_THEME),
                        AppContext.getMessage(GenericI18Enum.BUTTON_YES),
                        AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
                        new ConfirmDialog.Listener() {
                            private static final long serialVersionUID = 2086515060473457749L;

                            @Override
                            public void onClose(ConfirmDialog dialog) {
                                if (dialog.isConfirmed()) {
                                    themeService.removeTheme(AppContext.getAccountId());
                                    Page.getCurrent().getJavaScript().execute("window.location.reload();");
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AccountSettingContainer customizeContainer = (AccountSettingContainer) container;
        customizeContainer.gotoSubView(AppContext.getMessage(AdminI18nEnum.OPT_THEME));

        AccountTheme accountTheme;
        if (data == null || data.getParams() == null) {
            accountTheme = themeService.findTheme(AppContext.getAccountId());
        } else {
            accountTheme = (AccountTheme) data.getParams();
        }

        if (accountTheme == null) {
            accountTheme = themeService.findDefaultTheme(AppContext.getAccountId());
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
            accountTheme.setSaccountid(AppContext.getAccountId());
            accountTheme.setId(null);
            accountTheme.setIsdefault(Boolean.FALSE);
            themeService.saveWithSession(accountTheme, AppContext.getUsername());
        } else {
            accountTheme.setIsdefault(Boolean.FALSE);
            themeService.updateWithSession(accountTheme, AppContext.getUsername());
        }
    }
}
