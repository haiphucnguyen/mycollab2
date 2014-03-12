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

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.jetty.GenericServerRunner;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@ViewComponent
public class LoginViewImpl extends AbstractPageView implements LoginView {

    private static final long serialVersionUID = 1L;

    public LoginViewImpl() {
        this.addComponent(new LoginForm());
    }

    class LoginForm extends CustomComponent {

        private static final long serialVersionUID = 1L;
        private final TextField usernameField;
        private final PasswordField passwordField;
        private final CheckBox rememberMe;

        public LoginForm() {
            final CustomLayout custom = CustomLayoutLoader
                    .createLayout("loginForm");
            custom.addStyleName("customLoginForm");
            usernameField = new TextField("Email address");

            custom.addComponent(usernameField, "usernameField");

            passwordField = new PasswordField("Password");
            StringLengthValidator passwordValidator = new StringLengthValidator(
                    "Password length must be greater than 6", 6,
                    Integer.MAX_VALUE, false);
            passwordField.addValidator(passwordValidator);

            custom.addComponent(passwordField, "passwordField");

            rememberMe = new CheckBox("Remember me for a week", false);
            custom.addComponent(rememberMe, "rememberMe");

            Button loginBtn = new Button("Sign In", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    try {

                        custom.removeComponent("customErrorMsg");

                        LoginViewImpl.this.fireEvent(new UserEvent.PlainLogin(
                                LoginViewImpl.this, new String[] {
                                        usernameField.getValue(),
                                        passwordField.getValue(),
                                        String.valueOf(rememberMe.getValue()) }));
                    } catch (MyCollabException e) {
                        custom.addComponent(new Label(e.getMessage()),
                                "customErrorMsg");

                    } catch (Exception e) {
                        throw new MyCollabException(e);
                    }
                }
            });

            loginBtn.setStyleName(UIConstants.THEME_GREEN_LINK);
            custom.addComponent(loginBtn, "loginButton");

            if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
                Link signupLink = new Link(
                        "Create an Account",
                        new ExternalResource("https://www.mycollab.com/pricing"));
                signupLink.setTargetName("_blank");
                custom.addComponent(signupLink, "signupLink");
            }

            Button forgotPasswordBtn = new Button("Forgot your password?",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                            EventBus.getInstance().fireEvent(
                                    new ShellEvent.GotoForgotPasswordPage(this,
                                            null));
                        }
                    });
            forgotPasswordBtn.setStyleName("link");
            custom.addComponent(forgotPasswordBtn, "forgotLink");

            if (GenericServerRunner.isFirstTimeRunner) {
                LoginForm.this
                        .setComponentError(new UserError(
                                "For the first time using MyCollab, the default email/password is admin@mycollab.com/admin123. You should change email/password when you access MyCollab successfully."));
                GenericServerRunner.isFirstTimeRunner = false;
            }

            this.setCompositionRoot(custom);
            this.setHeight("100%");
        }
    }
}
