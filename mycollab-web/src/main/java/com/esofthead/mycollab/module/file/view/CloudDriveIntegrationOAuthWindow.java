package com.esofthead.mycollab.module.file.view;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.common.localization.GenericI18Enum;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.LocalizationHelper;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class CloudDriveIntegrationOAuthWindow extends Window {
	private static final long serialVersionUID = 1L;

	private TextField folderName;

	private static Logger log = LoggerFactory
			.getLogger(CloudDriveIntegrationOAuthWindow.class);

	public CloudDriveIntegrationOAuthWindow(String title) {
		super(title);
		this.setWidth("420px");
		this.setHeight("280px");
		this.center();
		constructBody();
	}

	private void constructBody() {
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);

		Label messageInfoLal = new Label(
				"You can connect Cloud Drives (Google Drive, Dropbox) to Mycollab Documents. They will be showed in My-Documents' folder and you can do everything like in one place.");
		mainLayout.addComponent(messageInfoLal);

		Button btnLogin = new Button("Login(Dropbox)",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						String authorizeUrl = buildAuthUrl();
						open(new ExternalResource(authorizeUrl), "New Window",
								true);
					}
				});
		btnLogin.addStyleName(UIConstants.THEME_BLUE_LINK);
		mainLayout.addComponent(btnLogin);
		mainLayout.setComponentAlignment(btnLogin, Alignment.MIDDLE_CENTER);

		Label title = new Label("Folder Title");

		mainLayout.addComponent(title);

		HorizontalLayout folderNameLayout = new HorizontalLayout();
		folderNameLayout.setSpacing(false);

		Button dropboxIconBtn = new Button();
		dropboxIconBtn.setStyleName("dropbox-icon-button");
		dropboxIconBtn.setIcon(MyCollabResource
				.newResource("icons/16/ecm/dropbox_icon_16px.png"));
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
		controllGroupBtn.setMargin(true, false, false, false);

		Button doneBtn = new Button(
				LocalizationHelper.getMessage(GenericI18Enum.BUTTON_OK_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						try {
							String name = folderName.getValue().toString()
									.trim();
							if (name.equals("")) {
								getWindow().showNotification(
										"Please Enter Folder Name");
								return;
							}
							ExternalDrive externalDrive = new ExternalDrive();
							// dropboxDrive.setAccesstoken("");
							externalDrive.setCreatedtime(new Date());
							externalDrive.setFoldername(name);
							externalDrive.setOwner(AppContext.getUsername());
							externalDrive.setStoragename(getStorageName());

							ExternalDriveService service = AppContext
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
		doneBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controllGroupBtn.addComponent(doneBtn);

		Button cancleBtn = new Button(
				LocalizationHelper
						.getMessage(GenericI18Enum.BUTTON_CANCEL_LABEL),
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						CloudDriveIntegrationOAuthWindow.this.close();
					}
				});
		cancleBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		controllGroupBtn.addComponent(cancleBtn);

		mainLayout.addComponent(controllGroupBtn);
		mainLayout.setComponentAlignment(controllGroupBtn,
				Alignment.MIDDLE_CENTER);
		this.addComponent(mainLayout);
	}

	public String getCloudDriveFolderName() {
		return folderName.getValue().toString().trim();
	}

	protected abstract String buildAuthUrl();

	protected abstract String getStorageName();

	protected abstract void addExternalDrive(ExternalDrive externalDrive);
}
