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
import com.esofthead.mycollab.module.project.domain.SimpleProblem;
import com.esofthead.mycollab.module.project.domain.SimpleProject;
import com.esofthead.mycollab.module.project.service.ProblemService;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationForProjectAction;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.schedule.email.project.ProjectProblemRelayEmailNotificationAction;

@Component
public class ProjectProblemRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationForProjectAction implements
		ProjectProblemRelayEmailNotificationAction {

	@Autowired
	private ProblemService problemService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private AuditLogService auditLogService;

	private final ProjectFieldNameMapper mapper;

	public ProjectProblemRelayEmailNotificationActionImpl() {
		mapper = new ProjectFieldNameMapper();
	}

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
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(problem, emailNotification));

		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleProblem problem,
			SimpleRelayEmailNotification emailNotification) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				problem.getProjectid());

		hyperLinks.put("problemURL",
				linkGenerator.generateProblemPreviewFullLink(problem.getId()));

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
				emailNotification.getSaccountid());
		if (project != null) {
			hyperLinks.put("projectName", project.getName());
		}

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int problemId = emailNotification.getTypeid();
		SimpleProblem problem = problemService.findById(problemId, 0);
		if (problem == null) {
			return null;
		}

		String subject = StringUtils.subString(problem.getIssuename(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: Problem \"" + subject
						+ "...\" edited",
				"templates/email/project/problemUpdateNotifier.mt");

		templateGenerator.putVariable("problem", problem);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(problem, emailNotification));
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
		int problemId = emailNotification.getTypeid();
		SimpleProblem problem = problemService.findById(problemId, 0);
		if (problem == null) {
			return null;
		}
		String comment = StringUtils.subString(
				emailNotification.getChangecomment(), 150);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$hyperLinks.projectName]: "
						+ emailNotification.getChangeByUserFullName()
						+ " add new comment \"" + comment
						+ "...\" to problem \""
						+ StringUtils.subString(problem.getIssuename(), 100)
						+ "\"",
				"templates/email/project/problemCommentNotifier.mt");

		templateGenerator.putVariable("problem", problem);
		templateGenerator.putVariable("hyperLinks",
				createHyperLinks(problem, emailNotification));
		templateGenerator.putVariable("comment", emailNotification);
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				problem.getProjectid());
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));

		return templateGenerator;
	}

	public class ProjectFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ProjectFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("issuename", "Issue name");
			fieldNameMap.put("assignedUserFullName", "Assigned to");
			fieldNameMap.put("datedue", "Due date");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("impact", "Impact");
			fieldNameMap.put("priority", "Priority");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
