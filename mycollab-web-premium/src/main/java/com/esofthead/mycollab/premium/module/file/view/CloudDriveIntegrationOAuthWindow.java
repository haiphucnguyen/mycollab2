package com.esofthead.mycollab.premium.module.file.view;

import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.artur.icepush.ICEPush;

import com.esofthead.mycollab.common.i18n.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.eventmanager.ApplicationEvent;
import com.esofthead.mycollab.eventmanager.ApplicationEventListener;
import com.esofthead.mycollab.eventmanager.EventBus;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.module.file.events.CloudDriveOAuthCallbackEvent;
import com.esofthead.mycollab.module.file.events.CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.mycollab.vaadin.AppContext;
import com.esofthead.mycollab.vaadin.ui.MyCollabResource;
import com.esofthead.mycollab.vaadin.ui.NotificationUtil;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.vaadin.server.BrowserWindowOpener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public abstract class CloudDriveIntegrationOAuthWindow extends Window {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(CloudDriveIntegrationOAuthWindow.class);

	private TextField folderName;
	private CloudDriveInfo cloudDriveInfo;

	private ICEPush pusher = new ICEPush();

	private VerticalLayout mainLayout;
	private VerticalLayout messageBox;

	private ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo> listener;

	public CloudDriveIntegrationOAuthWindow(String title) {
		super(title);
		this.setWidth("420px");
		this.setResizable(false);
		this.center();
		constructBody();
		registerListeners();
	}

	private void registerListeners() {
		pusher.extend(UI.getCurrent());

		listener = new ApplicationEventListener<CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo>() {
			private static final long serialVersionUID = 1L;

			@Override
			public void handle(ReceiveCloudDriveInfo event) {
				cloudDriveInfo = (CloudDriveInfo) event.getData();

				log.debug("Receive cloud drive info: "
						+ BeanUtility.printBeanObj(cloudDriveInfo));
				CloudDriveIntegrationOAuthWindow.this.setHeight("210px");

				messageBox.removeAllComponents();
				messageBox.addComponent(new Label("Access token retrieved"));
				// Push changes to client
				pusher.push();
			}

			@Override
			public Class<? extends ApplicationEvent> getEventType() {
				return CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo.class;
			}
		};
		EventBus.getInstance().addListener(listener);
	}

	@Override
	public void close() {
		if (listener != null) {
			EventBus.getInstance().removeListener(listener);
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
				"You can connect Cloud Drives (Google Drive, Dropbox) to Mycollab Documents. They will be showed in My-Documents' folder and you can do everything like in one place.");
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
		dropboxIconBtn.setStyleName("dropbox-icon-button");
		dropboxIconBtn.setIcon(MyCollabResource
				.newResource("icons/12/ecm/dropbox.png"));
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

		HorizontalLayout controllGroupBtn = new HorizontalLayout();
		controllGroupBtn.setSpacing(true);
		controllGroupBtn.setHeight("50px");
		controllGroupBtn.setMargin(new MarginInfo(true, false, false, false));

		Button doneBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_OK_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							String name = folderName.getValue().toString()
									.trim();
							if (name.equals("")) {
								NotificationUtil
										.showWarningNotification("Please enter folder name");
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
							addExternalDrive(externalDrive);
						} catch (Exception e) {
							throw new MyCollabException(e);
						}
						CloudDriveIntegrationOAuthWindow.this.close();
					}
				});
		doneBtn.addStyleName(UIConstants.THEME_GREEN_LINK);
		controllGroupBtn.addComponent(doneBtn);

		Button cancelBtn = new Button(
				AppContext.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CloudDriveIntegrationOAuthWindow.this.close();
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
		return folderName.getValue().toString().trim();
	}

	protected abstract String buildAuthUrl();

	protected abstract String getStorageName();

	protected abstract void addExternalDrive(ExternalDrive externalDrive);
}
