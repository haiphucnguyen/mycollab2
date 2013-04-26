package com.esofthead.mycollab.oauth;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

public class OAuthCodeServlet extends AbstractAuthorizationCodeServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException,
			IOException {
		return new GoogleAuthorizationCodeFlow.Builder(
				new NetHttpTransport(),
				new JacksonFactory(),
				"1095048585699-htc9so8ta1aamt3pdmq50lvoos07pjbi.apps.googleusercontent.com",
				"PPAPCdK8nH6H-o4nKwIZX4dv", Arrays.asList(
						" https://www.googleapis.com/auth/userinfo.email",
						"https://www.googleapis.com/auth/userinfo.profile"))
				.build();
	}

	@Override
	protected String getRedirectUri(HttpServletRequest req)
			throws ServletException, IOException {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException,
			IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
