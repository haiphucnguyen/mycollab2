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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizePresenter;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ISettingContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountSettingBreadcrumb;
import com.esofthead.mycollab.module.user.accountsettings.view.events.AccountCustomizeEvent;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.SettingScreenDaa;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.premium.module.user.accountsettings.view.parameters.SettingExtScreenData;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.google.common.eventbus.Subscribe;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class SettingPresenter extends AbstractPresenter<ISettingContainer> implements ICustomizePresenter {
    private static final long serialVersionUID = 1L;

    public SettingPresenter() {
        super(ISettingContainer.class);
    }

    @Override
    protected void postInitView() {
        EventBusFactory.getInstance().register(new ApplicationEventListener<AccountCustomizeEvent.GotoUploadLogo>() {
            private static final long serialVersionUID = 7232775383781008850L;

            @Subscribe
            @Override
            public void handle(AccountCustomizeEvent.GotoUploadLogo event) {
                LogoUploadPresenter presenter = PresenterResolver.getPresenter(LogoUploadPresenter.class);
                if (event.getData() != null) {
                    presenter.go(view.getWidget(), (SettingScreenDaa.LogoUpload) event.getData());
                }

            }
        });
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;

        accountContainer.gotoSubView(SettingUIConstants.SETTING);

        AbstractPresenter<?> presenter;
        if (data instanceof SettingExtScreenData.GeneralSetting || data == null) {
            presenter = PresenterResolver.getPresenter(GeneralSettingPresenter.class);
        } else if (data instanceof SettingExtScreenData.Customize) {
            presenter = PresenterResolver.getPresenter(ThemeCustomizePresenter.class);
        } else if (data instanceof SettingExtScreenData.LogoUpload) {
            presenter = PresenterResolver.getPresenter(LogoChangePresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }
        AccountSettingBreadcrumb breadcrumb = ViewManager.getCacheComponent(AccountSettingBreadcrumb.class);
        breadcrumb.gotoSettingPage();
        presenter.go(view.getWidget(), data);
    }

}
