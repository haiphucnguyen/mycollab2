package com.mycollab.premium.shell.view.components;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.common.i18n.LicenseI18nEnum;
import com.mycollab.core.Version;
import com.mycollab.license.LicenseInfo;
import com.mycollab.license.service.LicenseResolver;
import com.mycollab.module.file.StorageUtils;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.AbstractAboutWindow;
import com.mycollab.vaadin.web.ui.WebResourceIds;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.time.LocalDate;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@ViewComponent
public class AboutWindow extends AbstractAboutWindow {
    public AboutWindow() {
        MHorizontalLayout content = new MHorizontalLayout().withMargin(true).withFullWidth();
        this.setContent(content);

        Image about = new Image("", new ExternalResource(StorageUtils.generateAssetRelativeLink(WebResourceIds._about)));
        MVerticalLayout rightPanel = new MVerticalLayout();
        ELabel versionLbl = ELabel.h2(String.format("MyCollab Enterprise Edition %s", Version.getVersion())).withFullWidth();
        ELabel javaNameLbl = new ELabel(String.format("%s, %s", System.getProperty("java.vm.name"),
                System.getProperty("java.runtime.version"))).withFullWidth();
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        ELabel osLbl = new ELabel(String.format("%s, %s", System.getProperty("os.name"), browser.getBrowserApplication())).withFullWidth();
        Div licenseDiv = new Div().appendChild(new Text("Powered by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab commercial license"));
        ELabel licenseLbl = ELabel.html(licenseDiv.write()).withFullWidth();
        rightPanel.with(versionLbl, javaNameLbl, osLbl, licenseLbl);

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
        if (licenseInfo == null) {
            Label licenseInfoLbl = new Label(UserUIContext.getMessage(LicenseI18nEnum.ERROR_LICENSE_INVALID));
            rightPanel.add(licenseInfoLbl);
        } else {
            Label licenseInfoLbl;
            if (licenseInfo.isExpired()) {
                licenseInfoLbl = ELabel.html("License to <b>" + licenseInfo.getLicenseOrg() + "</b>. The license is expired").withFullWidth();
            } else {
                if (licenseInfo.isTrial()) {
                    licenseInfoLbl = ELabel.html("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Trial, Expire at <b>" + UserUIContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>").withFullWidth();
                } else {
                    licenseInfoLbl = ELabel.html("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Expire at <b>" + UserUIContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>. Max users is <b>" + licenseInfo.getMaxUsers() + "</b>").withFullWidth();
                }
            }
            rightPanel.add(licenseInfoLbl);
        }

        if (licenseInfo != null && licenseInfo.isTrial()) {
            MButton buyBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_BUY_LICENSE), clickEvent -> {
                UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow());
                close();
            }).withStyleName(WebThemes.BUTTON_ACTION);
            MButton editLicenseBtn = new MButton(UserUIContext.getMessage(LicenseI18nEnum.ACTION_ENTER_LICENSE), clickEvent -> {
                UI.getCurrent().addWindow(new LicenseActivationWindow());
                close();
            }).withStyleName(WebThemes.BUTTON_ACTION);
            rightPanel.addComponent(new MHorizontalLayout(buyBtn, editLicenseBtn));
        }

        ELabel copyRightLbl = ELabel.html(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                LocalDate.now().getYear() + "")).withFullWidth();
        rightPanel.with(copyRightLbl);
        content.with(about, rightPanel).expand(rightPanel);
    }
}
