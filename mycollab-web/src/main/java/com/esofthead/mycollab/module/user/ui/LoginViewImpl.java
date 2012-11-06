package com.esofthead.mycollab.module.user.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.core.EngroupException;
import com.esofthead.mycollab.vaadin.mvp.ui.AbstractView;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.main.MainPage;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Form;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@Scope("prototype")
@Component
public class LoginViewImpl extends AbstractView implements LoginView {
	private static final long serialVersionUID = 1L;

	@Autowired
	private LoginPresenter loginPresenter;

	@Override
	protected CustomComponent initMainLayout() {
		CustomComponent comp = new CustomComponent(new LoginForm());
		return comp;
	}

	private class LoginForm extends Form {
		private static final long serialVersionUID = 1L;

		private final TextField usernameField;
		private final PasswordField passwordField;

		public LoginForm() {
			// this.setCaption("Login Form");
			CustomLayout custom = new CustomLayout("login-form");
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

			custom.addComponent(new Button("Login", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						LoginViewImpl.this.loginPresenter.doLogin(
								(String) LoginForm.this.usernameField
										.getValue(),
								(String) LoginForm.this.passwordField
										.getValue());
					} catch (EngroupException e) {
						LoginForm.this.setComponentError(new UserError(e
								.getMessage()));

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}), "loginButton");

			custom.addComponent(new Button("Sign up Now!",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {

						}
					}), "signupLink");

			this.setLayout(custom);
		}
	}

	@Override
	public void loginSuccess() {
		AppContext
				.getApplication()
				.getMainWindow()
				.setContent(
						AppContext.getView(MainPage.class).getCompContainer());
	}
}
