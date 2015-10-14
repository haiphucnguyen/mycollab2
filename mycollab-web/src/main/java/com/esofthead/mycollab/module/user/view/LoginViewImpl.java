/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.common.i18n.ShellI18nEnum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.jetty.ServerInstance;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewEvent;
import com.esofthead.mycollab.vaadin.ui.AssetResource;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.server.UserError;
import com.vaadin.ui.*;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@ViewComponent
public class LoginViewImpl extends AbstractPageView implements LoginView {
    private static final long serialVersionUID = 1L;

    private TextField usernameField;
    private PasswordField passwordField;
    private CheckBox rememberMe;

    public LoginViewImpl() {
        this.withSpacing(true);
        this.setSizeFull();
        MHorizontalLayout headerContainer = new MHorizontalLayout().withWidth("100%").withStyleName("login-header");
        Image appLogo = new Image(null, new AssetResource("icons/login-logo.png"));
        appLogo.setStyleName("app-logo");
        MHorizontalLayout socialLinks = new MHorizontalLayout().withStyleName("social-links");
        socialLinks.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        Link facebookLink = new Link("", new AssetResource("icons/email/footer-facebook.png"));
        facebookLink.setIcon(new AssetResource("icons/email/footer-facebook.png"));
        facebookLink.setTargetName("_blank");
        socialLinks.with(facebookLink);
        headerContainer.with(appLogo, socialLinks);

        with(headerContainer);

        MVerticalLayout formContainer = new MVerticalLayout().withStyleName("loginFormContainer");
        formContainer.setWidthUndefined();
        ELabel formHeaderLbl = new ELabel("Welcome back!").withStyleName("form-header");
        formContainer.with(formHeaderLbl).withAlign(formHeaderLbl, Alignment.TOP_CENTER);

        ELabel signinHeaderLbl = new ELabel("Sign in to MyCollab").withStyleName("form-subheader");
        formContainer.with(signinHeaderLbl).withAlign(signinHeaderLbl, Alignment.TOP_CENTER);

        usernameField = new TextField(AppContext.getMessage(ShellI18nEnum.FORM_EMAIL));
        passwordField = new PasswordField(AppContext.getMessage(ShellI18nEnum.FORM_PASSWORD));
        StringLengthValidator passwordValidator = new StringLengthValidator(
                "Password length must be greater than 6", 6, Integer.MAX_VALUE, false);
        passwordField.addValidator(passwordValidator);
        passwordField.addShortcutListener(new ShortcutListener("Signin", ShortcutAction.KeyCode.ENTER, null) {
            private static final long serialVersionUID = 5094514575531426118L;

            @Override
            public void handleAction(Object sender, Object target) {
                if (target == passwordField) {
                    try {
                        LoginViewImpl.this.fireEvent(new ViewEvent<>(LoginViewImpl.this,
                                new UserEvent.PlainLogin(usernameField.getValue(),
                                        passwordField.getValue(), rememberMe.getValue())));
                    } catch (Exception e) {
                        throw new MyCollabException(e);
                    }
                }
            }
        });
        rememberMe = new CheckBox(AppContext.getMessage(ShellI18nEnum.OPT_REMEMBER_PASSWORD), false);
        Button loginBtn = new Button(AppContext.getMessage(ShellI18nEnum.BUTTON_LOG_IN), new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    LoginViewImpl.this.fireEvent(new ViewEvent<>(LoginViewImpl.this, new UserEvent.PlainLogin(
                            usernameField.getValue(), passwordField.getValue(), rememberMe.getValue())));
                } catch (Exception e) {
                    throw new MyCollabException(e);
                }
            }
        });
        loginBtn.setStyleName(UIConstants.THEME_GREEN_LINK);

        formContainer.with(usernameField, passwordField, loginBtn);

        Button forgotPasswordBtn = new Button(AppContext.getMessage(ShellI18nEnum.BUTTON_FORGOT_PASSWORD),
                new Button.ClickListener() {
                    private static final long serialVersionUID = 1L;

                    @Override
                    public void buttonClick(Button.ClickEvent event) {
                        EventBusFactory.getInstance().post(new ShellEvent.GotoForgotPasswordPage(this, null));
                    }
                });
        forgotPasswordBtn.setStyleName(UIConstants.THEME_LINK);

        if (ServerInstance.getInstance().isFirstTimeRunner()) {
            setComponentError(new UserError(
                    "For the first time using MyCollab, the default email/password is admin@mycollab.com/admin123. You should change email/password when you access MyCollab successfully."));
            ServerInstance.getInstance().setIsFirstTimeRunner(false);
        }

        VerticalLayout spaceLayout = new VerticalLayout();
        with(formContainer, spaceLayout).withAlign(formContainer, Alignment.TOP_CENTER).expand(spaceLayout);
    }
}
