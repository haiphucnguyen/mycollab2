package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.DefaultSendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskNotificationService;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.project.view.ProjectLinkGenerator;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountLinkGenerator;
import com.esofthead.mycollab.utils.StringUtils;

@Service
public class ProjectTaskNotificationServiceImpl extends
		DefaultSendingRelayEmailNotificationAction implements
		ProjectTaskNotificationService {

	@Autowired
	private ProjectTaskService projectTaskService;
	@Autowired
	private AuditLogService auditLogService;

	private final ProjectFieldNameMapper mapper;

	public ProjectTaskNotificationServiceImpl() {
		mapper = new ProjectFieldNameMapper();
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findTaskById(taskId);

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
		hyperLinks.put(
				"taskUrl",
				ProjectLinkGenerator.generateTaskPreviewFullLink(
						task.getProjectid(), task.getId()));
		hyperLinks.put("shortTaskUrl",
				StringUtils.subString(task.getTaskname(), 150));
		hyperLinks.put("projectUrl", ProjectLinkGenerator
				.generateProjectFullLink(task.getProjectid(),
						ProjectLinkGenerator.URL_PREFIX_PARAM));
		hyperLinks.put("assignUserUrl", AccountLinkGenerator
				.generateUserPreviewFullLink(task.getAssignuser()));
		hyperLinks.put(
				"taskListUrl",
				ProjectLinkGenerator.generateTaskGroupPreviewFullLink(
						task.getProjectid(), task.getTasklistid()));
		return hyperLinks;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findTaskById(taskId);
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
			SimpleAuditLog auditLog = auditLogService
					.findById(emailNotification.getExtratypeid());
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}

		return templateGenerator;
	}

	@Override
	public TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findTaskById(taskId);
		if (task == null) {
			return null;
		}
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
		templateGenerator.putVariable("userComment", AccountLinkGenerator
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
