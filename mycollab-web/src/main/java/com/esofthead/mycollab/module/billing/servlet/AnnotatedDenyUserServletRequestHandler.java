/**
 * This file is part of mycollab-web.
 *
 * mycollab-web is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-web is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-web.  If not, see <http://www.gnu.org/licenses/>.
 */
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

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.servlet.AnnotatedDenyProjectMemberInvitationServletHandler.FeedBackPageGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.criteria.UserSearchCriteria;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.servlet.GenericServlet;
import com.esofthead.mycollab.spring.ApplicationContextUtil;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

/**
 * 
 * @author MyCollab Ltd.
 * @since 1.0
 * 
 */
@Component("denyUserInviteServlet")
public class AnnotatedDenyUserServletRequestHandler extends GenericServlet {

	private static String USER_DENY_FEEDBACK_TEMPLATE = "templates/page/user/UserDenyInvitationPage.mt";
	private static String USER_HAS_DENIED_PAGE = "templates/page/user/UserDeniedPage.mt";

	private static Logger log = LoggerFactory
			.getLogger(AnnotatedDenyUserServletRequestHandler.class);

	public static class PageUserNotExistGenerator {
		public static void responeUserNotExistPage(
				HttpServletResponse response, String loginURL)
				throws IOException {
			String pageNotFoundTemplate = "templates/page/UserNotExistPage.mt";
			TemplateContext context = new TemplateContext();

			Reader reader;
			try {
				reader = new InputStreamReader(PageUserNotExistGenerator.class
						.getClassLoader().getResourceAsStream(
								pageNotFoundTemplate), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				reader = new InputStreamReader(PageUserNotExistGenerator.class
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

	private String generateRefuseUserDenyActionPage(String loginURL,
			String pageNotFoundTemplate) {
		TemplateContext context = new TemplateContext();

		Reader reader;
		try {
			reader = new InputStreamReader(
					AnnotatedDenyUserServletRequestHandler.class
							.getClassLoader().getResourceAsStream(
									pageNotFoundTemplate), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(
					AnnotatedDenyUserServletRequestHandler.class
							.getClassLoader().getResourceAsStream(
									pageNotFoundTemplate));
		}
		context.put("loginURL", loginURL);
		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);

		return writer.toString();
	}

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo != null) {
				if (pathInfo.startsWith("/")) {
					pathInfo = pathInfo.substring(1);

					pathInfo = UrlEncodeDecoder.decode(pathInfo);
					int accountId = Integer.parseInt(pathInfo.substring(0,
							pathInfo.indexOf("/")));
					pathInfo = pathInfo
							.substring((accountId + "").length() + 1);

					String username = pathInfo.substring(0,
							pathInfo.indexOf("/"));
					pathInfo = pathInfo.substring(username.length() + 1);

					String inviterName = pathInfo.substring(0,
							pathInfo.indexOf("/"));
					pathInfo = pathInfo.substring(inviterName.length() + 1);

					String inviterEmail = pathInfo.substring(0,
							pathInfo.indexOf("/"));
					pathInfo = pathInfo.substring(inviterEmail.length() + 1);

					String subdomain = pathInfo;

					UserService userService = ApplicationContextUtil
							.getSpringBean(UserService.class);
					SimpleUser checkUser = userService
							.findUserByUserNameInAccount(username, accountId);

					if (checkUser == null) {
						// this user no long exist on System page
						PageUserNotExistGenerator.responeUserNotExistPage(
								response, request.getContextPath() + "/");
						return;
					} else {
						if (checkUser.getRegisterstatus().equals(
								RegisterStatusConstants.ACTIVE)) {
							// You cant deny , Userhas active , go to login Page
							String html = generateRefuseUserDenyActionPage(
									request.getContextPath() + "/",
									"templates/page/project/RefuseUserDenyActionPage.mt");
							PrintWriter out = response.getWriter();
							out.println(html);
							return;
						} else if (checkUser.getRegisterstatus().equals(
								RegisterStatusConstants.VERIFICATING)) {
							UserSearchCriteria criteria = new UserSearchCriteria();
							criteria.setUsername(new StringSearchField(username));
							criteria.setSaccountid(new NumberSearchField(
									accountId));
							userService.pendingUserAccount(username, accountId);

							String redirectURL = SiteConfiguration
									.getSiteUrl(subdomain)
									+ "project/member/feedback/";
							String html = FeedBackPageGenerator
									.generateDenyFeedbacktoInviter(0, 0,
											inviterEmail, inviterName,
											redirectURL, checkUser.getEmail(),
											checkUser.getUsername(), "",
											USER_DENY_FEEDBACK_TEMPLATE, 0);
							PrintWriter out = response.getWriter();
							out.println(html);
							return;
						} else if (checkUser.getRegisterstatus().equals(
								RegisterStatusConstants.DELETE)) {
							String html = generateRefuseUserDenyActionPage(
									request.getContextPath() + "/",
									USER_HAS_DENIED_PAGE);
							PrintWriter out = response.getWriter();
							out.println(html);
							return;
						}
					}
				}
			}
			throw new ResourceNotFoundException();
		} catch (NumberFormatException e) {
			throw new ResourceNotFoundException();
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException();
		} catch (Exception e) {
			log.error("Error with userService", e);
			throw new MyCollabException(e);
		}
	}
}
