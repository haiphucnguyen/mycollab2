package com.esofthead.mycollab.shell.view;

import java.util.Date;

import org.vaadin.hene.popupbutton.PopupButton;

import com.esofthead.mycollab.module.billing.AccountStatusConstants;
import com.esofthead.mycollab.module.billing.service.BillingService;
import com.esofthead.mycollab.module.user.domain.BillingPlan;
import com.esofthead.mycollab.module.user.domain.SimpleBillingAccount;
import com.esofthead.mycollab.shell.events.ShellEvent;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.events.EventBus;
import com.esofthead.mycollab.vaadin.mvp.AbstractView;
import com.esofthead.mycollab.vaadin.mvp.ControllerRegistry;
import com.esofthead.mycollab.vaadin.mvp.IModule;
import com.esofthead.mycollab.vaadin.mvp.ModuleHelper;
import com.esofthead.mycollab.vaadin.ui.FeedbackWindow;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.vaadin.ui.UserAvatarControlFactory;
import com.esofthead.mycollab.vaadin.ui.ViewComponent;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.CustomLayoutLoader;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.event.LayoutEvents;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.lazyloadwrapper.LazyLoadWrapper;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@ViewComponent
public final class MainView extends AbstractView {

    private final CssLayout bodyLayout;

    private PopupButton serviceMenu;

    public MainView() {
        this.setSizeFull();
        this.addComponent(this.createTopMenu());
        this.bodyLayout = new CssLayout();
        this.bodyLayout.addStyleName("main-body");
        this.bodyLayout.setWidth("100%");
        this.bodyLayout.setHeight("100%");
        this.addComponent(this.bodyLayout);
        this.setExpandRatio(this.bodyLayout, 1.0f);
        this.addComponent(this.createFooter());
        this.setSizeFull();
        ControllerRegistry.addController(new MainViewController(this));
    }

    public void addModule(final IModule module) {
        ModuleHelper.setCurrentModule(module);
        this.bodyLayout.removeAllComponents();
        final LazyLoadWrapper comp = new LazyLoadWrapper(module.getWidget());
        this.bodyLayout.addComponent(comp);

        if (ModuleHelper.isCurrentCrmModule()) {
            serviceMenu.setCaption("CRM");
            serviceMenu.setIcon(MyCollabResource
                    .newResource("icons/16/customer_gray.png"));
        } else if (ModuleHelper.isCurrentProjectModule()) {
            serviceMenu.setCaption("Projects");
            serviceMenu.setIcon(MyCollabResource
                    .newResource("icons/16/project_gray.png"));
        } else if (ModuleHelper.isCurrentFileModule()) {
            serviceMenu.setCaption("Documents");
            serviceMenu.setIcon(MyCollabResource
                    .newResource("icons/16/document_gray.png"));
        } else {
            serviceMenu.setCaption("Services");
            serviceMenu.setIcon(null);
        }
    }

    private CustomLayout createFooter() {
        final CustomLayout footer = CustomLayoutLoader.createLayout("footer");
        final Button sendFeedback = new Button("Feedback");
        sendFeedback.setStyleName(UIConstants.THEME_ROUND_BUTTON);
        sendFeedback.addListener(new ClickListener() {

            @Override
            public void buttonClick(final ClickEvent event) {
                MainView.this.getWindow().addWindow(new FeedbackWindow());
            }
        });
        footer.addComponent(sendFeedback, "footer-right");
        return footer;
    }

    private CustomLayout createTopMenu() {
        final CustomLayout layout = CustomLayoutLoader
                .createLayout("topNavigation");
        layout.setStyleName("topNavigation");
        layout.setHeight("40px");
        layout.setWidth("100%");
        serviceMenu = new PopupButton("Services");
        serviceMenu.setStyleName("serviceMenu");
        serviceMenu.addStyleName("topNavPopup");
        final VerticalLayout vLayout = new VerticalLayout();
        vLayout.setWidth("200px");

        final Button crmLink = new Button("CRM", new Button.ClickListener() {
            @Override
            public void buttonClick(final ClickEvent event) {
                serviceMenu.setPopupVisible(false);
                EventBus.getInstance().fireEvent(
                        new ShellEvent.GotoCrmModule(this, null));
            }
        });
        crmLink.setIcon(MyCollabResource.newResource("icons/16/customer.png"));
        crmLink.setStyleName("link");
        vLayout.addComponent(crmLink);

        final Button prjLink = new Button("Projects",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(final ClickEvent event) {
                        serviceMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoProjectModule(this, null));
                    }
                });
        prjLink.setStyleName("link");
        prjLink.setIcon(MyCollabResource.newResource("icons/16/project.png"));
        vLayout.addComponent(prjLink);

        final Button docLink = new Button("Documents",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(final ClickEvent event) {
                        serviceMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoFileModule(this, null));
                    }
                });
        docLink.setStyleName("link");
        docLink.setIcon(MyCollabResource.newResource("icons/16/document.png"));
        vLayout.addComponent(docLink);

        serviceMenu.addComponent(vLayout);
        layout.addComponent(serviceMenu, "serviceMenu");

        final HorizontalLayout accountLayout = new HorizontalLayout();

        // display trial box if user in trial mode
        SimpleBillingAccount billingAccount = AppContext.getBillingAccount();
        if (AccountStatusConstants.TRIAL.equals(billingAccount.getStatus())) {
            Label informLbl = new Label("", Label.CONTENT_XHTML);
            informLbl.addStyleName("trialEndingNotification");
            informLbl.setHeight("100%");
            HorizontalLayout informBox = new HorizontalLayout();
            informBox.addStyleName("trialInformBox");
            informBox.setSizeFull();
            informBox.addComponent(informLbl);
            informBox.setMargin(false, true, false, false);
            informBox.addListener(new LayoutEvents.LayoutClickListener() {

                @Override
                public void layoutClick(LayoutClickEvent event) {
                    // TODO: go to upgrade billing plan page
                }
            });
            accountLayout.addComponent(informBox);
            accountLayout.setSpacing(true);
            accountLayout.setComponentAlignment(informBox,
                    Alignment.MIDDLE_LEFT);

            Date createdtime = billingAccount.getCreatedtime();
            long timeDeviation = System.currentTimeMillis()
                    - createdtime.getTime();
            double daysLeft = Math.floor(timeDeviation / (1000 * 60 * 60 * 24));
            if (daysLeft > 30) {
                BillingService billingService = ApplicationContextUtil
                        .getSpringBean(BillingService.class);
                BillingPlan freeBillingPlan = billingService
                        .getFreeBillingPlan();
                billingAccount.setBillingPlan(freeBillingPlan);
            } else {
                if (AppContext.isAdmin()) {
                    informLbl
                            .setValue("<div class='informBlock'>TRIAL ENDING<br>"
                                    + (30 - daysLeft)
                                    + " DAYS LEFT</div><div class='informBlock'>&gt;&gt;</div>");
                } else {
                    informLbl
                            .setValue("<div class='informBlock'>TRIAL ENDING<br>"
                                    + (30 - daysLeft)
                                    + " DAYS LEFT</div><div class='informBlock'>&gt;&gt;</div>");
                }
            }
        }

        final Embedded userAvatar = UserAvatarControlFactory
                .createUserAvatarEmbeddedComponent(
                        AppContext.getUserAvatarId(), 24);
        accountLayout.addComponent(userAvatar);
        accountLayout.setComponentAlignment(userAvatar, Alignment.MIDDLE_LEFT);

        final PopupButton accountMenu = new PopupButton(AppContext.getSession()
                .getDisplayName());
        final VerticalLayout accLayout = new VerticalLayout();
        accLayout.setWidth("120px");

        final Button myProfileBtn = new Button("Profile",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(final ClickEvent event) {
                        accountMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoUserAccountModule(this,
                                        new String[] { "preview" }));
                    }
                });
        myProfileBtn.setStyleName("link");
        accLayout.addComponent(myProfileBtn);

        final Button myAccountBtn = new Button("Account",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        accountMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoUserAccountModule(this,
                                        new String[] { "billing" }));
                    }
                });
        myAccountBtn.setStyleName("link");
        accLayout.addComponent(myAccountBtn);

        final Button userMgtBtn = new Button("User Management",
                new Button.ClickListener() {

                    @Override
                    public void buttonClick(final ClickEvent event) {
                        accountMenu.setPopupVisible(false);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.GotoUserAccountModule(this,
                                        new String[] { "user", "list" }));
                    }
                });
        userMgtBtn.setStyleName("link");
        accLayout.addComponent(userMgtBtn);

        final Button signoutBtn = new Button("Sign out",
                new Button.ClickListener() {
                    @Override
                    public void buttonClick(final ClickEvent event) {
                        AppContext.getInstance().setSession(null, null, null);
                        EventBus.getInstance().fireEvent(
                                new ShellEvent.LogOut(this, null));
                    }
                });
        signoutBtn.setStyleName("link");
        accLayout.addComponent(signoutBtn);

        accountMenu.addComponent(accLayout);
        accountMenu.setStyleName("accountMenu");
        accountMenu.addStyleName("topNavPopup");
        accountLayout.addComponent(accountMenu);

        layout.addComponent(accountLayout, "accountMenu");

        return layout;
    }
}
