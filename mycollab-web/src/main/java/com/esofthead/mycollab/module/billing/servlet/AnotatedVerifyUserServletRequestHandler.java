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

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.module.user.dao.UserAccountInvitationMapper;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.domain.UserAccountInvitationExample;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.template.velocity.EngineFactory;

@Component("verifyUserServletHandler")
public class AnotatedVerifyUserServletRequestHandler implements
		HttpRequestHandler {

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
				pathInfo = UrlEncodeDecoder.decode(pathInfo);

				int accountId = Integer.parseInt(pathInfo.substring(0,
						pathInfo.indexOf("/")));
				pathInfo = pathInfo.substring((accountId + "").length() + 1);

				String username = pathInfo.substring(0, pathInfo.indexOf("/"));
				pathInfo = pathInfo.substring(username.length() + 1);

				subdomain = pathInfo;
				User user = userService.findUserByUserName(username);
				if (user != null
						&& user.getRegisterstatus().equals(
								RegisterStatusConstants.ACTIVE)) {
					// redirect to account site
					request.getRequestDispatcher(request.getContextPath() + "/")
							.forward(request, response);
					request.setAttribute("username", user.getUsername());
					request.setAttribute("password", user.getPassword());
					return;
				} else if (user != null
						&& user.getRegisterstatus().equals(
								RegisterStatusConstants.VERIFICATING)) {
					// remove account invitation
					UserAccountInvitationExample userAccountInvitationExample = new UserAccountInvitationExample();
					userAccountInvitationExample.createCriteria()
							.andUsernameEqualTo(username)
							.andAccountidEqualTo(accountId);
					userAccountInvitationMapper
							.deleteByExample(userAccountInvitationExample);

					// forward to page create password for new user
					String redirectURL = SiteConfiguration
							.getSiteUrl(subdomain)
							+ "user/confirm_invite/update_info/";
					String html = generateUserFillInformationPage(request,
							accountId, username, user.getEmail(), redirectURL,
							loginURL);
					PrintWriter out = response.getWriter();
					out.print(html);
					return;
				}
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private String generateUserFillInformationPage(HttpServletRequest request,
			int accountId, String username, String email, String redirectURL,
			String loginURL) {
		String template = "/templates/page/FillUserInformation.mt";
		VelocityContext context = new VelocityContext(
				EngineFactory.createContext());
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
		EngineFactory.getTemplateEngine().evaluate(context, writer, "log task",
				reader);
		return writer.toString();
	}
}
