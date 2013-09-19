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
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationForProjectAction;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.schedule.email.project.ProjectRiskRelayEmailNotificationAction;

@Component
public class ProjectRiskRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationForProjectAction implements
		ProjectRiskRelayEmailNotificationAction {

	@Autowired
	private RiskService riskService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AuditLogService auditLogService;

	private final ProjectFieldNameMapper mapper;

	public ProjectRiskRelayEmailNotificationActionImpl() {
		mapper = new ProjectFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int riskId = emailNotification.getTypeid();
		SimpleRisk risk = riskService.findById(riskId,
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Risk \"" + risk.getRiskname()
						+ "\" has been created",
				"templates/email/project/riskCreatedNotifier.mt");

		templateGenerator.putVariable("risk", risk);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(risk, emailNotification));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleRisk risk,
			SimpleRelayEmailNotification emailNotification) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				risk.getProjectid());
		hyperLinks.put("riskURL",
				linkGenerator.generateRiskPreviewFullLink(risk.getId()));

		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("raiseUserUrl", linkGenerator
				.generateUserPreviewFullLink(risk.getRaisedByUserFullName()));
		hyperLinks.put("assignUserURL", linkGenerator
				.generateUserPreviewFullLink(risk.getAssignedToUserFullName()));

		SimpleProject project = projectService.findById(risk.getProjectid(),
				emailNotification.getSaccountid());
		if (project != null) {
			hyperLinks.put("projectName", project.getName());
		}
		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int riskId = emailNotification.getTypeid();
		SimpleRisk risk = riskService.findById(riskId,
				emailNotification.getSaccountid());
		if (risk == null) {
			return null;
		}

		String subject = StringUtils.subString(risk.getRiskname(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Risk \"" + subject + "...\" edited",
				"templates/email/project/riskUpdateNotifier.mt");

		templateGenerator.putVariable("risk", risk);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(risk, emailNotification));
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
		int riskId = emailNotification.getTypeid();
		SimpleRisk risk = riskService.findById(riskId,
				emailNotification.getSaccountid());
		if (risk == null) {
			return null;
		}

		String comment = StringUtils.subString(
				emailNotification.getChangecomment(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: "
						+ emailNotification.getChangeByUserFullName()
						+ " add new comment \"" + comment + "...\" to risk \""
						+ StringUtils.subString(risk.getRiskname(), 100) + "\"",
				"templates/email/project/riskCommentNotifier.mt");
		templateGenerator.putVariable("risk", risk);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(risk, emailNotification));
		templateGenerator.putVariable("comment", emailNotification);

		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				risk.getProjectid());
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));

		return templateGenerator;
	}

	public class ProjectFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ProjectFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("riskname", "Risk Name");
			fieldNameMap.put("assignedToUserFullName", "Assigned to");
			fieldNameMap.put("consequence", "Consequence");
			fieldNameMap.put("probalitity", "Probability");

			fieldNameMap.put("datedue", "Due date");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("response", "Response");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
