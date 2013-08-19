package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.template.velocity.EngineFactory;

@Component("inviteOutsideMemberHandlerServlet")
public class AnotatedInviteOutsideMemberHandlerServlet implements
		HttpRequestHandler {
	private static String OUTSIDE_MEMBER_WELCOME_PAGE = "templates/email/project/memberInvitation/outsideMemberAcceptInvitationPage.mt";

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			pathInfo = pathInfo.substring(1);

			String pathVariables = UrlEncodeDecoder.decode(pathInfo);
			int sAccountId = Integer.parseInt(pathVariables.substring(0,
					pathVariables.indexOf("/")));
			pathVariables = pathVariables
					.substring((sAccountId + "").length() + 1);

			String name = pathVariables
					.substring(0, pathVariables.indexOf("/"));
			pathVariables = pathVariables.substring(name.length() + 1);

			String email = pathVariables.substring(0,
					pathVariables.indexOf("/"));
			pathVariables = pathVariables.substring(email.length() + 1);

			int projectId = Integer.parseInt(pathVariables.substring(0,
					pathVariables.indexOf("/")));
			pathVariables = pathVariables
					.substring((projectId + "").length() + 1);

			int roleId = Integer.parseInt(pathVariables.substring(0,
					pathVariables.indexOf("/")));
			pathVariables = pathVariables.substring((roleId + "").length() + 1);

			String loginURL = pathVariables;

			String handelCreateAccountURL = loginURL
					+ "project/outside/createAccount/";

			String html = generateOutsideMemberAcceptPage(sAccountId, name,
					email, projectId, roleId, loginURL, handelCreateAccountURL);
			PrintWriter out = response.getWriter();
			out.println(html);
		}
	}

	private String generateOutsideMemberAcceptPage(int sAccountId, String name,
			String email, int projectId, int roleId, String loginURL,
			String handelCreateAccountURL) {
		VelocityContext context = new VelocityContext(
				EngineFactory.createContext());

		Reader reader;
		try {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							OUTSIDE_MEMBER_WELCOME_PAGE), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							OUTSIDE_MEMBER_WELCOME_PAGE));
		}
		context.put("name", name);
		context.put("email", email);
		context.put("projectId", projectId);
		context.put("roleId", roleId);
		context.put("loginURL", loginURL);
		context.put("handelCreateAccountURL", handelCreateAccountURL);
		context.put("sAccountId", sAccountId);

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());

		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		EngineFactory.getTemplateEngine().evaluate(context, writer, "log task",
				reader);
		return writer.toString();
	}
}
