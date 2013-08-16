package com.esofthead.mycollab.module.file.view;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.web.AppContext;
import com.vaadin.terminal.gwt.server.WebApplicationContext;

public abstract class DropBoxOAuthWindow extends
		CloudDriveIntegrationOAuthWindow {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(DropBoxOAuthWindow.class);

	public DropBoxOAuthWindow() {
		super("Add Dropbox");
	}

	@Override
	protected String buildAuthUrl() {
		String redirectUri = SiteConfiguration.getDropboxCallbackUrl();

		java.util.Locale locale = new Locale(Locale.US.getLanguage(),
				Locale.US.getCountry());
		String userLocale = locale.toString();
		DbxRequestConfig requestConfig = new DbxRequestConfig("text-edit/0.1",
				userLocale);
		DbxAppInfo appInfo = new DbxAppInfo("y43ga49m30dfu02",
				"rheskqqb6f8fo6a");
		log.debug("redirect URL : " + redirectUri);
		WebApplicationContext webContext = (WebApplicationContext) AppContext
				.getApplication().getContext();

		HttpSession session = webContext.getHttpSession();
		String sessionKey = "dropbox-auth-csrf-token";
		DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session,
				sessionKey);
		DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo,
				redirectUri, csrfTokenStore);
		return webAuth.start();
	}

	@Override
	protected String getStorageName() {
		return StorageNames.DROPBOX;
	}
}
