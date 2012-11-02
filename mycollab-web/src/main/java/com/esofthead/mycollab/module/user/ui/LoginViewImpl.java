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
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
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

		private TextField usernameField;
		private PasswordField passwordField;

		public LoginForm() {
			this.setCaption("Login Form");
			usernameField = new TextField("Email");
			usernameField.setValue("hainguyen@esofthead.com");
			this.getLayout().addComponent(usernameField);

			passwordField = new PasswordField("Password");
			passwordField.setValue("123456");
			StringLengthValidator passwordValidator = new StringLengthValidator(
					"Password length must be greater than 6", 6,
					Integer.MAX_VALUE, false);
			passwordField.addValidator(passwordValidator);
			this.getLayout().addComponent(passwordField);

			HorizontalLayout buttonLayout = new HorizontalLayout();
			this.setFooter(buttonLayout);

			buttonLayout.addComponent(new Button("Login",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						public void buttonClick(ClickEvent event) {
							try {
								LoginViewImpl.this.loginPresenter.doLogin(
										(String) LoginForm.this.usernameField
												.getValue(),
										(String) LoginForm.this.passwordField
												.getValue());
							} catch (EngroupException e) {
								LoginForm.this.setComponentError(new UserError(
										e.getMessage()));

							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}));

			buttonLayout.addComponent(new Button("Signup",
					new Button.ClickListener() {
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {

						}
					}));
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
