package com.esofthead.mycollab.shell.view;

import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.DesktopApplication;
import com.hp.gagawa.java.elements.A;
import com.hp.gagawa.java.elements.Div;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import org.apache.commons.io.FileUtils;
import org.vaadin.maddon.layouts.MHorizontalLayout;
import org.vaadin.maddon.layouts.MVerticalLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class UpdateVersionConfirmWindow extends Window {
    private static String headerTemplate = "There is the new MyCollab version %s . For the " +
            "enhancements and security purpose, you should upgrade to the latest version";

    public UpdateVersionConfirmWindow(Properties props) {
        super("There is the new MyCollab update");
        this.setModal(true);
        this.setResizable(false);
        center();
        this.setWidth("600px");

        MVerticalLayout content = new MVerticalLayout();
        this.setContent(content);

        Div titleDiv = new Div().appendText(String.format(headerTemplate, props.getProperty("version"))).setStyle("font-weight:bold");
        content.with(new Label(titleDiv.write(), ContentMode.HTML));

        Div manualInstallLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;Manual install: ")
                .appendChild(new A(props.getProperty("downloadLink"), "_blank")
                        .appendText("Download link"));
        content.with(new Label(manualInstallLink.write(), ContentMode.HTML));

        Div releaseNoteLink = new Div().appendText("&nbsp;&nbsp;&nbsp;&nbsp;Release Notes: ")
                .appendChild(new A(props.getProperty("releaseNotes"), "_blank")
                        .appendText("Link"));
        content.with(new Label(releaseNoteLink.write(), ContentMode.HTML));

        MHorizontalLayout buttonControls = new MHorizontalLayout().withMargin(true);
        Button skipBtn = new Button("Skip", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UpdateVersionConfirmWindow.this.close();
            }
        });
        skipBtn.addStyleName(UIConstants.THEME_GRAY_LINK);

        Button autoUpgradeBtn = new Button("Auto Upgrade", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                UI.getCurrent().setPollInterval(1000);
                new Thread(new AutoUpgradeProcess()).start();
                UpdateVersionConfirmWindow.this.close();
            }
        });
        autoUpgradeBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
        buttonControls.with(skipBtn, autoUpgradeBtn);
        content.with(buttonControls).withAlign(buttonControls, Alignment.MIDDLE_RIGHT);
    }

    class AutoUpgradeProcess implements Runnable {
        @Override
        public void run() {
            try {
                File tmpFile = File.createTempFile("mycollab", "zip");
                URL url = new URL("http://sourceforge.net/projects/mycollab/files/MyCollab_5.0.3/MyCollab-MacOS-5.0.3.zip/download");
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
                int responseCode = httpConn.getResponseCode();

                // always check HTTP response code first
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    String disposition = httpConn.getHeaderField("Content-Disposition");
                    String contentType = httpConn.getContentType();
                    int contentLength = httpConn.getContentLength();

                    System.out.println("Content-Type = " + contentType);
                    System.out.println("Content-Disposition = " + disposition);
                    System.out.println("Content-Length = " + contentLength);

                    // opens input stream from the HTTP connection
                    InputStream inputStream = httpConn.getInputStream();

                    // opens an output stream to save into file
                    FileOutputStream outputStream = new FileOutputStream(tmpFile);

                    int bytesRead;
                    byte[] buffer = new byte[1024];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                        System.out.println("Bytes read: " + bytesRead);
                    }

                    outputStream.close();
                    inputStream.close();
                }
                httpConn.disconnect();
            } catch (IOException e) {
                NotificationUtil.showErrorNotification("Can not download the latest MyCollab distribution. You could try again or install MyCollab manually");
            } finally {
                UI.getCurrent().setPollInterval(-1);
            }

        }
    }

    class DownloadProgressWindow extends Window {
        private long contentLength;
        

        DownloadProgressWindow(long contentLength) {
            super("Downloading ...");
            this.contentLength = contentLength;
            this.setResizable(false);
            this.setModal(false);
            this.setWidth("600px");

            MVerticalLayout content = new MVerticalLayout();
            this.setContent(content);
        }
    }

    public static void main(String[] args) throws Exception {
        File tmpFile = File.createTempFile("mycollab", "zip");
        FileUtils.copyURLToFile(new URL("http://sourceforge.net/projects/mycollab/files/MyCollab_5.0.3/README.md/download"), tmpFile);
        System.out.println("File: " + tmpFile.getAbsolutePath());
    }
}
