package com.esofthead.mycollab.module.project.service.esb.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.MailRelayService;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.service.esb.InviteOutsideProjectMemberListener;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.domain.User;
import com.esofthead.mycollab.module.user.service.UserService;

@Component
public class InviteOutsideProjectMemberListenerImpl implements
		InviteOutsideProjectMemberListener {

	private static Logger log = LoggerFactory
			.getLogger(InviteOutsideProjectMemberListenerImpl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private MailRelayService mailRelayService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectMemberService projectMemberService;

	@Override
	public void inviteUsers(String[] emails, int projectId, int projectRoleId,
			String inviteUserName, int sAccountId) {

		log.debug(
				"Request sending invitation email to user {} in project id {} with role id {} and account id {} by user {}",
				new String[] { BeanUtility.printBeanObj(emails),
						projectId + "", projectRoleId + "", sAccountId + "",
						inviteUserName });

		SimpleProject project = projectService.findById(projectId, sAccountId);

		String name = "You";
		SimpleUser user = userService.findUserByUserNameInAccount(
				inviteUserName, sAccountId);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"$inviteUser has invited you to join the team for project \" $member.projectName\"",
				"templates/email/project/memberInvitation/memberInvitationNotifier.mt");

		SimpleProjectMember member = new SimpleProjectMember();
		member.setProjectName(project.getName());

		templateGenerator.putVariable("member", member);
		templateGenerator.putVariable("inviteUser", user.getDisplayName());

		String subdomain = projectService.getSubdomainOfProject(projectId);

		for (String email : emails) {
			User inviteUser = userService.findUserByUserName(email);

			if (inviteUser != null) {
				// user already belong to account

				member.setProjectid(projectId);
				member.setUsername(inviteUser.getUsername());
				member.setProjectroleid(projectId);
				member.setIsadmin(false);
				member.setSaccountid(sAccountId);
				member.setStatus(ProjectMemberStatusContants.VERIFICATING);
				member.setJoindate(new Date());

				projectMemberService.saveWithSession(member, inviteUserName);
				templateGenerator.putVariable(
						"urlAccept",
						SiteConfiguration.getSiteUrl(subdomain)
								+ "project/member/invitation/confirm_invite/"
								+ UrlEncodeDecoder.encode(email + "/"
										+ projectId + "/" + sAccountId));
			} else {
				// user not exist
				templateGenerator.putVariable(
						"urlAccept",
						SiteConfiguration.getSiteUrl(subdomain)
								+ "project/member/invitation/confirm_invite/"
								+ UrlEncodeDecoder.encode(email + "/"
										+ projectId + "/" + sAccountId + "/"
										+ projectRoleId));
			}

			templateGenerator.putVariable(
					"urlDeny",
					SiteConfiguration.getSiteUrl(subdomain)
							+ "project/member/invitation/deny_invite/"
							+ UrlEncodeDecoder.encode(email + "/" + projectId
									+ "/" + sAccountId));

			templateGenerator.putVariable("userName", name);

			mailRelayService.saveRelayEmail(new String[] { name },
					new String[] { email },
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent());
		}

	}

}
