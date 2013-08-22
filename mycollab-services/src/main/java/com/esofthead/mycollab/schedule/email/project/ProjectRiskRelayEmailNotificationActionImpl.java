package com.esofthead.mycollab.schedule.email.project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleRisk;
import com.esofthead.mycollab.module.project.service.RiskService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationAction;

public class ProjectRiskRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationAction implements
		ProjectRiskRelayEmailNotificationAction {

	@Autowired
	private RiskService riskService;

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int riskId = emailNotification.getTypeid();
		SimpleRisk risk = riskService.findById(riskId, 0);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$risk.riskname]: Risk \"" + risk.getRiskname() + "\" created",
				"templates/email/project/riskCreatedNotifier.mt");

		templateGenerator.putVariable("risk", risk);
		templateGenerator.putVariable("hyperLinks", createHyperLinks(risk));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleRisk risk) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				risk.getProjectid());
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("raiseUserUrl", linkGenerator
				.generateUserPreviewFullLink(risk.getRaisedByUserFullName()));
		hyperLinks.put("assignUserURL", linkGenerator
				.generateUserPreviewFullLink(risk.getAssignedToUserFullName()));
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
