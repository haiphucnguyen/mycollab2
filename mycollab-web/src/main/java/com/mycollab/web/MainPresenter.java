package com.mycollab.web;

import com.mycollab.module.user.service.BillingAccountService;
import com.mycollab.vaadin.AppUI;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@SpringComponent
@UIScope
public class MainPresenter implements Serializable {

    private MainView view;

    @Autowired
    private BillingAccountService billingAccountService;

    void initView(MainView mainView) {
        this.view = mainView;
    }

    void setDefaultView() {
        int activeUsersCount = billingAccountService.getTotalActiveUsersInAccount(AppUI.getAccountId());
        if (activeUsersCount == 0) {
            view.getUI().getNavigator().navigateTo(SetupNewInstanceView.VIEW_NAME);
        } else {
            // Read previously stored cookie value
            navigateToLoginView();
        }
    }


    private void navigateToLoginView() {
        view.getUI().getNavigator().navigateTo(LoginView.VIEW_NAME);
    }
}
