package com.mycollab.server;

import com.mycollab.core.utils.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.io.File;
import java.net.URI;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
public class DefaultServerRunner {
    public static void main(String[] args) {
        if (!checkConfigFileExist()) {
            openDefaultWebBrowserForInstallation();
        } else {
            ProductionMyCollabServer.main(args);
        }
    }

    private static boolean checkConfigFileExist() {
        File confFolder = FileUtils.getDesireFile(FileUtils.getUserFolder(), "conf", "src/main/conf");
        return confFolder != null && new File(confFolder, "mycollab.properties").exists();
    }

    private static void openDefaultWebBrowserForInstallation() {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(new URI("http://localhost:" + 8080));
            } catch (Exception e) {
                //do nothing, while user can install MyCollab on the remote server
            }
        }
    }
}
