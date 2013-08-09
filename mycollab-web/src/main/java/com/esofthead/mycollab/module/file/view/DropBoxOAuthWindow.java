package com.esofthead.mycollab.module.file.view;

import java.util.GregorianCalendar;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.domain.ExternalDrive;
import com.esofthead.mycollab.module.ecm.service.ExternalDriveService;
import com.esofthead.mycollab.vaadin.ui.UIConstants;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.mycollab.web.MyCollabResource;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.terminal.gwt.server.WebApplicationContext;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public abstract class DropBoxOAuthWindow extends Window {
	private static final long serialVersionUID = 1L;
	private TextField folderName;

	public DropBoxOAuthWindow() {
		super("Connecting account");
		this.setWidth("500px");
		this.setName("Dropbox");
		this.center();
		constructBody();
	}

	private void constructBody() {
		final VerticalLayout mainLayout = new VerticalLayout();
		mainLayout.setWidth("100%");
		mainLayout.setSpacing(true);

		Label messageInfoLal = new Label(
				"You can connect the following account to Mycollab-Documents. They will be showed in My-Documents' folder and you can do everything like in one place.");
		mainLayout.addComponent(messageInfoLal);

		final VerticalLayout dropboxVerticalLayout = new VerticalLayout();
		Embedded iconEmbedded = new Embedded();
		iconEmbedded.setSource(MyCollabResource
				.newResource("icons/dropbox_icon_256px.png"));
		dropboxVerticalLayout.addComponent(iconEmbedded);
		dropboxVerticalLayout.setComponentAlignment(iconEmbedded,
				Alignment.MIDDLE_CENTER);

		final Button dropboxBtn = new Button("Dropbox",
				new Button.ClickListener() {
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						String redirectUri = "http://localhost:8080/mycollab-web/drive/dropboxAuth";

						java.util.Locale locale = new Locale(Locale.US
								.getLanguage(), Locale.US.getCountry());
						String userLocale = locale.toString();
						DbxRequestConfig requestConfig = new DbxRequestConfig(
								"text-edit/0.1", userLocale);
						DbxAppInfo appInfo = new DbxAppInfo("y43ga49m30dfu02",
								"rheskqqb6f8fo6a");
						System.out.println("redirect URL : " + redirectUri);
						WebApplicationContext webContext = (WebApplicationContext) AppContext
								.getApplication().getContext();

						HttpSession session = webContext.getHttpSession();
						String sessionKey = "dropbox-auth-csrf-token";
						DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(
								session, sessionKey);
						DbxWebAuth webAuth = new DbxWebAuth(requestConfig,
								appInfo, redirectUri, csrfTokenStore);
						final String authorizeUrl = webAuth.start();

						open(new ExternalResource(authorizeUrl), "new Window",
								true);
					}
				});
		dropboxBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		dropboxVerticalLayout.addComponent(dropboxBtn);
		dropboxVerticalLayout.setComponentAlignment(dropboxBtn,
				Alignment.MIDDLE_CENTER);

		folderName = new TextField("Folder Name ");
		folderName.setWidth("150px");
		dropboxVerticalLayout.addComponent(folderName);
		dropboxVerticalLayout.setComponentAlignment(folderName,
				Alignment.MIDDLE_CENTER);

		Button doneBtn = new Button("Done", new Button.ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					String name = folderName.getValue().toString().trim();
					if (name.equals("")) {
						getWindow()
								.showNotification("Please Enter folder name");
						return;
					}
					ExternalDrive dropboxDrive = new ExternalDrive();
					dropboxDrive.setAccesstoken(AppContext
							.getCurrentAccessToken());
					dropboxDrive.setCreatedtime(new GregorianCalendar(2100, 1,
							1).getTime());
					dropboxDrive.setFoldername(name);
					dropboxDrive.setOwner(AppContext.getUsername());
					dropboxDrive.setStoragename(StorageNames.DROPBOX);

					ExternalDriveService service = AppContext
							.getSpringBean(ExternalDriveService.class);
					service.saveWithSession(dropboxDrive,
							AppContext.getUsername());
					handleReloadAfterConnectDropbox();
				} catch (Exception e) {
					throw new MyCollabException(e);
				}
				DropBoxOAuthWindow.this.close();
			}
		});
		doneBtn.addStyleName(UIConstants.THEME_BLUE_LINK);
		dropboxVerticalLayout.addComponent(doneBtn);
		dropboxVerticalLayout.setComponentAlignment(doneBtn,
				Alignment.MIDDLE_CENTER);

		mainLayout.addComponent(dropboxVerticalLayout);
		this.addComponent(mainLayout);
	}

	public String getDropBoxRootFolderName() {
		return folderName.getValue().toString().trim();
	}

	protected abstract void handleReloadAfterConnectDropbox();
}
