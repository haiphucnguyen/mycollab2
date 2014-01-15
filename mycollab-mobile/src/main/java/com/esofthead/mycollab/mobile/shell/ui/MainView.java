package com.esofthead.mycollab.mobile.shell.ui;

import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.mobile.shell.events.ShellEvent;
import com.esofthead.mycollab.vaadin.mvp.AbstractMobileMainView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@ViewComponent
public class MainView extends AbstractMobileMainView {

    public MainView() {
        super();

        initUI();
    }

    private void initUI() {
        this.setStyleName("main-view");
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
        contentLayout.addComponent(welcomeTextWrapper);

        ModuleButton crmButton = new ModuleButton("Customer Management");
        crmButton.setWidth("100%");
        crmButton.addStyleName("crm");
        crmButton.addClickListener(new NavigationButton.NavigationButtonClickListener() {
            @Override
            public void buttonClick(NavigationButton.NavigationButtonClickEvent event) {
                EventBus.getInstance().fireEvent(new ShellEvent.GotoCrmModule(this, null));
            }
        });
        contentLayout.addComponent(crmButton);

        ModuleButton pmButton = new ModuleButton("Project Management");
        pmButton.setWidth("100%");
        pmButton.addStyleName("project");
        contentLayout.addComponent(pmButton);

        ModuleButton fileButton = new ModuleButton("Document Management");
        fileButton.setWidth("100%");
        fileButton.addStyleName("document");
        contentLayout.addComponent(fileButton);

        this.addComponent(contentLayout);
    }
}
