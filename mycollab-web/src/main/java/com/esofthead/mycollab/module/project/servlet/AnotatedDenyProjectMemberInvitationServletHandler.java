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
import com.esofthead.mycollab.module.billing.RegisterStatusConstants;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
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

				String projectAdmin = "";
				if (pathVariables.indexOf("/") != -1) {
					projectAdmin = pathVariables.substring(0,
							pathVariables.indexOf("/"));
				} else {
					projectAdmin = pathVariables;
				}

				pathVariables = pathVariables.replace(projectAdmin + "/", "");

				int memberId = Integer.parseInt(pathVariables);
				if (memberId > 0 && projectMemberService != null
						&& sAccountId > 0 && projectAdmin != null
						&& !projectAdmin.equals("")) {
					SimpleProjectMember member = projectMemberService.findById(
							memberId, sAccountId);
					if (member != null
							&& !member.getStatus().equals(
									ProjectMemberStatusContants.ACTIVE)) {
						member.setStatus(RegisterStatusConstants.DENY);
						projectMemberService.removeWithSession(memberId,
								projectAdmin, AppContext.getAccountId());

						String html = generateDenyFeedbacktoInviter(
								"cuongnguyen@esofthead.com",
								"http://localhost:8080/mycollab-web");
						PrintWriter out = response.getWriter();
						out.println(html);
					}
				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}

	private String generateDenyFeedbacktoInviter(String userEmail,
			String redirectURL) {
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

		StringWriter writer = new StringWriter();
		EngineFactory.getTemplateEngine().evaluate(context, writer, "log task",
				reader);
		return writer.toString();
	}

}
