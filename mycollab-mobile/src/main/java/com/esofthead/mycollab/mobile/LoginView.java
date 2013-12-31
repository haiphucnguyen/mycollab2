package com.esofthead.mycollab.mobile;

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
 * Created by nghi on 12/31/13.
 */
public class LoginView extends CssLayout {

    public LoginView() {
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

        Label introText = new Label("MyCollab helps you do all your office jobs on the computers, phones and tablets you use");
        introText.setStyleName("intro-text");
        contentLayout.addComponent(introText);

        CssLayout welcomeTextWrapper = new CssLayout();
        welcomeTextWrapper.setStyleName("welcometext-wrapper");
        welcomeTextWrapper.setWidth("100%");
        Label welcomeText = new Label("Welcome Back!");
        welcomeTextWrapper.addComponent(welcomeText);
        contentLayout.addComponent(welcomeTextWrapper);

        EmailField emailField = new EmailField();
        emailField.setWidth("100%");
        emailField.setInputPrompt("E-mail Address");
        contentLayout.addComponent(emailField);

        PasswordField pwdField = new PasswordField();
        pwdField.setWidth("100%");
        pwdField.setInputPrompt("Password");
        contentLayout.addComponent(pwdField);

        Button signInBtn = new Button("Sign In");
        signInBtn.setWidth("100%");
        signInBtn.addStyleName(UIConstants.BUTTON_BIG);
        signInBtn.addStyleName(UIConstants.COLOR_BLUE);
        contentLayout.addComponent(signInBtn);

        Button createAccountBtn = new Button("Create Account");
        createAccountBtn.setWidth("100%");
        createAccountBtn.addStyleName(UIConstants.BUTTON_BIG);
        createAccountBtn.addStyleName(UIConstants.COLOR_GRAY);
        contentLayout.addComponent(createAccountBtn);

        this.addComponent(contentLayout);
    }
}
