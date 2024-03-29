package com.mycollab.premium.shell.view.components;

import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import org.springframework.web.client.RestTemplate;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

/**
 * @author myCollab Ltd
 * @since 5.3.2
 */
public class BuyPremiumSoftwareWindow extends MWindow {
    public BuyPremiumSoftwareWindow() {
        super("Buy MyCollab Pro edition");
        this.withModal(true).withResizable(false).withWidth("750px");
        RestTemplate restTemplate = new RestTemplate();
        MVerticalLayout content = new MVerticalLayout();
        try {
            ServerConfiguration serverConfiguration = AppContextUtil.getSpringBean(ServerConfiguration.class);
            String result = restTemplate.getForObject(serverConfiguration.getApiUrl("linktobuy"), String.class);
            Label webPage = ELabel.html(result).withHeight("600px");
            this.setContent(content.with(webPage).withAlign(webPage, Alignment.TOP_CENTER));
        } catch (Exception e) {
            String result = FileUtils.readFileAsPlainString("buying.html");
            Label webPage = new Label(result, ContentMode.HTML);
            this.setContent(content.with(webPage).withAlign(webPage, Alignment.TOP_CENTER));
        }
    }
}
