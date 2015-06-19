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

import com.esofthead.mycollab.module.user.accountsettings.customize.view.ICustomizeContainer;
import com.esofthead.mycollab.module.user.domain.AccountTheme;
import com.esofthead.mycollab.module.user.accountsettings.view.parameters.SettingScreenDaa;
import com.esofthead.mycollab.vaadin.mvp.ScreenData;
import com.esofthead.mycollab.vaadin.ui.AbstractPresenter;
import com.vaadin.ui.ComponentContainer;

/**
 * @author MyCollab Ltd.
 * @since 4.1
 */
public class LogoUploadPresenter extends AbstractPresenter<LogoUploadView> {
    private static final long serialVersionUID = 2528130438109089209L;

    public LogoUploadPresenter() {
        super(LogoUploadView.class);
    }

    @Override
    protected void onGo(ComponentContainer container, ScreenData<?> data) {
        ICustomizeContainer customizeContainer = (ICustomizeContainer) container;
        customizeContainer.removeAllComponents();
        customizeContainer.addComponent(view.getWidget());

        AccountTheme accountTheme = (AccountTheme) ((SettingScreenDaa.LogoUpload) data)
                .getExtraParam();
        view.editPhoto((byte[]) data.getParams(), accountTheme);

    }

}
