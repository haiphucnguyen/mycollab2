package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.schedule.email.project.ProjectTaskRelayEmailNotificationAction;

@Service
public class ProjectTaskRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationAction implements
		ProjectTaskRelayEmailNotificationAction {

	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private AuditLogService auditLogService;

	private final ProjectFieldNameMapper mapper;

	public ProjectTaskRelayEmailNotificationActionImpl() {
		mapper = new ProjectFieldNameMapper();
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findById(taskId,
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(task.getTaskname(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$task.projectName]: Task \"" + subject + "...\" created",
				"templates/email/project/taskCreatedNotifier.mt");
		templateGenerator.putVariable("task", task);
		templateGenerator.putVariable("hyperLinks", createHyperLinks(task));
		return templateGenerator;
	}

	private Map<String, String> createHyperLinks(SimpleTask task) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				task.getProjectid());
		hyperLinks.put("taskUrl",
				linkGenerator.generateTaskPreviewFullLink(task.getId()));
		hyperLinks.put("shortTaskUrl",
				StringUtils.subString(task.getTaskname(), 150));
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks
				.put("assignUserUrl", linkGenerator
						.generateUserPreviewFullLink(task.getAssignuser()));
		hyperLinks.put("taskListUrl", linkGenerator
				.generateTaskGroupPreviewFullLink(task.getTasklistid()));
		return hyperLinks;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findById(taskId,
				emailNotification.getSaccountid());
		if (task == null) {
			return null;
		}

		String subject = StringUtils.subString(task.getTaskname(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$task.projectName]: Task \"" + subject + "...\" edited",
				"templates/email/project/taskUpdatedNotifier.mt");
		templateGenerator.putVariable("task", task);
		templateGenerator.putVariable("hyperLinks", createHyperLinks(task));

		if (emailNotification.getExtratypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findById(
					emailNotification.getExtratypeid(),
					emailNotification.getSaccountid());
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}

		return templateGenerator;
	}

	@Override
	public TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findById(taskId,
				emailNotification.getSaccountid());
		if (task == null) {
			return null;
		}
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				task.getProjectid());
		String comment = StringUtils.subString(
				emailNotification.getChangecomment(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$task.projectName]: "
						+ emailNotification.getChangeByUserFullName()
						+ " add new comment \"" + comment + "...\" to task \""
						+ StringUtils.subString(task.getTaskname(), 100) + "\"",
				"templates/email/project/taskCommentNotifier.mt");
		templateGenerator.putVariable("task", task);
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("hyperLinks", createHyperLinks(task));

		return templateGenerator;
	}

	public class ProjectFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ProjectFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("taskname", "Task Name");
			fieldNameMap.put("startdate", "Start Date");
			fieldNameMap.put("enddate", "End Date");
			fieldNameMap.put("actualstartdate", "Actual Start Date");
			fieldNameMap.put("actualenddate", "Actual End Date");
			fieldNameMap.put("assignuser", "Assigned to");
			fieldNameMap.put("percentagecomplete", "Complete (%)");
			fieldNameMap.put("notes", "Notes");
			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("deadline", "Deadline");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
