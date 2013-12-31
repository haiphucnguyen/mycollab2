package com.esofthead.mycollab.mobile;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by nghi on 12/31/13.
 */
public class LoginView extends VerticalLayout {

    public LoginView() {
        super();

        initUI();
    }

    private void initUI() {
        this.setStyleName("login-view");
        this.setDefaultComponentAlignment(Alignment.TOP_CENTER);

        Image mainLogo = new Image(null, new ThemeResource("icons/logo_m.png"));
        this.addComponent(mainLogo);
    }
}
