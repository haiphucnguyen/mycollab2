package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		// TODO Auto-generated method stub
		return null;
	}

}
