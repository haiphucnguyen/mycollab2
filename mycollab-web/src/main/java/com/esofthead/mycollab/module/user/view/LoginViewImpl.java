package com.esofthead.mycollab.module.user.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.user.events.UserEvent;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.UserError;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Form;
import com.vaadin.ui.Link;
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
		private final CheckBox rememberMe;

		public LoginForm() {
			// this.setCaption("Login Form");
			CustomLayout custom = CustomLayoutLoader.createLayout("loginForm");
			custom.addStyleName("customLoginForm");
			usernameField = new TextField("Email address");
			usernameField.setValue("hainguyen@esofthead.com");

			custom.addComponent(usernameField, "usernameField");

			passwordField = new PasswordField("Password");
			passwordField.setValue("123456");
			StringLengthValidator passwordValidator = new StringLengthValidator(
					"Password length must be greater than 6", 6,
					Integer.MAX_VALUE, false);
			passwordField.addValidator(passwordValidator);

			custom.addComponent(passwordField, "passwordField");

			rememberMe = new CheckBox("Remember me for a week", true);
			custom.addComponent(rememberMe, "rememberMe");

			Button loginBtn = new Button("Sign In", new Button.ClickListener() {
				private static final long serialVersionUID = 1L;

				@Override
				public void buttonClick(ClickEvent event) {
					try {
						LoginViewImpl.this.fireEvent(new UserEvent.PlainLogin(
								LoginViewImpl.this, new String[] {
										(String) usernameField.getValue(),
										(String) passwordField.getValue(),
										String.valueOf(rememberMe
												.booleanValue()) }));
					} catch (MyCollabException e) {
						LoginForm.this.setComponentError(new UserError(e
								.getMessage()));

					} catch (Exception e) {
						throw new MyCollabException(e);
					}
				}
			});

			loginBtn.setStyleName(UIConstants.THEME_BLUE_LINK);
			custom.addComponent(loginBtn, "loginButton");

			Link signupLink = new Link("Create an Account",
					new ExternalResource("https://www.mycollab.com/pricing"));
			signupLink.setTargetName("_blank");
			custom.addComponent(signupLink, "signupLink");

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

			this.setLayout(custom);
			this.setHeight("100%");
		}
	}
}
