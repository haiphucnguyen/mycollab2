package com.esofthead.mycollab.module.file.servlet;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxAuthFinish;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxSessionStore;
import com.dropbox.core.DbxStandardSessionStore;
import com.dropbox.core.DbxWebAuth;
import com.esofthead.mycollab.core.MyCollabException;

@Component("dropboxAuthServlet")
public class AnnotatedDropboxAuthServlet implements HttpRequestHandler {

	private DbxWebAuth getWebAuth(final HttpServletRequest request) {
		java.util.Locale locale = new Locale(Locale.US.getLanguage(),
				Locale.US.getCountry());
		String userLocale = locale.toString();
		DbxRequestConfig requestConfig = new DbxRequestConfig("text-edit/0.1",
				userLocale);
		DbxAppInfo appInfo = new DbxAppInfo("y43ga49m30dfu02",
				"rheskqqb6f8fo6a");
		String redirectUri = request.getRequestURL().toString();
		System.out.println("redirect URL : " + redirectUri);
		HttpSession session = request.getSession(true);
		String sessionKey = "dropbox-auth-csrf-token";
		DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session,
				sessionKey);
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
			throw new MyCollabException(e);
		}
		String accessToken = authFinish.accessToken;
		System.out.print("Access TOken key : " + accessToken);
	}

}
