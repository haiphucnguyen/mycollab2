package com.mycollab.ondemand.shell.view.components;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.mycollab.core.Version;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.ui.UIConstants;
import com.mycollab.vaadin.web.ui.AbstractAboutWindow;
import com.mycollab.vaadin.web.ui.WebResourceIds;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
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

        AbstractStorageService storageService = AppContextUtil.getSpringBean(AbstractStorageService.class);
        Image about = new Image("", new ExternalResource(storageService.generateAssetRelativeLink(WebResourceIds._about)));

        MVerticalLayout rightPanel = new MVerticalLayout();
        ELabel versionLbl = ELabel.h2(String.format("MyCollab Cloud Edition %s", Version.getVersion()));
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        ELabel osLbl = new ELabel(String.format("%s, %s", System.getProperty("os.name"), browser.getBrowserApplication())).withFullWidth();

        Div licenseDiv = new Div().appendChild(new Text("Powered 11by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab Commercial license"));
        ELabel licenseLbl = ELabel.html(licenseDiv.write()).withFullWidth();
        ELabel copyRightLbl = ELabel.html(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                LocalDate.now().getYear() + "")).withFullWidth();
        rightPanel.with(versionLbl, osLbl, licenseLbl, copyRightLbl).withAlign(copyRightLbl, Alignment.BOTTOM_LEFT);
        content.with(about, rightPanel).expand(rightPanel);
    }
}
