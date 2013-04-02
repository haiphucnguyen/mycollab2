package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.view.ProjectLinkGenerator;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugNotificationService;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.accountsettings.view.AccountLinkGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.utils.StringUtils;

@Service
public class BugNotificationServiceImpl implements BugNotificationService {
	@Autowired
	private BugService bugService;
	@Autowired
	private AuditLogService auditLogService;

	private final BugFieldNameMapper mapper;

	public BugNotificationServiceImpl() {
		mapper = new BugFieldNameMapper();
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		int taskId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findBugById(taskId);

		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"bugUrl",
				ProjectLinkGenerator.generateBugPreviewFullLink(
						bug.getProjectid(), bug.getId()));
		hyperLinks.put("projectUrl", ProjectLinkGenerator
				.generateProjectFullLink(bug.getProjectid()));
		hyperLinks.put("loggedUserUrl", AccountLinkGenerator
				.generateUserPreviewFullLink(bug.getLogby()));
		hyperLinks.put("assignUserUrl", AccountLinkGenerator
				.generateUserPreviewFullLink(bug.getAssignuser()));
		hyperLinks.put(
				"milestoneUrl",
				ProjectLinkGenerator.generateMilestonePreviewFullLink(
						bug.getProjectid(), bug.getMilestoneid()));

		String subject = StringUtils.subString(bug.getSummary(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: Bug \"" + subject + "...\" created",
				"templates/email/project/bugCreatedNotifier.mt");
		templateGenerator.putVariable("bug", bug);
		templateGenerator.putVariable("hyperLinks", hyperLinks);
		return templateGenerator;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		int taskId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findBugById(taskId);
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"bugUrl",
				ProjectLinkGenerator.generateBugPreviewFullLink(
						bug.getProjectid(), bug.getId()));
		hyperLinks.put("projectUrl", ProjectLinkGenerator
				.generateProjectFullLink(bug.getProjectid()));
		hyperLinks.put("loggedUserUrl", AccountLinkGenerator
				.generateUserPreviewFullLink(bug.getLogby()));
		hyperLinks.put("assignUserUrl", AccountLinkGenerator
				.generateUserPreviewFullLink(bug.getAssignuser()));
		hyperLinks.put(
				"milestoneUrl",
				ProjectLinkGenerator.generateMilestonePreviewFullLink(
						bug.getProjectid(), bug.getMilestoneid()));

		String subject = StringUtils.subString(bug.getSummary(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: Bug \"" + subject + "...\" updated",
				"templates/email/project/bugUpdatedNotifier.mt");
		templateGenerator.putVariable("bug", bug);
		templateGenerator.putVariable("hyperLinks", hyperLinks);

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
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	public class BugFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		BugFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("summary", "Bug Summary");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("assignuser", "Assigned to");
			fieldNameMap.put("resolution", "Resolution");
			fieldNameMap.put("severity", "Serverity");
			fieldNameMap.put("environment", "Environment");
			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("duedate", "Due Date");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}
}
