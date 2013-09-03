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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.configuration.SharingOptions;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.project.domain.ProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.template.velocity.TemplateContext;
import com.esofthead.template.velocity.TemplateEngine;

@Component("denyInvitationMemberServletHandler")
public class AnotatedDenyProjectMemberInvitationServletHandler implements
		HttpRequestHandler {

	private static String DENY_FEEDBACK_TEMPLATE = "templates/page/memberDenyInvitationPage.mt";
	private static String REFUSE_MEMBER_DENY_TEMPLATE = "templates/page/refuseMemberDenyActionPage.mt";

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private RelayEmailNotificationService relayEmailService;

	@Autowired
	private ProjectService projectService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				// email, projectId, sAccountId , inviterName, inviterEmail
				String pathVariables = UrlEncodeDecoder.decode(pathInfo);

				String email = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables.substring(email.length() + 1);

				int projectId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((projectId + "")
						.length() + 1);

				int sAccountId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((sAccountId + "")
						.length() + 1);

				String inviterName = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables
						.substring(inviterName.length() + 1);

				String inviterEmail = pathVariables;

				String subdomain = projectService
						.getSubdomainOfProject(projectId);

				// remove from ProjectMember Table if it exist ---------
				ProjectMember projectMember = projectMemberService
						.findMemberByUsername(email, projectId, sAccountId);
				if (projectMember != null) {
					String html = generateRefuseMemberDenyActionPage();
					PrintWriter out = response.getWriter();
					out.println(html);
					return;
				}

				String redirectURL = SiteConfiguration.getSiteUrl(subdomain)
						+ "project/member/feedback/";

				String html = generateDenyFeedbacktoInviter(inviterEmail,
						inviterName, redirectURL, email, "You");
				PrintWriter out = response.getWriter();
				out.println(html);
				return;
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private String generateRefuseMemberDenyActionPage() {
		TemplateContext context = new TemplateContext();

		Reader reader;
		try {
			reader = new InputStreamReader(
					AnotatedDenyProjectMemberInvitationServletHandler.class
							.getClassLoader().getResourceAsStream(
									REFUSE_MEMBER_DENY_TEMPLATE), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(
					AnotatedDenyProjectMemberInvitationServletHandler.class
							.getClassLoader().getResourceAsStream(
									REFUSE_MEMBER_DENY_TEMPLATE));
		}
		Map<String, String> defaultUrls = new HashMap<String, String>();

		SharingOptions sharingOptions = SiteConfiguration.getSharingOptions();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		defaultUrls.put("facebook_url", sharingOptions.getFacebookUrl());
		defaultUrls.put("google_url", sharingOptions.getGoogleplusUrl());
		defaultUrls.put("linkedin_url", sharingOptions.getLinkedinUrl());
		defaultUrls.put("twitter_url", sharingOptions.getTwitterUrl());

		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}

	private String generateDenyFeedbacktoInviter(String inviterEmail,
			String inviterName, String redirectURL, String memberEmail,
			String memberName) {
		TemplateContext context = new TemplateContext();

		Reader reader;
		try {
			reader = new InputStreamReader(
					AnotatedDenyProjectMemberInvitationServletHandler.class
							.getClassLoader().getResourceAsStream(
									DENY_FEEDBACK_TEMPLATE), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(
					AnotatedDenyProjectMemberInvitationServletHandler.class
							.getClassLoader().getResourceAsStream(
									DENY_FEEDBACK_TEMPLATE));
		}
		context.put("inviterEmail", inviterEmail);
		context.put("redirectURL", redirectURL);
		context.put("toEmail", memberEmail);
		context.put("toName", memberName);
		context.put("inviterName", inviterName);

		Map<String, String> defaultUrls = new HashMap<String, String>();

		SharingOptions sharingOptions = SiteConfiguration.getSharingOptions();

		defaultUrls.put("cdn_url", SiteConfiguration.getCdnUrl());
		defaultUrls.put("facebook_url", sharingOptions.getFacebookUrl());
		defaultUrls.put("google_url", sharingOptions.getGoogleplusUrl());
		defaultUrls.put("linkedin_url", sharingOptions.getLinkedinUrl());
		defaultUrls.put("twitter_url", sharingOptions.getTwitterUrl());

		context.put("defaultUrls", defaultUrls);

		StringWriter writer = new StringWriter();
		TemplateEngine.evaluate(context, writer, "log task", reader);
		return writer.toString();
	}

}
