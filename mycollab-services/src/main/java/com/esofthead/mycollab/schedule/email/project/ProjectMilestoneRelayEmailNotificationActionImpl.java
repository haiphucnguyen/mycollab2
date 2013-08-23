package com.esofthead.mycollab.schedule.email.project;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleMilestone;
import com.esofthead.mycollab.module.project.service.MilestoneService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationForProjectAction;

@Component
public class ProjectMilestoneRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationForProjectAction implements
		ProjectMilestoneRelayEmailNotificationAction {
	@Autowired
	private MilestoneService milestoneService;

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int milestoneId = emailNotification.getTypeid();
		SimpleMilestone milestone = milestoneService.findById(milestoneId, 0);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$milestone.name]: Phase \"" + milestone.getName()
						+ "\" created",
				"templates/email/project/phaseCreatedNotifier.mt");

		templateGenerator.putVariable("milestone", milestone);
		templateGenerator
				.putVariable("hyperLinks", createHyperLinks(milestone));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleMilestone milestone) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				milestone.getProjectid());
		hyperLinks.put("milestoneURL", linkGenerator
				.generateMilestonePreviewFullLink(milestone.getId()));
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("ownerUserUrl", linkGenerator
				.generateUserPreviewFullLink(milestone.getOwnerFullName()));
		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		// do nothing
		return null;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		// do nothing
		return null;
	}

}
