package com.esofthead.mycollab.module.billing.servlet;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.ResourceNotFoundException;
import com.esofthead.mycollab.module.billing.servlet.AnotatedDenyUserServletRequestHandler.PageUserNotExistGenerator;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.servlet.GenericServlet;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("recoverUserPasswordServlet")
public class AnotatedUserRecoveryPasswordHandlerServlet extends GenericServlet {

	private static Logger log = LoggerFactory
			.getLogger(AnotatedUserRecoveryPasswordHandlerServlet.class);
	@Autowired
	private UserService userService;

	private String generateUserRecoveryPasswordPage(String username,
			String loginURL, String redirectURL) {
		String template = "templates/page/user/UserRecoveryPasswordPage.mt";
		TemplateContext context = new TemplateContext();
		Reader reader;
		try {
			reader = new InputStreamReader(
					AnotatedUserRecoveryPasswordHandlerServlet.class
							.getClassLoader().getResourceAsStream(template),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(
					AnotatedUserRecoveryPasswordHandlerServlet.class
							.getClassLoader().getResourceAsStream(template));
		}

		context.put("username", username);
		context.put("loginURL", loginURL);
		context.put("redirectURL", redirectURL);

		Map<String, String> defaultUrls = new HashMap<String, String>();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}

	@Override
	protected void onHandleRequest(HttpServletRequest request,
			HttpServletResponse response) {
		String pathInfo = request.getPathInfo();
		try {
			if (pathInfo != null) {
				if (pathInfo.startsWith("/")) {
					pathInfo = pathInfo.substring(1);
					pathInfo = UrlEncodeDecoder.decode(pathInfo);

					String username = pathInfo;
					User user = userService.findUserByUserName(username);
					if (user == null) {
						PageUserNotExistGenerator.responeUserNotExistPage(
								response, request.getContextPath() + "/");
						return;
					} else {
						String loginURL = request.getContextPath() + "/";

						String redirectURL = loginURL
								+ "user/recoverypassword/action";

						String html = generateUserRecoveryPasswordPage(
								username, loginURL, redirectURL);
						PrintWriter out = response.getWriter();
						out.print(html);
						return;
					}
				}
			}
			throw new ResourceNotFoundException();
		} catch (IndexOutOfBoundsException e) {
			throw new ResourceNotFoundException();
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException();
		} catch (Exception e) {
			log.error("Error with userService", e);
			throw new MyCollabException(e);
		}
	}
}
