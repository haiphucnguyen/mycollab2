package com.mycollab.premium.shell.view.components;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.vaadin.AbstractLicenseActivationWindow;
import com.mycollab.vaadin.mvp.ViewManager;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.springframework.web.client.RestTemplate;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author myCollab Ltd
 * @since 5.3.2
 */
public class BuyPremiumSoftwareWindow extends MWindow {
    public BuyPremiumSoftwareWindow() {
        super("Buy MyCollab Pro edition");
        this.withModal(true).withResizable(false).withWidth("700px");
        RestTemplate restTemplate = new RestTemplate();
        MVerticalLayout content = new MVerticalLayout();
        try {
            String result = restTemplate.getForObject(SiteConfiguration.getApiUrl("linktobuy"), String.class);
            Label webPage = new Label(result, ContentMode.HTML);
            webPage.setHeight("600px");
            this.setContent(content.with(webPage).withAlign(webPage, Alignment.TOP_CENTER));
        } catch (Exception e) {
            String result = FileUtils.readFileAsPlainString("buying.html");
            Label webPage = new Label(result, ContentMode.HTML);
            this.setContent(content.with(webPage).withAlign(webPage, Alignment.TOP_CENTER));
        }

        MButton editLicenseBtn = new MButton("Enter license code", clickEvent -> {
            Window activateWindow = ViewManager.getCacheComponent(AbstractLicenseActivationWindow.class);
            UI.getCurrent().addWindow(activateWindow);
            close();
        }).withStyleName(UIConstants.BUTTON_ACTION);
        content.with(editLicenseBtn).withAlign(editLicenseBtn, Alignment.MIDDLE_CENTER);
    }
}
