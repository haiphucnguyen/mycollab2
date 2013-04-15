package com.esofthead.mycollab.schedule.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.ApplicationProperties;
import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.ProjectMemberStatusContants;
import com.esofthead.mycollab.module.project.domain.SimpleProjectMember;
import com.esofthead.mycollab.module.project.domain.criteria.ProjectMemberSearchCriteria;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.schedule.ScheduleConfig;
import com.esofthead.mycollab.web.AppContext;

@Service
public class ScheduleProjectMemberInvitationImp {

	private static Logger log = LoggerFactory
			.getLogger(ScheduleProjectMemberInvitationImp.class);

	@Autowired
	private ExtMailService extMailService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	public void runNotification() {

//		ProjectMemberSearchCriteria searchCriteria = new ProjectMemberSearchCriteria();
//		searchCriteria.setStatus(new StringSearchField(
//				ProjectMemberStatusContants.VERIFICATING));
//		ProjectMemberService memberService = AppContext
//				.getSpringBean(ProjectMemberService.class);
//		List<SimpleProjectMember> lstProjectMember = memberService
//				.findPagableListByCriteria(new SearchRequest<ProjectMemberSearchCriteria>(
//						searchCriteria, 0, Integer.MAX_VALUE));
//
//		log.debug("Get " + lstProjectMember.size()
//				+ " invitation email notifications");
//
//		for (SimpleProjectMember member : lstProjectMember) {
//
//			TemplateGenerator templateGenerator = new TemplateGenerator(
//					"MyCollab has invited you to join the team for project \" $member.projectName\"",
//					"templates/email/project/memberInvitation/memberInvitationNotifier.mt");
//			templateGenerator.putVariable("member", member);
//			templateGenerator.putVariable(
//					"urlAccept",
//					ApplicationProperties
//							.getProperty(ApplicationProperties.APP_URL)
//							+ "?url="
//							+ "project/member/invitation/confirm_invite"
//							+ UrlEncodeDecoder.encode(member.getUsername()));
//			templateGenerator.putVariable(
//					"urlDeny",
//					ApplicationProperties
//							.getProperty(ApplicationProperties.APP_URL)
//							+ "?url="
//							+ "project/member/invitation/deny_invite"
//							+ UrlEncodeDecoder.encode(member.getUsername()));
//			extMailService.sendHTMLMail("mail@esofthead.com", "MyCollab",
//					Arrays.asList(new MailRecipientField(member.getUsername(),
//							member.getMemberFullName())), null, null,
//					templateGenerator.generateSubjectContent(),
//					templateGenerator.generateBodyContent(), null);
//		}

	}

}
