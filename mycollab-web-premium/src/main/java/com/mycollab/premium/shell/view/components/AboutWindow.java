package com.mycollab.premium.shell.view.components;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.configuration.StorageFactory;
import com.mycollab.core.Version;
import com.mycollab.premium.license.service.LicenseResolver;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.shell.view.components.AbstractAboutWindow;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebResourceIds;
import com.mycollab.vaadin.web.ui.WebUIConstants;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@ViewComponent
public class AboutWindow extends AbstractAboutWindow {
    public AboutWindow() {
        super();

        MHorizontalLayout content = new MHorizontalLayout().withMargin(true).withFullWidth();
        this.setContent(content);

        Image about = new Image("", new ExternalResource(StorageFactory.generateAssetRelativeLink(WebResourceIds._about)));
        MVerticalLayout rightPanel = new MVerticalLayout();
        ELabel versionLbl = ELabel.h2(String.format("MyCollab Enterprise Edition %s", Version.getVersion()));
        Label javaNameLbl = new Label(String.format("%s, %s", System.getProperty("java.vm.name"),
                System.getProperty("java.runtime.version")));
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        Label osLbl = new Label(String.format("%s, %s", System.getProperty("os.name"), browser.getBrowserApplication()));
        osLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        Div licenseDiv = new Div().appendChild(new Text("Powered by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab commercial license"));
        Label licenseLbl = new Label(licenseDiv.write(), ContentMode.HTML);
        rightPanel.with(versionLbl, javaNameLbl, osLbl, licenseLbl);

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
        if (licenseInfo == null) {
            Label licenseInfoLbl = new Label(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
            rightPanel.add(licenseInfoLbl);
        } else {
            Label licenseInfoLbl;
            if (licenseInfo.isExpired()) {
                licenseInfoLbl = new Label("License to <b>" + licenseInfo.getLicenseOrg() + "</b>. The license is expired", ContentMode.HTML);
            } else {
                if (licenseInfo.isTrial()) {
                    licenseInfoLbl = new Label("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Trial, Expire at <b>" + UserUIContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>", ContentMode.HTML);
                } else {
                    licenseInfoLbl = new Label("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Expire at <b>" + UserUIContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>. Max users is <b>" + licenseInfo.getMaxUsers() + "</b>",
                            ContentMode.HTML);
                }
            }
            rightPanel.add(licenseInfoLbl);
        }

        if (licenseInfo != null && licenseInfo.isTrial()) {
            MButton buyBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_BUY_LICENSE), clickEvent -> {
                UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow());
                close();
            }).withStyleName(WebUIConstants.BUTTON_ACTION);
            MButton editLicenseBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_ENTER_LICENSE), clickEvent -> {
                UI.getCurrent().addWindow(new LicenseActivationWindow());
                close();
            }).withStyleName(WebUIConstants.BUTTON_ACTION);
            rightPanel.addComponent(new MHorizontalLayout(buyBtn, editLicenseBtn));
        }

        Label copyRightLbl = new Label(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                new GregorianCalendar().get(Calendar.YEAR) + ""), ContentMode.HTML);
        rightPanel.with(copyRightLbl);
        content.with(about, rightPanel).expand(rightPanel);
    }
}
