package com.esofthead.mycollab.module.billing.servlet;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("denyUserServletHandler")
public class AnotatedDenyUserServletRequestHandler implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedDenyUserServletRequestHandler.class);

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				pathInfo = UrlEncodeDecoder.decode(pathInfo);
				int accountId = Integer.parseInt(pathInfo.substring(0,
						pathInfo.indexOf("/")));
				pathInfo = pathInfo.substring((accountId + "").length() + 1);

				String username = pathInfo;
				UserService userService = AppContext
						.getSpringBean(UserService.class);
				User curUser = userService.findUserByUserName(username);
				if (curUser == null) {
					// this user no long exist on System page
					PageUserNotExistGenerator.responeUserNotExistPage(response,
							request.getContextPath() + "/");
					return;
				} else {
					if (curUser.getRegisterstatus().equals(
							RegisterStatusConstants.ACTIVE)) {
						// YOu cant deny , Userhas active , go to login Page
						String html = generateRefuseUserDenyActionPage(request
								.getContextPath() + "/");
						PrintWriter out = response.getWriter();
						out.println(html);
						return;
					} else {
						try {
							UserSearchCriteria criteria = new UserSearchCriteria();
							criteria.setUsername(new StringSearchField(username));
							criteria.setSaccountid(new NumberSearchField(
									accountId));

							userService.removeUserAccount(username, accountId);

							log.debug("Verify user successfully. Redirect to application page");
							response.sendRedirect(request.getContextPath()
									+ "/");
						} catch (UserInvalidInputException e) {
							log.debug("Redirect user to user invalid page");
							PageNotFoundGenerator.responsePage404(response);
						}
						return;
					}
				}
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	public static class PageUserNotExistGenerator {
		public static void responeUserNotExistPage(
				HttpServletResponse response, String loginURL)
				throws IOException {
			String pageNotFoundTemplate = "templates/page/UserNotExistPage.mt";
			TemplateContext context = new TemplateContext();

			Reader reader;
			try {
				reader = new InputStreamReader(PageNotFoundGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				reader = new InputStreamReader(PageNotFoundGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate));
			}
			context.put("loginURL", loginURL);
			Map<String, String> defaultUrls = new HashMap<String, String>();

			defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
			context.put("defaultUrls", defaultUrls);

			StringWriter writer = new StringWriter();
			TemplateEngine.evaluate(context, writer, "log task", reader);

			String html = writer.toString();
			PrintWriter out = response.getWriter();
			out.println(html);
		}
	}

	private String generateRefuseUserDenyActionPage(String loginURL) {
		String pageNotFoundTemplate = "templates/page/RefuseUserDenyActionPage.mt";
		TemplateContext context = new TemplateContext();

		Reader reader;
		try {
			reader = new InputStreamReader(
					PageNotFoundGenerator.class.getClassLoader()
							.getResourceAsStream(pageNotFoundTemplate), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(PageNotFoundGenerator.class
					.getClassLoader().getResourceAsStream(pageNotFoundTemplate));
		}
		context.put("loginURL", loginURL);
		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);

		return writer.toString();
	}
}
