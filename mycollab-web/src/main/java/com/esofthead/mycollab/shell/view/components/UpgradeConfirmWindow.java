/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.shell.view.components;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.IgnoreException;
import com.esofthead.mycollab.jetty.ServerInstance;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;

import java.io.File;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UpgradeConfirmWindow extends Window {
    private static String headerTemplate = "MyCollab just got better . For the " +
            "enhancements and security purpose, you should upgrade to the latest version";

    public UpgradeConfirmWindow(final String version, final String autoDownloadLink, String manualDownloadLink,
                                final String installerFilePath) {
        super("A new update is ready to install");
        this.setModal(true);
        this.setResizable(false);
        this.center();
        this.setWidth("600px");

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        Div titleDiv = new Div().appendText(String.format(headerTemplate, version)).setStyle("font-weight:bold");
        content.with(new Label(titleDiv.write(), ContentMode.HTML));

        Div manualInstallLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;Manual install: ")
                .appendChild(new A(manualDownloadLink, "_blank")
                        .appendText("Download link"));
        content.with(new Label(manualInstallLink.write(), ContentMode.HTML));

        Div releaseNoteLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;Release Notes: ")
                .appendChild(new A("https://community.mycollab.com/release-notes/", "_blank")
                        .appendText("Link"));
        content.with(new Label(releaseNoteLink.write(), ContentMode.HTML));

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(true);
        Button skipBtn = new Button("Skip", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UpgradeConfirmWindow.this.close();
            }
        });
        skipBtn.addStyleName(UIConstants.THEME_GRAY_LINK);

        Button autoUpgradeBtn = new Button("Auto Upgrade", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                new Thread(new AutoUpgradeProcess(installerFilePath)).start();
                UpgradeConfirmWindow.this.close();
            }
        });
        autoUpgradeBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
        buttonControls.with(skipBtn, autoUpgradeBtn);
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    class AutoUpgradeProcess implements Runnable {
        private String installerFilePath;

        AutoUpgradeProcess(String installerFilePath) {
            this.installerFilePath = installerFilePath;
        }

        @Override
        public void run() {
            if (installerFilePath != null) {
                File installerFile = new File(installerFilePath);
                if (installerFile.exists()) {
                    ServerInstance.getInstance().preUpgrade();
                    String locUrl = SiteConfiguration.getSiteUrl(AppContext.getSubDomain()) + "it/upgrade";
                    Page.getCurrent().setLocation(locUrl);
                    ServerInstance.getInstance().upgrade(installerFile);
                    return;
                }
            } else {
                throw new IgnoreException("Can not upgrade MyCollab");
            }
        }
    }
}
