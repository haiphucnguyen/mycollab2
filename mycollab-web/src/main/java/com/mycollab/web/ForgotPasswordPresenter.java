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
package com.mycollab.web;

import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.ShellI18nEnum;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.i18n.LocalizationHelper;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.user.domain.User;
import com.mycollab.module.user.service.UserService;
import com.mycollab.shell.event.ShellEvent;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.Presenter;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@SpringComponent
@ViewScope
public class ForgotPasswordPresenter extends Presenter<ForgotPasswordView> {

    @Autowired
    private ExtMailService extMailService;

    @Autowired
    private UserService userService;

    void checkMailSetup() {
        if (!extMailService.isMailSetupValid()) {
            NotificationUtil.showErrorNotification(UserUIContext.getMessage(ShellI18nEnum.WINDOW_SMTP_CONFIRM_SETUP_FOR_USER));
        }
    }

    void sendForgotPassword(String username) {
        if (StringUtils.isValidEmail(username)) {
            User user = userService.findUserByUserName(username);

            if (user == null) {
                NotificationUtil.showErrorNotification(LocalizationHelper.getMessage(AppUI.getDefaultLocale(), GenericI18Enum.ERROR_USER_IS_NOT_EXISTED, username));
            } else {
                userService.requestToResetPassword(user.getUsername());
                NotificationUtil.showNotification(LocalizationHelper.getMessage(AppUI.getDefaultLocale(), GenericI18Enum.OPT_SUCCESS),
                        LocalizationHelper.getMessage(AppUI.getDefaultLocale(), ShellI18nEnum.OPT_EMAIL_SENDER_NOTIFICATION));
                EventBusFactory.getInstance().post(new ShellEvent.LogOut(this, null));
            }
        } else {
            NotificationUtil.showErrorNotification(LocalizationHelper.getMessage(AppUI.getDefaultLocale(), ErrorI18nEnum.NOT_VALID_EMAIL, username));
        }
    }

    void backToLoginView() {
        view.getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
    }
}
