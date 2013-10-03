package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleTask;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.TaskService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.crm.CrmLinkGenerator;
import com.esofthead.mycollab.schedule.email.crm.TaskRelayEmailNotificationAction;

@Component
public class TaskRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		TaskRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private TaskService taskService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final TaskFieldNameMapper mapper;

	public TaskRelayEmailNotificationActionImpl() {
		mapper = new TaskFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleTask simpleTask = taskService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleTask != null) {
			String subject = StringUtils
					.subString(simpleTask.getSubject(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"CRM-Task: \"" + subject + "\" has been created",
					"templates/email/crm/crmTaskCreatedNotifier.mt");

			templateGenerator.putVariable("simpleTask", simpleTask);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleTask));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(SimpleTask simpleTask) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"taskURL",
				getSiteUrl(simpleTask.getSaccountid())
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.TASK, simpleTask.getId()));

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleTask simpleTask = taskService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(simpleTask.getSubject(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"CRM-Task: \"" + subject + "...\" has been updated",
				"templates/email/crm/crmTaskUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleTask", simpleTask);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleTask));

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
		SimpleTask simpleTask = taskService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[CRM-Task]" + emailNotification.getChangeByUserFullName()
						+ " has commented on "
						+ StringUtils.subString(simpleTask.getSubject(), 100)
						+ "\"", "templates/email/crm/crmTaskAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable(
				"userComment",
				getSiteUrl(emailNotification.getSaccountid())
						+ ProjectLinkUtils.URL_PREFIX_PARAM
						+ "account/user/preview/"
						+ UrlEncodeDecoder.encode(emailNotification
								.getChangeby()));
		templateGenerator.putVariable("simpleTask", simpleTask);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleTask));

		return templateGenerator;
	}

	@Override
	protected List<SimpleUser> getListNotififyUserWithFilter(
			SimpleRelayEmailNotification notification) {
		List<CrmNotificationSetting> notificationSettings = notificationService
				.findNotifications(notification.getChangeby(),
						notification.getSaccountid());

		List<SimpleUser> inListUsers = notification.getNotifyUsers();

		// TODO: MORE CODE FILTER
		return inListUsers;
	}

	public class TaskFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		TaskFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("subject", "Subject");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("startdate", "Start Date");
			fieldNameMap.put("relatedTo", "Related To");
			fieldNameMap.put("duedate", "Due Date");
			fieldNameMap.put("contactName", "Contact");
			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("assignuser", "Assignee");
			fieldNameMap.put("description", "Description");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
