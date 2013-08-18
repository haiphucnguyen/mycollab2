package com.esofthead.mycollab.module.file.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.infinispan.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.module.file.events.CloudDriveOAuthCallbackEvent;
import com.esofthead.mycollab.module.file.view.MyCollabDbxSessionStore;
import com.esofthead.mycollab.vaadin.events.EventBus;

@Component("dropboxAuthServlet")
public class AnnotatedDropboxAuthServlet implements HttpRequestHandler {
	private static Logger log = LoggerFactory
			.getLogger(AnnotatedDropboxAuthServlet.class);

	private DbxWebAuth getWebAuth(final HttpServletRequest request) {
		java.util.Locale locale = new Locale(Locale.US.getLanguage(),
				Locale.US.getCountry());
		String userLocale = locale.toString();
		DbxRequestConfig requestConfig = new DbxRequestConfig("text-edit/0.1",
				userLocale);
		DbxAppInfo appInfo = new DbxAppInfo("y43ga49m30dfu02",
				"rheskqqb6f8fo6a");
		String redirectUri = request.getRequestURL().toString();
		HttpSession session = request.getSession(true);
		String sessionKey = "dropbox-auth-csrf-token";
		DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session,
				sessionKey);
		String stateParam = request.getParameter("state");
		if (stateParam == null || stateParam.equals("")) {
			throw new MyCollabException(
					"Can not get state parameter successfully, Invalid request");
		}

		int index = stateParam.indexOf("|");
		if (index < 0) {
			throw new MyCollabException("Invalid parameter request "
					+ stateParam);
		}

		String oldSessionId = stateParam.substring(index + 1);
		BasicCache<String, Object> cache = LocalCacheManager
				.getCache(oldSessionId);
		Object csrfTokenVal = cache.get(sessionKey);

		if (csrfTokenVal == null) {
			throw new MyCollabException(
					"Invalid parameter request, can not define csrfToken");
		} else {
			csrfTokenStore.set((String) csrfTokenVal);
		}

		DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo,
				redirectUri, csrfTokenStore);
		return webAuth;
	}

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		DbxAuthFinish authFinish = null;
		try {
			authFinish = getWebAuth(request).finish(request.getParameterMap());
		} catch (Exception e) {
			log.error("Authorize dropbox request failed", e);
			PrintWriter out = response.getWriter();
			out.println("<html>"
					+ "<body></body>"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>"
					+ "<script>");
			out.println("$(document).ready(function(){" + "window.close();"
					+ "});");
			out.println("</script>");
			out.println("</html>");
			return;
		}

		String accessToken = authFinish.accessToken;
		String sessionId = authFinish.urlState;
		if (sessionId.startsWith("|")) {
			sessionId = sessionId.substring(1);
		}

		// Store accessToken ...
		CloudDriveInfo cloudDriveInfo = new CloudDriveInfo(
				StorageNames.DROPBOX, accessToken);

		EventBus eventBus = EventBus.getInstanceSession(sessionId);
		if (eventBus != null) {
			eventBus.fireEvent(new CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo(
					AnnotatedDropboxAuthServlet.this, cloudDriveInfo));
		} else {
			log.error(
					"Can not find eventbus for session id {}, this session is not initialized by user yet",
					sessionId);
		}

		// response script close current window
		PrintWriter out = response.getWriter();
		out.println("<html>"
				+ "<body></body>"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>"
				+ "<script>");
		out.println("$(document).ready(function(){" + "window.close();" + "});");
		out.println("</script>");
		out.println("</html>");
	}

}
