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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.billing.servlet.AnotatedDenyUserServletRequestHandler.PageUserNotExistGenerator;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitationExample;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("acceptUserInvitationServlet")
public class AnotatedVerifyUserServletRequestHandler implements
		HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedVerifyUserServletRequestHandler.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountInvitationMapper userAccountInvitationMapper;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		String subdomain = "", loginURL = request.getContextPath() + "/";
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);
			}
			pathInfo = UrlEncodeDecoder.decode(pathInfo);

			int accountId = Integer.parseInt(pathInfo.substring(0,
					pathInfo.indexOf("/")));
			pathInfo = pathInfo.substring((accountId + "").length() + 1);

			String username = pathInfo.substring(0, pathInfo.indexOf("/"));
			pathInfo = pathInfo.substring(username.length() + 1);

			subdomain = pathInfo;
			User user = userService.findUserByUserName(username);
			SimpleUser userInAccount = userService.findUserByUserNameInAccount(
					username, accountId);

			if (user == null || userInAccount == null) {
				PageUserNotExistGenerator.responeUserNotExistPage(response,
						request.getContextPath() + "/");
				return;
			} else {
				if (userInAccount.getRegisterstatus().equals(
						RegisterStatusConstants.ACTIVE)) {
					log.debug("Forward user {} to page {}", user.getUsername(),
							request.getContextPath());
					// redirect to account site
					request.getRequestDispatcher(request.getContextPath() + "/")
							.forward(request, response);
					return;
				} else {
					// remove account invitation
					UserAccountInvitationExample userAccountInvitationExample = new UserAccountInvitationExample();
					userAccountInvitationExample.createCriteria()
							.andUsernameEqualTo(username)
							.andAccountidEqualTo(accountId);
					userAccountInvitationMapper
							.deleteByExample(userAccountInvitationExample);

					userService.updateUserAccountStatus(username, accountId,
							RegisterStatusConstants.ACTIVE);

					if (user.getPassword() == null
							|| user.getPassword().trim().equals("")) {
						log.debug(
								"User {} has null password. It seems he is the new user join to mycollab. Redirect him to page let him update his password {}",
								user.getUsername(),
								BeanUtility.printBeanObj(user));
						// forward to page create password for new user
						String redirectURL = SiteConfiguration
								.getSiteUrl(subdomain)
								+ "user/confirm_invite/update_info/";
						String html = generateUserFillInformationPage(request,
								accountId, username, user.getEmail(),
								redirectURL, loginURL);
						PrintWriter out = response.getWriter();
						out.print(html);
					} else {
						log.debug("Forward user {} to page {}",
								user.getUsername(), request.getContextPath());
						// redirect to account site
						request.getRequestDispatcher(
								request.getContextPath() + "/").forward(
								request, response);
						return;
					}
				}
			}
		} else {
			PageNotFoundGenerator.responsePage404(response);
		}
	}

	private String generateUserFillInformationPage(HttpServletRequest request,
			int accountId, String username, String email, String redirectURL,
			String loginURL) {
		String template = "/templates/page/user/FillUserInformation.mt";
		TemplateContext context = new TemplateContext();
		Reader reader;
		try {
			reader = new InputStreamReader(PageNotFoundGenerator.class
					.getClassLoader().getResourceAsStream(template), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(PageNotFoundGenerator.class
					.getClassLoader().getResourceAsStream(template));
		}

		context.put("username", username);
		context.put("accountId", accountId);
		context.put("email", email);
		context.put("redirectURL", redirectURL);
		context.put("loginURL", loginURL);

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}
}
