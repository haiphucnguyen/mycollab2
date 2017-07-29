package com.mycollab.vaadin.web.ui;

import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.common.i18n.ShellI18nEnum;
import com.mycollab.core.IgnoreException;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.ui.ELabel;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.io.File;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UpgradeConfirmWindow extends MWindow {
    private static final Logger LOG = LoggerFactory.getLogger(UpgradeConfirmWindow.class);

    private UI currentUI;
    private String installerFilePath;

    public UpgradeConfirmWindow(final String version, String manualDownloadLink, final String installerFilePath) {
        super(UserUIContext.getMessage(ShellI18nEnum.OPT_NEW_UPGRADE_IS_READY));
        this.withModal(true).withResizable(false).withCenter().withWidth("600px");
        this.installerFilePath = installerFilePath;

        currentUI = UI.getCurrent();

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        Div titleDiv = new Div().appendText(UserUIContext.getMessage(ShellI18nEnum.OPT_REQUEST_UPGRADE, version)).setStyle("font-weight:bold");
        content.with(ELabel.html(titleDiv.write()));

        Div manualInstallLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;" + UserUIContext.getMessage(ShellI18nEnum.OPT_MANUAL_INSTALL) + ": ")
                .appendChild(new A(manualDownloadLink, "_blank")
                        .appendText(UserUIContext.getMessage(ShellI18nEnum.OPT_DOWNLOAD_LINK)));
        content.with(ELabel.html(manualInstallLink.write()));

        Div manualUpgradeHowtoLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;" + UserUIContext.getMessage(ShellI18nEnum.OPT_MANUAL_UPGRADE) + ": ")
                .appendChild(new A("https://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/upgrade-mycollab-automatically/", "_blank").appendText("Link"));
        content.with(new Label(manualUpgradeHowtoLink.write(), ContentMode.HTML));

        Div releaseNoteLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;" + UserUIContext.getMessage(ShellI18nEnum.OPT_RELEASE_NOTES) + ": ")
                .appendChild(new A("https://community.mycollab.com/docs/hosting-mycollab-on-your-own-server/releases/", "_blank").appendText("Link"));
        content.with(new Label(releaseNoteLink.write(), ContentMode.HTML));

        MButton skipBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.ACTION_SKIP), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);

        MButton autoUpgradeBtn = new MButton(UserUIContext.getMessage(ShellI18nEnum.ACTION_AUTO_UPGRADE), clickEvent -> {
            close();
            navigateToWaitingUpgradePage();
        }).withStyleName(WebThemes.BUTTON_ACTION);
        if (installerFilePath == null) {
            autoUpgradeBtn.setEnabled(false);
        }

        MHorizontalLayout buttonControls = new MHorizontalLayout(skipBtn, autoUpgradeBtn).withMargin(true);
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    private void navigateToWaitingUpgradePage() {
        if (installerFilePath != null) {
            final File installerFile = new File(installerFilePath);
            if (installerFile.exists()) {
                new Thread(() -> {
//                    ServerInstance.getInstance().preUpgrade();
                    final String locUrl = MyCollabUI.getSiteUrl() + "it/upgrade";
                    Future<Void> access = currentUI.access(() -> {
                        LOG.info("Redirect to the upgrade page " + locUrl);
                        currentUI.getPage().setLocation(locUrl);
                        currentUI.push();
                    });

                    try {
                        access.get();
                        TimeUnit.SECONDS.sleep(5);
//                        ServerInstance.getInstance().upgrade(installerFile);
                    } catch (Exception e) {
                        LOG.error("Error while upgrade", e);
                    }
                }).start();
            }
        } else {
            throw new IgnoreException("Can not upgrade MyCollab");
        }
    }
}
