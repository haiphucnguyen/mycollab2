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
package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent.ResetTheme;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent.SaveTheme;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.service.AccountThemeService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.esofthead.mycollab.vaadin.ui.ConfirmDialogExt;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.Page;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.UI;
import org.vaadin.dialogs.ConfirmDialog;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class ThemeCustomizePresenter extends AbstractPresenter<ThemeCustomizeView> {
    private static final long serialVersionUID = 5330315328389778202L;

    private AccountThemeService themeService;

    public ThemeCustomizePresenter() {
        super(ThemeCustomizeView.class);
        themeService = ApplicationContextUtil.getSpringBean(AccountThemeService.class);
    }

    @Override
    protected void postInitView() {
        EventBusFactory.getInstance().register(new ApplicationEventListener<AccountCustomizeEvent.SaveTheme>() {
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
        EventBusFactory.getInstance().register(new ApplicationEventListener<AccountCustomizeEvent.ResetTheme>() {
            private static final long serialVersionUID = 1594676526731151824L;

            @Subscribe
            @Override
            public void handle(ResetTheme event) {
                ConfirmDialogExt.show(UI.getCurrent(), "Reset to the default theme",
                        "This action will reset all your customizations to default. Are you really want to do this?",
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
        SettingContainer customizeContainer = (SettingContainer) container;
        customizeContainer.gotoSubView("Theme");

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
