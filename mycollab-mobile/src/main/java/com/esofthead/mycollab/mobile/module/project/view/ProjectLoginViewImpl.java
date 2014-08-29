package com.esofthead.mycollab.mobile.module.project.view;

import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.mobile.UIConstants;
import com.esofthead.mycollab.mobile.module.crm.events.CrmEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobileMainView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 *
 * @since 4.4.0
 *
 */

@ViewComponent
public class ProjectLoginViewImpl extends AbstractMobileMainView implements
		ProjectLoginView {

	private static final long serialVersionUID = 2079094611178305339L;

	public ProjectLoginViewImpl() {

		initUI();
	}

	private void initUI() {
		this.setStyleName("login-view");
		this.setSizeFull();

		VerticalLayout contentLayout = new VerticalLayout();
		contentLayout.setStyleName("content-wrapper");
		contentLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		contentLayout.setMargin(true);
		contentLayout.setSpacing(true);
		contentLayout.setWidth("320px");

		Image mainLogo = new Image(null, new ThemeResource("icons/logo_m.png"));
		contentLayout.addComponent(mainLogo);

		Label introText = new Label(
				"MyCollab helps you do all your office jobs on the computers, phones and tablets you use");
		introText.setStyleName("intro-text");
		contentLayout.addComponent(introText);

		CssLayout welcomeTextWrapper = new CssLayout();
		welcomeTextWrapper.setStyleName("welcometext-wrapper");
		welcomeTextWrapper.setWidth("100%");
		welcomeTextWrapper.setHeight("15px");
		Label welcomeText = new Label("Login to Projects");
		welcomeText.setWidth("150px");
		welcomeTextWrapper.addComponent(welcomeText);
		contentLayout.addComponent(welcomeTextWrapper);

		final EmailField emailField = new EmailField();
		emailField.setWidth("100%");
		emailField.setInputPrompt("E-mail Address");
		emailField.setStyleName("email-input");
		contentLayout.addComponent(emailField);

		final PasswordField pwdField = new PasswordField();
		pwdField.setWidth("100%");
		pwdField.setInputPrompt("Password");
		pwdField.setStyleName("password-input");
		contentLayout.addComponent(pwdField);

		final CheckBox rememberPassword = new CheckBox();
		rememberPassword.setWidth("100%");
		rememberPassword.setCaption("Remember password");
		rememberPassword.setValue(true);
		contentLayout.addComponent(rememberPassword);

		Button signInBtn = new Button("Sign In");
		signInBtn.setWidth("100%");
		signInBtn.addStyleName(UIConstants.BUTTON_BIG);
		signInBtn.addStyleName(UIConstants.COLOR_BLUE);
		signInBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				EventBusFactory.getInstance().post(
						new CrmEvent.PlainLogin(this, new String[] {
								emailField.getValue(), pwdField.getValue(),
								String.valueOf(rememberPassword.getValue()) }));
			}
		});
		contentLayout.addComponent(signInBtn);

		Button createAccountBtn = new Button("Create Account");
		createAccountBtn.setWidth("100%");
		createAccountBtn.addStyleName(UIConstants.BUTTON_BIG);
		createAccountBtn.addStyleName(UIConstants.COLOR_GRAY);
		contentLayout.addComponent(createAccountBtn);

		this.addComponent(contentLayout);
	}
}
