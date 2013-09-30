package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.schedule.email.project.ProjectMilestoneRelayEmailNotificationAction;

@Component
public class ProjectMilestoneRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationForProjectAction implements
		ProjectMilestoneRelayEmailNotificationAction {
	@Autowired
	private MilestoneService milestoneService;

	@Autowired
	private AuditLogService auditLogService;

	@Autowired
	private ProjectService projectService;

	private final ProjectFieldNameMapper mapper;

	public ProjectMilestoneRelayEmailNotificationActionImpl() {
		mapper = new ProjectFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int milestoneId = emailNotification.getTypeid();
		SimpleMilestone milestone = milestoneService.findById(milestoneId,
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Phase \"" + milestone.getName()
						+ "\" has been created",
				"templates/email/project/phaseCreatedNotifier.mt");

		templateGenerator.putVariable("milestone", milestone);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(milestone, emailNotification));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleMilestone milestone,
			SimpleRelayEmailNotification emailNotification) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				milestone.getProjectid());
		hyperLinks.put("milestoneURL", linkGenerator
				.generateMilestonePreviewFullLink(milestone.getId()));
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("ownerUserUrl", linkGenerator
				.generateUserPreviewFullLink(milestone.getOwnerFullName()));

		SimpleProject project = projectService.findById(
				milestone.getProjectid(), emailNotification.getSaccountid());
		if (project != null) {
			hyperLinks.put("projectName", project.getName());
		}

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int milestoneId = emailNotification.getTypeid();
		SimpleMilestone milestone = milestoneService.findById(milestoneId,
				emailNotification.getSaccountid());
		if (milestone == null) {
			return null;
		}

		String subject = StringUtils.subString(milestone.getName(), 150);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Phase \"" + subject
						+ "...\" edited",
				"templates/email/project/phaseUpdateNotifier.mt");

		templateGenerator.putVariable("milestone", milestone);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(milestone, emailNotification));

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}

		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int milestoneId = emailNotification.getTypeid();
		SimpleMilestone milestone = milestoneService.findById(milestoneId,
				emailNotification.getSaccountid());
		if (milestone == null) {
			return null;
		}
		String comment = StringUtils.subString(
				emailNotification.getChangecomment(), 150);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: "
						+ emailNotification.getChangeByUserFullName()
						+ " add new comment \"" + comment + "...\" to phase \""
						+ StringUtils.subString(milestone.getName(), 100)
						+ "\"",
				"templates/email/project/phaseCommentNotifier.mt");

		templateGenerator.putVariable("milestone", milestone);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(milestone, emailNotification));
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				milestone.getProjectid());
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));
		return templateGenerator;
	}

	public class ProjectFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ProjectFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("name", "Phase Name");
			fieldNameMap.put("startdate", "Start Date");
			fieldNameMap.put("enddate", "End Date");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("ownerFullName", "Responsible User");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
