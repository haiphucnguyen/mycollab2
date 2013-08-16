package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.VelocityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.web.AppContext;
import com.esofthead.template.velocity.EngineFactory;

@Component("denyInvitationMemberServletHandler")
public class AnotatedDenyProjectMemberInvitationServletHandler implements
		HttpRequestHandler {

	private static String DENY_FEEDBACK_TEMPLATE = "templates/email/project/memberInvitation/memberDenyInvitationPage.mt";

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
				pathVariables = pathVariables.replace(sAccountId + "/", "");

				int memberId = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.replace(memberId + "/", "");

				String inviterEmail = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables.replace(inviterEmail + "/", "");

				String inviterName = pathVariables;

				if (memberId > 0) {
					SimpleProjectMember member = projectMemberService.findById(
							memberId, sAccountId);
					if (member != null
							&& !member.getStatus().equals(
									ProjectMemberStatusContants.ACTIVE)) {
						String memberEmail = member.getEmail();
						String memberName = member.getMemberFullName();
						projectMemberService.removeWithSession(memberId, "",
								AppContext.getAccountId());

						ProjectService projectService = AppContext
								.getSpringBean(ProjectService.class);
						String subdomain = projectService
								.getSubdomainOfProject(member.getProjectid());

						String redirectURL = SiteConfiguration
								.getSiteUrl(subdomain)
								+ "project/member/feedback/";

						String html = generateDenyFeedbacktoInviter(
								inviterEmail, inviterName, redirectURL,
								memberEmail, memberName);
						PrintWriter out = response.getWriter();
						out.println(html);
					}
				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}

	private String generateDenyFeedbacktoInviter(String inviterEmail,
			String inviterName, String redirectURL, String memberEmail,
			String memberName) {
		VelocityContext context = new VelocityContext(
				EngineFactory.createContext());

		Reader reader;
		try {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							DENY_FEEDBACK_TEMPLATE), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			reader = new InputStreamReader(TemplateGenerator.class
					.getClassLoader().getResourceAsStream(
							DENY_FEEDBACK_TEMPLATE));
		}
		context.put("inviterEmail", inviterEmail);
		context.put("redirectURL", redirectURL);
		context.put("memberEmail", memberEmail);
		context.put("memberName", memberName);
		context.put("inviterName", inviterName);

		StringWriter writer = new StringWriter();
		EngineFactory.getTemplateEngine().evaluate(context, writer, "log task",
				reader);
		return writer.toString();
	}

}
