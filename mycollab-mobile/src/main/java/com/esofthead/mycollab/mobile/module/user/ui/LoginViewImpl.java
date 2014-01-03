package com.esofthead.mycollab.mobile.module.user.ui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.mobile.UIConstants;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.UserPreference;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.UserPreferenceService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.VerticalLayout;

/**
 * 
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class LoginViewImpl extends VerticalLayout {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(LoginViewImpl.class);

	public LoginViewImpl() {
		super();

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
		Label welcomeText = new Label("Welcome Back!");
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

		Button signInBtn = new Button("Sign In");
		signInBtn.setWidth("100%");
		signInBtn.addStyleName(UIConstants.BUTTON_BIG);
		signInBtn.addStyleName(UIConstants.COLOR_BLUE);
		signInBtn.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(Button.ClickEvent event) {
				doLogin(emailField.getValue(), pwdField.getValue());
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

	private void doLogin(String username, String password) {
		UserService userService = ApplicationContextUtil
				.getSpringBean(UserService.class);
		SimpleUser user = userService.authentication(username, password,
				AppContext.getSubDomain(), false);

		BillingAccountService billingAccountService = ApplicationContextUtil
				.getSpringBean(BillingAccountService.class);

		SimpleBillingAccount billingAccount = billingAccountService
				.getBillingAccountById(AppContext.getAccountId());

		log.debug("Get billing account successfully: "
				+ BeanUtility.printBeanObj(billingAccount));

		UserPreferenceService preferenceService = ApplicationContextUtil
				.getSpringBean(UserPreferenceService.class);
		UserPreference pref = preferenceService.getPreferenceOfUser(username,
				AppContext.getAccountId());

		log.debug("Login to system successfully. Save user and preference "
				+ pref + " to session");
	}
}
