package com.esofthead.mycollab.ondemand.module.file.view;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.common.ui.components.AbstractCloudDriveOAuthWindow;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBusFactory;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.esb.CloudDriveOAuthCallbackEvent;
import com.esofthead.mycollab.module.ecm.esb.CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.google.common.eventbus.Subscribe;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.maddon.layouts.MHorizontalLayout;

import java.util.GregorianCalendar;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class DefaultCloudDriveOAuthWindow extends
		AbstractCloudDriveOAuthWindow {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultCloudDriveOAuthWindow.class);

	private TextField folderName;
	private CloudDriveInfo cloudDriveInfo;

	private VerticalLayout mainLayout;
	private VerticalLayout messageBox;

	private ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo> listener;

	public DefaultCloudDriveOAuthWindow() {
		this.setWidth("420px");
		this.setResizable(false);
		this.center();
		constructBody();
		registerListeners();
	}

	private void registerListeners() {

		final UI ui = UI.getCurrent();

		listener = new ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo>() {
			private static final long serialVersionUID = 1L;

			@Subscribe
			@Override
			public void handle(ReceiveCloudDriveInfo event) {
				ui.setPollInterval(1000);
				cloudDriveInfo = (CloudDriveInfo) event.getData();

				LOG.debug("Receive cloud drive info: "
						+ BeanUtility.printBeanObj(cloudDriveInfo));

				ui.access(new Runnable() {
					@Override
					public void run() {
						DefaultCloudDriveOAuthWindow.this.setHeight("210px");
						messageBox.removeAllComponents();
						messageBox.addComponent(new Label(
								"Access token is retrieved"));
						ui.setPollInterval(-1);
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
		mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setSpacing(true);
		mainLayout.setMargin(new MarginInfo(true, true, false, true));

		messageBox = new VerticalLayout();
		messageBox.setSpacing(true);
		mainLayout.addComponent(messageBox);

		Label messageInfoLbl = new Label(
				"You can connect Cloud Drives (Google Drive, Dropbox) to the MyCollab. They will be showed in 'My Documents' folder and you can do everything like in one place.");
		messageBox.addComponent(messageInfoLbl);

		Button btnLogin = new Button("Login (" + getStorageName() + ")");
		BrowserWindowOpener windowOpenner = new BrowserWindowOpener(
				new ExternalResource(buildAuthUrl()));
		windowOpenner.extend(btnLogin);

		btnLogin.addStyleName(UIConstants.THEME_BLUE_LINK);
		messageBox.addComponent(btnLogin);
		messageBox.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);

		Label title = new Label("Folder Title");

		mainLayout.addComponent(title);

		HorizontalLayout folderNameLayout = new HorizontalLayout();
		folderNameLayout.setSpacing(false);

		Button dropboxIconBtn = new Button();
		dropboxIconBtn.setIcon(FontAwesome.DROPBOX);
		folderNameLayout.addComponent(dropboxIconBtn);

		folderName = new TextField();
		folderName.setValue("Dropbox Directory");
		folderName.setWidth("340px");
		folderName.addStyleName("no-border-radius-left");
		folderNameLayout.addComponent(folderName);
		mainLayout.addComponent(folderNameLayout);

		final VerticalLayout dropboxVerticalLayout = new VerticalLayout();
		Embedded iconEmbedded = new Embedded();
		iconEmbedded.setSource(MyCollabResource
				.newResource("icons/dropbox_icon_256px.png"));
		dropboxVerticalLayout.addComponent(iconEmbedded);
		dropboxVerticalLayout.setComponentAlignment(iconEmbedded,
				Alignment.MIDDLE_CENTER);

		MHorizontalLayout controllGroupBtn = new MHorizontalLayout().withMargin(new MarginInfo(true, false, false,
                false)).withHeight("50px");

		Button doneBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_OK),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							String name = folderName.getValue().trim();
							if (StringUtils.isBlank(name)) {
								NotificationUtil
										.showWarningNotification("Please enter the folder name");
								return;
							}
							ExternalDrive externalDrive = new ExternalDrive();
							externalDrive.setAccesstoken(cloudDriveInfo
									.getAccessToken());
							externalDrive
									.setCreatedtime(new GregorianCalendar()
											.getTime());
							externalDrive.setFoldername(name);
							externalDrive.setOwner(AppContext.getUsername());
							externalDrive.setStoragename(getStorageName());

							ExternalDriveService service = ApplicationContextUtil
									.getSpringBean(ExternalDriveService.class);
							service.saveWithSession(externalDrive,
									AppContext.getUsername());
							DefaultCloudDriveOAuthWindow.this
									.fireEvent(new ExternalDriveConnectedEvent(
											DefaultCloudDriveOAuthWindow.this,
											externalDrive));
						} catch (Exception e) {
							throw new MyCollabException(e);
						}
						DefaultCloudDriveOAuthWindow.this.close();
					}
				});
		doneBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controllGroupBtn.addComponent(doneBtn);

		Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						DefaultCloudDriveOAuthWindow.this.close();
					}
				});
		cancelBtn.addStyleName(UIConstants.THEME_GRAY_LINK);
		controllGroupBtn.addComponent(cancelBtn);

		mainLayout.addComponent(controllGroupBtn);
		mainLayout.setComponentAlignment(controllGroupBtn,
				Alignment.MIDDLE_CENTER);
		this.setContent(mainLayout);
	}

	public String getCloudDriveFolderName() {
		return folderName.getValue().trim();
	}

	protected abstract String buildAuthUrl();

	protected abstract String getStorageName();
}
