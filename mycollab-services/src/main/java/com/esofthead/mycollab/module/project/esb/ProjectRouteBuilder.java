package com.esofthead.mycollab.module.project.esb;

import org.apache.camel.ExchangePattern;
import org.apache.camel.spring.SpringRouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
public class ProjectRouteBuilder extends SpringRouteBuilder {

	private static Logger log = LoggerFactory
			.getLogger(ProjectRouteBuilder.class);

	@Override
	public void configure() throws Exception {
		log.debug("Configure project remove route");
		from(ProjectEndPoints.PROJECT_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:projectDelete.queue");
		from("seda:projectDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(DeleteProjectCommand.class),
				"projectRemoved(int, int)");

		log.debug("Configure project member remove route");
		from(ProjectEndPoints.PROJECT_MEMBER_DELETE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectMemberDelete.queue");
		from("seda:projectMemberDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectMemberCommand.class),
						"projectMemberRemoved(String,int, int, int)");

		log.debug("Configure project message remove route");
		from(ProjectEndPoints.PROJECT_MESSAGE_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectMessageDelete.queue");
		from("seda:projectMessageDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectMessageCommand.class),
						"messageRemoved(String,int, int, int)");

		log.debug("Configure project bug remove route");
		from(ProjectEndPoints.PROJECT_BUG_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:projectBugDelete.queue");
		from("seda:projectBugDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(DeleteProjectBugCommand.class),
				"bugRemoved(String,int, int, int)");

		log.debug("Configure project component remove route");
		from(ProjectEndPoints.PROJECT_COMPONENT_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectComponentDelete.queue");
		from("seda:projectComponentDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectComponentCommand.class),
						"componentRemoved(String,int, int, int)");

		log.debug("Configure project component remove route");
		from(ProjectEndPoints.PROJECT_COMPONENT_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectComponentDelete.queue");
		from("seda:projectComponentDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectComponentCommand.class),
						"componentRemoved(String,int, int, int)");

		log.debug("Configure project version remove route");
		from(ProjectEndPoints.PROJECT_VERSION_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectVersionDelete.queue");
		from("seda:projectVersionDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectVersionCommand.class),
						"versionRemoved(String,int, int, int)");

		log.debug("Configure project task remove route");
		from(ProjectEndPoints.PROJECT_TASK_REMOVE_ENDPOINT).setExchangePattern(
				ExchangePattern.InOnly).to("seda:projectTaskDelete.queue");
		from("seda:projectTaskDelete.queue").threads().bean(
				ApplicationContextUtil.getBean(DeleteProjectTaskCommand.class),
				"taskRemoved(String,int, int, int)");

		log.debug("Configure project task list remove route");
		from(ProjectEndPoints.PROJECT_TASKLIST_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectTaskListDelete.queue");
		from("seda:projectTaskListDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectTaskListCommand.class),
						"taskListRemoved(String,int, int, int)");

		log.debug("Configure project milestone remove route");
		from(ProjectEndPoints.PROJECT_MILESTONE_REMOVE_ENDPOINT)
				.setExchangePattern(ExchangePattern.InOnly).to(
						"seda:projectMilestoneDelete.queue");
		from("seda:projectMilestoneDelete.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(DeleteProjectMilestoneCommand.class),
						"milestoneRemoved(String,int, int, int)");

		log.debug("Configure project member invitation route");
		from(ProjectEndPoints.PROJECT_SEND_INVITATION_USER).setExchangePattern(
				ExchangePattern.InOnly)
				.to("seda:projectMemberInvitation.queue");
		from("seda:projectMemberInvitation.queue")
				.threads()
				.bean(ApplicationContextUtil
						.getBean(InviteOutsideProjectMemberCommand.class),
						"inviteUsers(String[],int, int, String, int)");
	}

}
