package com.esofthead.mycollab.ondemand.module.file.view;

import java.util.Locale;

import org.infinispan.commons.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.common.MyCollabSession;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.vaadin.server.VaadinService;
import com.vaadin.server.WrappedHttpSession;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
public final class DropBoxOAuthWindow extends DefaultCloudDriveOAuthWindow {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(DropBoxOAuthWindow.class);

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
		LOG.debug("redirect URL : " + redirectUri);
		WrappedHttpSession wrappedSession = (WrappedHttpSession) VaadinService
				.getCurrentRequest().getWrappedSession();

		String sessionKey = "dropbox-auth-csrf-token";
		DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(
				wrappedSession.getHttpSession(), sessionKey);
		String appId = MyCollabSession.getSessionId();

		DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo,
				redirectUri, csrfTokenStore);
		String authUrl = webAuth.start(appId);

		BasicCache<String, Object> cache = LocalCacheManager.getCache(appId);
		cache.put(sessionKey, csrfTokenStore.get());

		return authUrl;
	}

	@Override
	protected String getStorageName() {
		return StorageNames.DROPBOX;
	}
}
