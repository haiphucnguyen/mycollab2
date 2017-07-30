package com.mycollab.server;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.awt.*;
import java.io.File;
import java.net.URI;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, FreeMarkerAutoConfiguration.class,
        FlywayAutoConfiguration.class})
@ComponentScan(basePackages = {"com.mycollab.**.spring, com.mycollab.**.configuration, com.mycollab.**.servlet"})
public class DefaultServerRunner {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(DefaultServerRunner.class);
        if (!checkConfigFileExist()) {
            application.setAdditionalProfiles("setup");
            ConfigurableApplicationContext appContext = application.run(args);
            while (!checkConfigFileExist()) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new MyCollabException(e);
                }
            }
            appContext.stop();
            application.setAdditionalProfiles("production");
            application.run(args);

        } else {
            application.setAdditionalProfiles("production");
            application.run(args);
        }
    }

    private static boolean checkConfigFileExist() {
        File confFolder = FileUtils.getDesireFile(FileUtils.getUserFolder(), "config", "src/main/config");
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
