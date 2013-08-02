package com.esofthead.mycollab.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class MyCollabGoogleCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		GoogleOauthHelper helper = new GoogleOauthHelper();
		if (request.getParameter("code") != null
				&& request.getParameter("state").equals("google")) {

			/*
			 * Executes after google redirects to the callback url. Please note
			 * that the state request parameter is for convenience to
			 * differentiate between authentication methods (ex. facebook oauth,
			 * google oauth, twitter, in-house).
			 * 
			 * GoogleAuthHelper()#getUserInfoJson(String) method returns a
			 * String containing the json representation of the authenticated
			 * user's information. At this point you should parse and persist
			 * the info.
			 */
			Gson gSon = new Gson();
			GoogleUser user = gSon.fromJson(
					helper.getUserInfoJson(request.getParameter("code")),
					GoogleUser.class);
			System.out.println(user.getEmail() + " " + user.getId() + " "
					+ user.getName());
		}
	}

}
