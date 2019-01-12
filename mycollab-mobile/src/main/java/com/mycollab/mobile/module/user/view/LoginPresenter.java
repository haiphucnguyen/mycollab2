/**
 * Copyright © MyCollab
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.mobile.module.user.view;

import com.mycollab.common.i18n.ShellI18nEnum;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.i18n.LocalizationHelper;
import com.mycollab.mobile.mvp.AbstractPresenter;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.mvp.ScreenData;
import com.vaadin.ui.HasComponents;
import org.vaadin.touchkit.ui.NavigationManager;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
public class LoginPresenter extends AbstractPresenter<LoginView> {
    private static final long serialVersionUID = 1L;

    public LoginPresenter() {
        super(LoginView.class);
    }

    @Override
    protected void onGo(HasComponents navigationManager, ScreenData<?> data) {
        ((NavigationManager) navigationManager).navigateTo(getView());
        AppUI.addFragment("", LocalizationHelper.getMessage(SiteConfiguration.getDefaultLocale(), ShellI18nEnum.OPT_LOGIN_PAGE));
    }
}
