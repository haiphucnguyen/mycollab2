package com.esofthead.mycollab.oauth;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Profile;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.util.SocialAuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esofthead.mycollab.core.MyCollabException;

public class ExternalAuthCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory
			.getLogger(ExternalAuthCallbackServlet.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("google callback");

		// get the auth provider manager from session
		SocialAuthManager manager = (SocialAuthManager) request.getSession()
				.getAttribute("authManager");
		try {
			// call connect method of manager which returns the provider object.
			// Pass request parameter map while calling connect method.
			Map<String, String> paramsMap = SocialAuthUtil
					.getRequestParametersMap(request);
			AuthProvider provider = manager.connect(paramsMap);

			// get profile
			Profile p = provider.getUserProfile();

			// you can obtain profile information
			log.debug(p.getFirstName() + "--" + p.getLastName() + "--"
					+ p.getEmail());
		} catch (Exception e) {
			throw new MyCollabException(e);
		}
	}
}
