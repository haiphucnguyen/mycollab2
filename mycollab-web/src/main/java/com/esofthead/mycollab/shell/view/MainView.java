package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.module.crm.view.CrmContainer;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountDashboardView;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.View;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.Hr;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.VerticalLayout;
import org.vaadin.hene.popupbutton.PopupButton;

@SuppressWarnings("serial")
@ViewComponent
public final class MainView extends AbstractView {

    private final VerticalLayout bodyLayout;
    private MainViewController controller;

    public MainView() {
        this.addComponent(createTopMenu());
        bodyLayout = new VerticalLayout();
        this.addComponent(bodyLayout);
        controller = new MainViewController(this);
    }

    private CustomLayout createTopMenu() {
        CustomLayout layout = new CustomLayout("topNavigation");
        layout.setStyleName("topNavigation");
        layout.setHeight("44px");
        layout.setWidth("100%");
        final PopupButton serviceMenu = new PopupButton("Services");
        serviceMenu.setStyleName("serviceMenu");
        serviceMenu.addStyleName("topNavPopup");
        VerticalLayout vLayout = new VerticalLayout();
        vLayout.setWidth("200px");

        Button crmLink = new Button("Customer Management",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        serviceMenu.setPopupVisible(false);
                        CrmContainer crmContainer = ViewManager
                                .getView(CrmContainer.class);
                        bodyLayout.removeAllComponents();
                        bodyLayout.addComponent(crmContainer);
                    }
                });
        crmLink.setStyleName("link");
        vLayout.addComponent(crmLink);

        Button prjLink = new Button("Project Management",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        serviceMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoProjectPage(this, null));
                    }
                });
        prjLink.setStyleName("link");
        vLayout.addComponent(prjLink);

        serviceMenu.addComponent(vLayout);
        layout.addComponent(serviceMenu, "serviceMenu");

        final PopupButton accountMenu = new PopupButton(AppContext.getSession()
                .getDisplayname());
        accountMenu.setStyleName("link");
        VerticalLayout accLayout = new VerticalLayout();
        accLayout.setWidth("120px");

        Button myAccountBtn = new Button("My Account",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(ClickEvent event) {
                        accountMenu.setPopupVisible(false);
                        AccountDashboardView accountView = ViewManager
                                .getView(AccountDashboardView.class);
                        bodyLayout.removeAllComponents();
                        bodyLayout.addComponent(accountView);
                        bodyLayout.setComponentAlignment(accountView,
                                Alignment.MIDDLE_CENTER);
                    }
                });
        myAccountBtn.setStyleName("link");
        accLayout.addComponent(myAccountBtn);

        accLayout.addComponent(new Hr());

        Button signoutBtn = new Button("Sign out", new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                AppContext.setSession(new SimpleUser());
                EventBus.getInstance().fireEvent(
                        new ShellEvent.LogOut(this, null));
            }
        });
        signoutBtn.setStyleName("link");
        accLayout.addComponent(signoutBtn);

        accountMenu.addComponent(accLayout);
        accountMenu.setStyleName("accountMenu");
        accountMenu.addStyleName("topNavPopup");
        layout.addComponent(accountMenu, "accountMenu");
        return layout;
    }

    public void addView(View view) {
        bodyLayout.removeAllComponents();
        bodyLayout.addComponent(view.getWidget());
        bodyLayout.setComponentAlignment(view.getWidget(),
                Alignment.MIDDLE_CENTER);
    }
}
