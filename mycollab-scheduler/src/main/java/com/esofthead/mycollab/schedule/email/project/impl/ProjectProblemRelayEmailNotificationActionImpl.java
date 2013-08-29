package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationForProjectAction;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;

@Component
public class ProjectProblemRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationForProjectAction implements
		SendingRelayEmailNotificationAction {

	@Autowired
	private ProblemService problemService;

	@Autowired
	private ProjectService projectService;

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int problemId = emailNotification.getTypeid();
		SimpleProblem problem = problemService.findById(problemId, 0);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Problem \""
						+ problem.getIssuename() + "\" has been created",
				"templates/email/project/problemCreatedNotifier.mt");

		templateGenerator.putVariable("problem", problem);
		templateGenerator.putVariable("hyperLinks", createHyperLinks(problem));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleProblem problem) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				problem.getProjectid());

		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks
				.put("assignUserUrl", linkGenerator
						.generateUserPreviewFullLink(problem
								.getAssignedUserFullName()));
		hyperLinks
				.put("raiseUserUrl", linkGenerator
						.generateUserPreviewFullLink(problem
								.getRaisedByUserFullName()));

		SimpleProject project = projectService.findById(problem.getProjectid(),
				problem.getSaccountid());
		if (project != null) {
			hyperLinks.put("projectName", project.getName());
		}

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
		// TODO Auto-generated method stub
		return null;
	}

}
