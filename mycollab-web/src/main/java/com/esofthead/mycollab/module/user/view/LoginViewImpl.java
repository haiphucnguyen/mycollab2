package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Form;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@ViewComponent
public class LoginViewImpl extends AbstractView implements LoginView {

    private static final long serialVersionUID = 1L;

    public LoginViewImpl() {
        this.addComponent(new LoginForm());
    }

    private class LoginForm extends Form {

        private static final long serialVersionUID = 1L;
        private final TextField usernameField;
        private final PasswordField passwordField;

        public LoginForm() {
            // this.setCaption("Login Form");
            CustomLayout custom = new CustomLayout("loginForm");
            custom.addStyleName("customLoginForm");
            usernameField = new TextField("Email");
            usernameField.setValue("hainguyen@esofthead.com");

            custom.addComponent(usernameField, "usernameField");

            passwordField = new PasswordField("Password");
            passwordField.setValue("123456");
            StringLengthValidator passwordValidator = new StringLengthValidator(
                    "Password length must be greater than 6", 6,
                    Integer.MAX_VALUE, false);
            passwordField.addValidator(passwordValidator);

            custom.addComponent(passwordField, "passwordField");

            Button loginBtn = new Button("Login", new Button.ClickListener() {
                private static final long serialVersionUID = 1L;

                @Override
                public void buttonClick(ClickEvent event) {
                    try {
                        LoginViewImpl.this.fireEvent(new UserEvent.PlainLogin(LoginViewImpl.this, new String[]{(String) usernameField.getValue(), (String) passwordField.getValue()}));
                    } catch (EngroupException e) {
                        LoginForm.this.setComponentError(new UserError(e
                                .getMessage()));

                    } catch (Exception e) {
                        throw new EngroupException(e);
                    }
                }
            });

            loginBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            custom.addComponent(loginBtn, "loginButton");

            Button signupBtn = new Button("Sign up Now!",
                    new Button.ClickListener() {
                        private static final long serialVersionUID = 1L;

                        @Override
                        public void buttonClick(ClickEvent event) {
                        }
                    });
            signupBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
            custom.addComponent(signupBtn, "signupLink");

            this.setLayout(custom);
        }
    }
}
