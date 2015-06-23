package com.esofthead.mycollab.premium.module.user.accountsettings.customize.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.AbstractPageView;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.*;
import com.esofthead.mycollab.web.CustomLayoutExt;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.util.Iterator;

/**
 * @author MyCollab Ltd
 * @since 5.1.0
 */
@ViewComponent
public class LogoChangeViewImpl extends AbstractPageView implements LogoChangeView {

    private MHorizontalLayout siteLogoLayout;
    private MHorizontalLayout avatarLayout;

    public LogoChangeViewImpl() {
        this.setMargin(new MarginInfo(false, true, true, true));
        this.addStyleName("userInfoContainer");
    }

    @Override
    public void displayView() {
        removeAllComponents();
        MHorizontalLayout header = new MHorizontalLayout().withMargin(new MarginInfo(true, true, true, false))
                .withWidth("100%").withStyleName(UIConstants.HEADER_VIEW);

        Label headerLbl = new Label("Logos for your site");
        headerLbl.addStyleName("h1");
        header.with(headerLbl);

        siteLogoLayout = new MHorizontalLayout();
        avatarLayout = new MHorizontalLayout();

        with(header, siteLogoLayout, avatarLayout);
        displaySiteLayout();
        displayAvatarLayout();
    }

    private void displaySiteLayout() {
        MVerticalLayout leftPanel = new MVerticalLayout();
        MVerticalLayout rightPanel = new MVerticalLayout();
        Label logoLbl = new Label("Site Logo");
        logoLbl.addStyleName("h2");
        Label descLbl = new Label("Site logos appears on site menu and email notifications");
        leftPanel.with(logoLbl, descLbl);
        CustomLayout previewLayout = CustomLayoutExt.createLayout("topNavigation");
        previewLayout.setStyleName("example-block");
        previewLayout.setHeight("40px");
        previewLayout.setWidth("520px");

        Button currentLogo = AccountLogoFactory.createAccountLogoImageComponent(
                null, 150);
        previewLayout.addComponent(currentLogo, "mainLogo");
        final ServiceMenu serviceMenu = new ServiceMenu();
        serviceMenu.addStyleName("topNavPopup");

        Button.ClickListener clickListener = new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(final Button.ClickEvent event) {
                Iterator<Component> iterator = serviceMenu.iterator();

                while (iterator.hasNext()) {
                    Component comp = iterator.next();
                    if (comp != event.getButton()) {
                        comp.removeStyleName("selected");
                    }
                }
                event.getButton().addStyleName("selected");
            }
        };

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_CRM),
                MyCollabResource.newResource(WebResourceIds._16_customer), clickListener);

        serviceMenu.selectService(0);

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_PROJECT),
                MyCollabResource.newResource(WebResourceIds._16_project), clickListener);

        serviceMenu.addService(AppContext.getMessage(GenericI18Enum.MODULE_DOCUMENT),
                MyCollabResource.newResource(WebResourceIds._16_document), clickListener);

        previewLayout.addComponent(serviceMenu, "serviceMenu");
        rightPanel.with(previewLayout);
        siteLogoLayout.with(leftPanel, rightPanel);
    }

    private void displayAvatarLayout() {

    }
}
