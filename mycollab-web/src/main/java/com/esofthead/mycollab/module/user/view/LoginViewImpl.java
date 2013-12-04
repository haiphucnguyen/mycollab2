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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.jetty.GenericServerRunner;
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
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;

@ViewComponent
public class LoginViewImpl extends AbstractView implements LoginView {

	private static final long serialVersionUID = 1L;

	public LoginViewImpl() {
		this.addComponent(new LoginForm());
	}

	public class LoginForm extends Form {

		private static final long serialVersionUID = 1L;
		private final TextField usernameField;
		private final PasswordField passwordField;
		private final CheckBox rememberMe;

		public LoginForm() {
			CustomLayout custom = CustomLayoutLoader.createLayout("loginForm");
			custom.addStyleName("customLoginForm");
			usernameField = new TextField("Email address");

			custom.addComponent(usernameField, "usernameField");

			passwordField = new PasswordField("Password");
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

			// if (GenericServerRunner.isFirstTimeRunner) {
			Label infoLbl = new Label(
					"It seems it is the first time you use MyCollab. The default username is admin@mycollab.com, password is admin123. You should change email/password when you access MyCollab successfully.",
					Label.CONTENT_XHTML);
			custom.addComponent(infoLbl);
			GenericServerRunner.isFirstTimeRunner = false;
			// }

			this.setLayout(custom);
			this.setHeight("100%");
		}
	}
}
