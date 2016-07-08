package com.mycollab.ondemand.shell.view.components;

import com.mycollab.core.MyCollabVersion;
import com.mycollab.shell.view.components.AbstractAboutWindow;
import com.mycollab.vaadin.mvp.ViewComponent;
import com.mycollab.vaadin.ui.AssetResource;
import com.mycollab.vaadin.ui.ELabel;
import com.mycollab.vaadin.web.ui.UIConstants;
import com.mycollab.vaadin.web.ui.WebResourceIds;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
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
        ELabel versionLbl = ELabel.h2(String.format("MyCollab Cloud Edition %s", MyCollabVersion.getVersion()));
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        Label osLbl = new Label(String.format("%s, %s", System.getProperty("os.name"),
                browser.getBrowserApplication()));
        osLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        Div licenseDiv = new Div().appendChild(new Text("Powered by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab Commercial license"));
        Label licenseLbl = ELabel.html(licenseDiv.write());
        Label copyRightLbl = ELabel.html(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                new GregorianCalendar().get(Calendar.YEAR) + ""));
        rightPanel.with(versionLbl, osLbl, licenseLbl, copyRightLbl)
                .withAlign(copyRightLbl, Alignment.BOTTOM_LEFT);
        content.with(about, rightPanel).expand(rightPanel);
    }
}