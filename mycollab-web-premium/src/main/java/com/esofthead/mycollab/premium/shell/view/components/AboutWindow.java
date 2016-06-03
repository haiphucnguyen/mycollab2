package com.esofthead.mycollab.premium.shell.view.components;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.shell.view.components.AbstractAboutWindow;
import com.esofthead.mycollab.spring.AppContextUtil;
import com.esofthead.mycollab.vaadin.AbstractLicenseActivationWindow;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.mvp.ViewManager;
import com.esofthead.mycollab.vaadin.ui.AssetResource;
import com.esofthead.mycollab.vaadin.ui.ELabel;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.WebResourceIds;
import com.esofthead.mycollab.web.BuyPremiumSoftwareWindow;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
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

        Image about = new Image("", new AssetResource(WebResourceIds._about));
        MVerticalLayout rightPanel = new MVerticalLayout();
        ELabel versionLbl = ELabel.h2(String.format("MyCollab Enterprise Edition %s", MyCollabVersion.getVersion()));
        Label javaNameLbl = new Label(String.format("%s, %s", System.getProperty("java.vm.name"),
                System.getProperty("java.runtime.version")));
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        Label osLbl = new Label(String.format("%s, %s", System.getProperty("os.name"),
                browser.getBrowserApplication()));
        osLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        Div licenseDiv = new Div().appendChild(new Text("Powered by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab commercial license"));
        Label licenseLbl = new Label(licenseDiv.write(), ContentMode.HTML);
        rightPanel.with(versionLbl, javaNameLbl, osLbl, licenseLbl);

        LicenseResolver licenseResolver = AppContextUtil.getSpringBean(LicenseResolver.class);
        LicenseInfo licenseInfo = licenseResolver.getLicenseInfo();
        if (licenseInfo == null) {
            Label licenseInfoLbl = new Label("Invalid license");
            rightPanel.add(licenseInfoLbl);
        } else {
            Label licenseInfoLbl;
            if (licenseInfo.isExpired()) {
                licenseInfoLbl = new Label("License to <b>" + licenseInfo.getLicenseOrg() + "</b>. The license is expired", ContentMode.HTML);
            } else {
                if (licenseInfo.isTrial()) {
                    licenseInfoLbl = new Label("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Trial, Expire at <b>" + AppContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>", ContentMode.HTML);
                } else {
                    licenseInfoLbl = new Label("License to <b>" + licenseInfo
                            .getLicenseOrg() + "</b>. Expire at <b>" + AppContext.formatPrettyTime(licenseInfo
                            .getExpireDate()) + "</b>. Max users is <b>" + licenseInfo.getMaxUsers() + "</b>",
                            ContentMode.HTML);
                }
            }
            rightPanel.add(licenseInfoLbl);
        }

        if (licenseInfo != null && licenseInfo.isTrial()) {
            Button buyBtn = new Button("Buy a license", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    UI.getCurrent().addWindow(new BuyPremiumSoftwareWindow());
                    close();
                }
            });
            buyBtn.addStyleName(UIConstants.BUTTON_ACTION);
            Button editLicenseBtn = new Button("Enter license code", new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    Window activateWindow = ViewManager.getCacheComponent(AbstractLicenseActivationWindow.class);
                    UI.getCurrent().addWindow(activateWindow);
                    close();
                }
            });
            editLicenseBtn.addStyleName(UIConstants.BUTTON_ACTION);
            rightPanel.addComponent(new MHorizontalLayout().with(buyBtn, editLicenseBtn));
        }

        Label copyRightLbl = new Label(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                new GregorianCalendar().get(Calendar.YEAR) + ""), ContentMode.HTML);
        rightPanel.with(copyRightLbl);
        content.with(about, rightPanel).expand(rightPanel);
    }
}
