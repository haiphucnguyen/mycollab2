/**
 * This file is part of mycollab-mobile.
 *
 * mycollab-mobile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-mobile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-mobile.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.mobile.module.user.view;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.mobile.module.user.events.UserEvent;
import com.esofthead.mycollab.mobile.ui.AbstractMobileMainView;
import com.esofthead.mycollab.mobile.ui.UIConstants;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewEvent;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.vaadin.addon.touchkit.ui.EmailField;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import org.vaadin.jouni.dom.Dom;
import org.vaadin.viritin.layouts.MVerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class LoginViewImpl extends AbstractMobileMainView implements LoginView {
    private static final long serialVersionUID = 1L;

    public LoginViewImpl() {
        super();
        initUI();
    }

    private void initUI() {
        this.setStyleName("login-view");
        this.setSizeFull();

        MVerticalLayout contentLayout = new MVerticalLayout().withStyleName("content-wrapper").withFullWidth();
        contentLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Image mainLogo = new Image(null, new ThemeResource("icons/logo_m.png"));
        contentLayout.addComponent(mainLogo);

        Label introText = new Label("MyCollab helps you do all your office jobs on the computers, phones and tablets you use");
        introText.setStyleName("intro-text");
        contentLayout.addComponent(introText);

        CssLayout welcomeTextWrapper = new CssLayout();
        ELabel welcomeText = new ELabel("Welcome Back!").withStyleName("h1");
        welcomeTextWrapper.addComponent(welcomeText);
        contentLayout.addComponent(welcomeTextWrapper);

        final EmailField emailField = new EmailField();
        emailField.setWidth("100%");
        new Dom(emailField).setAttribute("placeholder", "Email address");
        contentLayout.addComponent(emailField);

        final PasswordField pwdField = new PasswordField();
        pwdField.setWidth("100%");
        new Dom(pwdField).setAttribute("placeholder", "Password");
        contentLayout.addComponent(pwdField);

        final CheckBox rememberPassword = new CheckBox();
        rememberPassword.setWidth("100%");
        rememberPassword.setCaption("Remember password");
        rememberPassword.setValue(true);
        contentLayout.addComponent(rememberPassword);

        Button signInBtn = new Button("Sign In", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                try {
                    LoginViewImpl.this.fireEvent(new ViewEvent<>(LoginViewImpl.this, new UserEvent.PlainLogin(
                            emailField.getValue(), pwdField.getValue(), rememberPassword.getValue())));
                } catch (Exception e) {
                    throw new MyCollabException(e);
                }
            }
        });
        signInBtn.setWidth("100%");
        signInBtn.addStyleName(UIConstants.BUTTON_ACTION);
        contentLayout.addComponent(signInBtn);

        this.addComponent(contentLayout);
    }
}
