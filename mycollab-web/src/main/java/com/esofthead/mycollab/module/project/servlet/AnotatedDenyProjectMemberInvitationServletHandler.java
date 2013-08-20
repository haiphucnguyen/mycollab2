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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.configuration.SharingOptions;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.servlet.AnotatedVerifyProjectMemberInvitationHandlerServlet.PageNotFoundGenerator;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.EngineFactory;

@Component("denyInvitationMemberServletHandler")
public class AnotatedDenyProjectMemberInvitationServletHandler implements
		HttpRequestHandler {

	private static String DENY_FEEDBACK_TEMPLATE = "templates/page/memberDenyInvitationPage.mt";

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	private RelayEmailNotificationService relayEmailService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo != null) {
			if (pathInfo.startsWith("/")) {
				pathInfo = pathInfo.substring(1);

				String pathVariables = UrlEncodeDecoder.decode(pathInfo);
				int sAccountId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((sAccountId + "")
						.length() + 1);

				int memberId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.substring((memberId + "")
						.length() + 1);

				String senderEmail = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables.substring((senderEmail + "")
						.length() + 1);

				String senderName = pathVariables;

				String subdomain = "", toEmail = "", toName = "";
				// ** update variables for handel outside member deny invitation
				if (pathVariables.indexOf("/") != -1 && memberId == 0) {
					senderName = pathVariables.substring(0,
							pathVariables.indexOf("/"));
					pathVariables = pathVariables
							.substring(senderName.length() + 1);

					subdomain = pathVariables.substring(0,
							pathVariables.indexOf("mycollab-web")
									+ "mycollab-web".length() + 1);
					pathVariables = pathVariables
							.substring(subdomain.length() + 1);

					toEmail = pathVariables.substring(0,
							pathVariables.indexOf("/"));
					pathVariables = pathVariables
							.substring(toEmail.length() + 1);

					toName = pathVariables;
				}

				if (memberId > 0) {
					SimpleProjectMember member = projectMemberService.findById(
							memberId, sAccountId);
					if (member != null
							&& !member.getStatus().equals(
									ProjectMemberStatusContants.ACTIVE)) {
						toEmail = member.getEmail();
						toName = member.getMemberFullName();
						projectMemberService.removeWithSession(memberId, "",
								AppContext.getAccountId());

						ProjectService projectService = AppContext
								.getSpringBean(ProjectService.class);
						subdomain = projectService.getSubdomainOfProject(member
								.getProjectid());

						String redirectURL = SiteConfiguration
								.getSiteUrl(subdomain)
								+ "project/member/feedback/";

						String html = generateDenyFeedbacktoInviter(
								senderEmail, senderName, redirectURL, toEmail,
								toName);
						PrintWriter out = response.getWriter();
						out.println(html);
						return;
					}
				} else {
					String redirectURL = SiteConfiguration
							.getSiteUrl(subdomain) + "project/member/feedback/";

					String html = generateDenyFeedbacktoInviter(senderEmail,
							senderName, redirectURL, toEmail, toName);
					PrintWriter out = response.getWriter();
					out.println(html);
					return;
				}
			}
		}
		PageNotFoundGenerator.responsePage404(response);
	}

	private String generateDenyFeedbacktoInviter(String inviterEmail,
			String inviterName, String redirectURL, String memberEmail,
			String memberName) {
		VelocityContext context = new VelocityContext(
				EngineFactory.createContext());

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
		EngineFactory.getTemplateEngine().evaluate(context, writer, "log task",
				reader);
		return writer.toString();
	}

}
