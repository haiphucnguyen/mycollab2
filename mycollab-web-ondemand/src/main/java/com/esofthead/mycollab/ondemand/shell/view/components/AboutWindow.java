package com.esofthead.mycollab.ondemand.shell.view.components;

import com.esofthead.mycollab.core.MyCollabVersion;
import com.esofthead.mycollab.shell.view.components.AbstractAboutWindow;
import com.esofthead.mycollab.vaadin.mvp.ViewComponent;
import com.esofthead.mycollab.vaadin.ui.AssetResource;
import com.esofthead.mycollab.vaadin.web.ui.UIConstants;
import com.esofthead.mycollab.vaadin.web.ui.WebResourceIds;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.hp.gagawa.java.elements.Text;
import com.vaadin.server.Page;
import com.vaadin.server.WebBrowser;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
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
        super("About");
        this.setModal(true);
        this.setResizable(false);
        this.center();
        this.setWidth("600px");

        MHorizontalLayout content = new MHorizontalLayout().withMargin(true).withWidth("100%");
        this.setContent(content);

        Image about = new Image("", new AssetResource(WebResourceIds._about));
        MVerticalLayout rightPanel = new MVerticalLayout();
        Label versionLbl = new Label(String.format("MyCollab Cloud Edition %s", MyCollabVersion.getVersion()));
        versionLbl.addStyleName(ValoTheme.LABEL_H2);
        versionLbl.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        Label javaNameLbl = new Label(String.format("%s, %s", System.getProperty("java.vm.name"),
                System.getProperty("java.runtime.version")));
        WebBrowser browser = Page.getCurrent().getWebBrowser();
        Label osLbl = new Label(String.format("%s, %s", System.getProperty("os.name"),
                browser.getBrowserApplication()));
        osLbl.addStyleName(UIConstants.LABEL_WORD_WRAP);
        Div licenseDiv = new Div().appendChild(new Text("Powered by: "))
                .appendChild(new A("https://www.mycollab.com")
                        .appendText("MyCollab")).appendChild(new Text(". MyCollab Commercial license"));
        Label licenseLbl = new Label(licenseDiv.write(), ContentMode.HTML);
        Label copyRightLbl = new Label(String.format("&copy; %s - %s MyCollab Ltd. All rights reserved", "2011",
                new GregorianCalendar().get(Calendar.YEAR) + ""), ContentMode.HTML);
        rightPanel.with(versionLbl, javaNameLbl, osLbl, licenseLbl, copyRightLbl)
                .withAlign(copyRightLbl, Alignment.BOTTOM_LEFT);
        content.with(about, rightPanel).expand(rightPanel);
    }
}