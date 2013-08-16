package com.esofthead.mycollab.module.project.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

@Component("denyInvitationMemberServletHandler")
public class AnotatedDenyProjectMemberInvitationServletHandler implements
		HttpRequestHandler {

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
				int sAccount = Integer.parseInt(pathVariables.substring(0,
						pathVariables.indexOf("/")));
				pathVariables = pathVariables.replace(sAccount + "/", "");

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
						&& sAccount > 0 && projectAdmin != null
						&& !projectAdmin.equals("")) {
					SimpleProjectMember member = projectMemberService.findById(
							memberId, AppContext.getAccountId());
					if (member != null
							&& !member.getStatus().equals(
									ProjectMemberStatusContants.ACTIVE)) {
						member.setStatus(RegisterStatusConstants.DENY);
						projectMemberService.removeWithSession(memberId,
								projectAdmin, AppContext.getAccountId());
						// TODO : send mail to visitor

						// RelayEmailNotification relayNotification = new
						// RelayEmailNotification();
						// relayNotification.setChangeby(projectAdmin);
						// relayNotification.setChangecomment(member
						// .getMemberFullName());
						// relayNotification.setSaccountid(sAccount);
						// relayNotification.setType("invitationMember");
						// relayNotification
						// .setAction(MonitorTypeConstants.ADD_COMMENT_ACTION);
						// relayNotification.setTypeid(member.getProjectid());
						// relayNotification
						// .setEmailhandlerbean(MessageRelayEmailNotificationActionImpl.class
						// .getName());
						// if (relayEmailService != null) {
						// relayEmailService.saveWithSession(
						// relayNotification, projectAdmin);
						// }

						String html = htmlRespone(
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

	private String htmlRespone(String userEmail, String redirectURL) {
		TemplateGenerator templateGenerator = new TemplateGenerator("hello",
				"templates/email/project/memberInvitation/memberDenyInvitationPage.mt");
		templateGenerator.putVariable("toUserEmail", userEmail);
		templateGenerator.putVariable("redirectURL", redirectURL);

		String html = templateGenerator.generateSubjectContent() + " "
				+ templateGenerator.generateBodyContent();

		return html;
	}

}
