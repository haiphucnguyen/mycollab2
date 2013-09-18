package com.esofthead.mycollab.module.project.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class ProjectRouteBuilder extends SpringRouteBuilder {

	@Override
	public void configure() throws Exception {
		// Configure project removed
		from(ProjectEndPoints.PROJECT_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:projectDelete.queue");
		from("seda:projectDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(DeleteProjectListener.class),
				"projectRemoved(int, int)");

		// Configure project member removed
		from(ProjectEndPoints.PROJECT_MEMBER_DELETE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectMemberDelete.queue");
		from("seda:projectMemberDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectMemberListener.class),
						"projectMemberRemoved(String,int, int, int)");

		// Configure send project member invitation
		from(ProjectEndPoints.PROJECT_SEND_INVITATION_USER).setExchangePattern(
				ExchangePattern.InOnly)
				.to("seda:projectMemberInvitation.queue");
		from("seda:projectMemberInvitation.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(InviteOutsideProjectMemberListener.class),
						"inviteUsers(String[],int, int, String, int)");
	}

}
