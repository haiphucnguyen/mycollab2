package com.mycollab.ondemand.module.file.view;

import com.google.common.eventbus.Subscribe;
import com.mycollab.common.i18n.ErrorI18nEnum;
import com.mycollab.common.i18n.GenericI18Enum;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.BeanUtility;
import com.mycollab.vaadin.ApplicationEventListener;
import com.mycollab.vaadin.EventBusFactory;
import com.mycollab.module.ecm.domain.ExternalDrive;
import com.mycollab.module.ecm.service.ExternalDriveService;
import com.mycollab.module.file.CloudDriveInfo;
import com.mycollab.module.file.event.FileEvent;
import com.mycollab.ondemand.module.file.event.CloudDriveOAuthCallbackEvent;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.UserUIContext;
import com.mycollab.vaadin.AsyncInvoker;
import com.mycollab.vaadin.ui.NotificationUtil;
import com.mycollab.vaadin.web.ui.WebThemes;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.button.MButton;
import org.vaadin.viritin.layouts.MHorizontalLayout;
import org.vaadin.viritin.layouts.MVerticalLayout;
import org.vaadin.viritin.layouts.MWindow;

import java.util.GregorianCalendar;

/**
 * @author MyCollab Ltd
 * @since 5.1.1
 */
public abstract class DefaultCloudDriveOAuthWindow extends MWindow {
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = LoggerFactory.getLogger(DefaultCloudDriveOAuthWindow.class);

    private TextField folderName;
    private CloudDriveInfo cloudDriveInfo;

    private VerticalLayout messageBox;

    private ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo> listener;

    public DefaultCloudDriveOAuthWindow() {
        this.withWidth("420px").withResizable(false).withModal(true).withCenter();
        this.setCaption(windowTitle());
        constructBody();
        registerListeners();
    }

    private void registerListeners() {
        listener = new ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo>() {

            @Subscribe
            @Override
            public void handle(CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo event) {
                cloudDriveInfo = event.getData();
                LOG.debug("Receive cloud drive info: " + BeanUtility.printBeanObj(cloudDriveInfo));
                AsyncInvoker.access(getUI(), new AsyncInvoker.PageCommand() {
                    @Override
                    public void run() {
                        DefaultCloudDriveOAuthWindow.this.setHeight("210px");
                        messageBox.removeAllComponents();
                        messageBox.addComponent(new Label("Access token is retrieved"));
                    }
                });
            }
        };
        EventBusFactory.getInstance().register(listener);
    }

    @Override
    public void close() {
        if (listener != null) {
            EventBusFactory.getInstance().unregister(listener);
        }
        super.close();
    }

    private void constructBody() {
        MVerticalLayout mainLayout = new MVerticalLayout().withMargin(new MarginInfo(true, true, false, true)).withFullWidth();

        messageBox = new VerticalLayout();
        messageBox.setSpacing(true);
        mainLayout.addComponent(messageBox);

        Label messageInfoLbl = new Label("You can connect Cloud Drives (Google Drive, Dropbox) to the MyCollab. They will be showed in 'My Documents' folder and you can do everything like in one place.");
        messageBox.addComponent(messageInfoLbl);

        Button btnLogin = new Button("Login (" + getStorageName() + ")");
        BrowserWindowOpener windowOpener = oauthWindowOpener();
        windowOpener.extend(btnLogin);

        btnLogin.addStyleName(WebThemes.BUTTON_ACTION);
        messageBox.addComponent(btnLogin);
        messageBox.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);

        mainLayout.addComponent(new Label(UserUIContext.getMessage(GenericI18Enum.FORM_NAME)));

        HorizontalLayout folderNameLayout = new HorizontalLayout();
        folderNameLayout.setSpacing(false);

        Button dropboxIconBtn = new Button();
        dropboxIconBtn.setIcon(FontAwesome.DROPBOX);
        folderNameLayout.addComponent(dropboxIconBtn);

        folderName = new TextField();
        folderName.setValue("Dropbox Directory");
        folderName.setWidth("340px");
        folderNameLayout.addComponent(folderName);
        mainLayout.addComponent(folderNameLayout);

        final VerticalLayout dropboxVerticalLayout = new VerticalLayout();
        Image iconEmbedded = new Image();
        iconEmbedded.setSource(FontAwesome.DROPBOX);
        dropboxVerticalLayout.addComponent(iconEmbedded);
        dropboxVerticalLayout.setComponentAlignment(iconEmbedded, Alignment.MIDDLE_CENTER);

        MHorizontalLayout controllGroupBtn = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false, false)).withHeight("50px");

        MButton doneBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_OK), clickEvent -> {
            try {
                String name = folderName.getValue().trim();
                if (StringUtils.isBlank(name)) {
                    NotificationUtil.showWarningNotification(UserUIContext.getMessage(ErrorI18nEnum.FIELD_MUST_NOT_NULL,
                            UserUIContext.getMessage(GenericI18Enum.FORM_NAME)));
                    return;
                }
                ExternalDrive externalDrive = new ExternalDrive();
                externalDrive.setAccesstoken(cloudDriveInfo.getAccessToken());
                externalDrive.setCreatedtime(new GregorianCalendar().getTime());
                externalDrive.setFoldername(name);
                externalDrive.setOwner(UserUIContext.getUsername());
                externalDrive.setStoragename(getStorageName());

                ExternalDriveService service = AppContextUtil.getSpringBean(ExternalDriveService.class);
                service.saveWithSession(externalDrive, UserUIContext.getUsername());
                EventBusFactory.getInstance().post(new FileEvent.ExternalDriveConnectedEvent
                        (DefaultCloudDriveOAuthWindow.this, externalDrive));
            } catch (Exception e) {
                throw new MyCollabException(e);
            }
            close();
        }).withStyleName(WebThemes.BUTTON_ACTION);
        controllGroupBtn.addComponent(doneBtn);

        MButton cancelBtn = new MButton(UserUIContext.getMessage(GenericI18Enum.BUTTON_CANCEL), clickEvent -> close())
                .withStyleName(WebThemes.BUTTON_OPTION);
        controllGroupBtn.addComponent(cancelBtn);

        mainLayout.addComponent(controllGroupBtn);
        mainLayout.setComponentAlignment(controllGroupBtn, Alignment.MIDDLE_CENTER);
        this.setContent(mainLayout);
    }

    public String getCloudDriveFolderName() {
        return folderName.getValue().trim();
    }

    protected abstract BrowserWindowOpener oauthWindowOpener();

    protected abstract String getStorageName();

    protected abstract String windowTitle();
}
