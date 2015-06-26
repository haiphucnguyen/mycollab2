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
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ISettingContainer;
import com.esofthead.mycollab.module.user.accountsettings.customize.view.ISettingPresenter;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountModule;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.SettingExtScreenData;
import com.esofthead.mycollab.module.user.ui.SettingUIConstants;
import com.esofthead.mycollab.vaadin.mvp.PresenterResolver;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class SettingPresenter extends AbstractPresenter<ISettingContainer> implements ISettingPresenter {
    private static final long serialVersionUID = 1L;

    public SettingPresenter() {
        super(ISettingContainer.class);
    }


    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        AccountModule accountContainer = (AccountModule) container;

        accountContainer.gotoSubView(SettingUIConstants.GENERAL_SETTING);

        AbstractPresenter<?> presenter;
        if (data instanceof SettingExtScreenData.GeneralSetting || data == null) {
            presenter = PresenterResolver.getPresenter(GeneralSettingPresenter.class);
        } else if (data instanceof SettingExtScreenData.ThemeCustomize) {
            presenter = PresenterResolver.getPresenter(ThemeCustomizePresenter.class);
        } else {
            throw new MyCollabException("Do not support screen data " + data);
        }

        presenter.go(view.getWidget(), data);
    }

}
