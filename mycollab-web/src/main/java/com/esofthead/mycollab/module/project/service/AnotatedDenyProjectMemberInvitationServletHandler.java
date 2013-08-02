package com.esofthead.mycollab.module.project.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.RelayEmailNotification;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.schedule.email.command.MessageRelayEmailNotificationActionImpl;

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

				String projectAdmin = pathVariables.substring(0,
						pathVariables.indexOf("/"));
				pathVariables = pathVariables.replace(projectAdmin + "/", "");

				int memberId = Integer.parseInt(pathVariables);
				if (memberId > 0 && projectMemberService != null
						&& sAccount > 0 && projectAdmin != null
						&& !projectAdmin.equals("")) {
					SimpleProjectMember member = projectMemberService
							.findById(memberId);
					if (member != null && !member.getStatus().equals(ProjectMemberStatusContants.ACTIVE)) {
						projectMemberService.removeWithSession(memberId,
								projectAdmin);

						RelayEmailNotification relayNotification = new RelayEmailNotification();
						relayNotification.setChangeby(projectAdmin);
						relayNotification.setChangecomment(member
								.getMemberFullName());
						relayNotification.setSaccountid(sAccount);
						relayNotification.setType("invitationMember");
						relayNotification
								.setAction(MonitorTypeConstants.ADD_COMMENT_ACTION);
						relayNotification.setTypeid(member.getProjectid());
						relayNotification
								.setEmailhandlerbean(MessageRelayEmailNotificationActionImpl.class
										.getName());
						if (relayEmailService != null) {
							relayEmailService.saveWithSession(
									relayNotification, projectAdmin);
						}
					}
				}
			}
		} else {
			// TODO: response to user invalid page
		}
	}

}
