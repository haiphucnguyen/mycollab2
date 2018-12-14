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

import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.project.view.ProjectMainView;
import com.mycollab.module.user.dao.UserAccountMapper;
import com.mycollab.module.user.domain.SimpleBillingAccount;
import com.mycollab.module.user.domain.SimpleUser;
import com.mycollab.module.user.domain.UserAccount;
import com.mycollab.module.user.domain.UserAccountExample;
import com.mycollab.module.user.service.BillingAccountService;
import com.mycollab.module.user.service.UserService;
import com.mycollab.vaadin.AppUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.Presenter;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.JavaScript;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.util.BrowserCookie;

import java.time.LocalDateTime;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@SpringComponent
@ViewScope
public class LoginPresenter extends Presenter<LoginView> {
    private static final long serialVersionUID = 1L;

    private static final Logger LOG = LoggerFactory.getLogger(LoginPresenter.class);

    private static final String ACCOUNT_COOKIE = "mycollab";
    private static final String TEMP_ACCOUNT_COOKIE = "temp_account_mycollab";

    @Autowired
    private UserService userService;

    @Autowired
    private BillingAccountService billingAccountService;

    @Autowired
    private UserAccountMapper userAccountMapper;

    void registerLoginByCookie() {
        BrowserCookie.detectCookieValue(ACCOUNT_COOKIE, value -> {
            if (StringUtils.isNotBlank(value)) {
                String[] loginParams = value.split("\\$");
                if (loginParams.length == 2) {
                    try {
                        login(loginParams[0], EnDecryptHelper.decryptText(loginParams[1]), false);
                    } catch (Exception e) {
                        LOG.info("Password is expired");
                    }
                }
            } else {
                try {
                    SimpleUser user = (SimpleUser) MyCollabSession.getSessionVariable(MyCollabSession.USER_VAL);
                    if (user != null) {
                        afterDoLogin(user);
                    } else {
                        authenticateWithTempCookieValue();
                    }
                } catch (Exception e) {
                    LOG.info("Password is expired", e);
                }
            }
        });
    }

    void login(String username, String password, boolean isRemembered) {
        SimpleUser user = userService.authentication(username, password, AppUI.getSubDomain(), false);

        if (isRemembered) {
            rememberAccount(username, password);
        } else {
            rememberTempAccount(username, password);
        }

        afterDoLogin(user);
    }

    private void afterDoLogin(SimpleUser user) {
        SimpleBillingAccount billingAccount = billingAccountService.getBillingAccountById(AppUI.getAccountId());
        LOG.info(String.format("Get billing account successfully - Pricing: %s, User: %s - %s", "" + billingAccount.getBillingPlan().getPricing(),
                user.getUsername(), user.getDisplayName()));
        UserUIContext.getInstance().setSessionVariables(user, billingAccount);

        UserAccount userAccount = new UserAccount();
        userAccount.setLastaccessedtime(LocalDateTime.now());
        UserAccountExample ex = new UserAccountExample();
        ex.createCriteria().andAccountidEqualTo(billingAccount.getId()).andUsernameEqualTo(user.getUsername());
        userAccountMapper.updateByExampleSelective(userAccount, ex);

        view.getUI().getNavigator().navigateTo(ProjectMainView.VIEW_NAME);
    }

    private void authenticateWithTempCookieValue() {
        BrowserCookie.detectCookieValue(TEMP_ACCOUNT_COOKIE, value -> {
            if (value != null && !value.equals("")) {
                String[] loginParams = value.split("\\$");
                if (loginParams.length == 2) {
                    try {
                        login(loginParams[0], EnDecryptHelper.decryptText(loginParams[1]), false);
                    } catch (UserInvalidInputException e) {
                        LOG.info("Password is expired");
                    }
                }
            }
        });
    }

    private void rememberAccount(String username, String password) {
        String storeVal = username + "$" + EnDecryptHelper.encryptText(password);
        BrowserCookie.setCookie(ACCOUNT_COOKIE, storeVal);
    }

    private void rememberTempAccount(String username, String password) {
        String storeVal = username + "$" + EnDecryptHelper.encryptText(password);
        String setCookieVal = String.format("var now = new Date(); now.setTime(now.getTime() + 1 * 1800 * 1000); " +
                "document.cookie = \"%s=%s; expires=\" + now.toUTCString() + \"; path=/\";", TEMP_ACCOUNT_COOKIE, storeVal);
        JavaScript.getCurrent().execute(setCookieVal);
    }

    private void unsetRememberPassword() {
        BrowserCookie.setCookie(ACCOUNT_COOKIE, "");
        BrowserCookie.setCookie(TEMP_ACCOUNT_COOKIE, "");
    }
}
