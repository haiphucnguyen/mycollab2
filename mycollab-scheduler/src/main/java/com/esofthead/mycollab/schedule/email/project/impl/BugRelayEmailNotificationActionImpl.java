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
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.schedule.email.DefaultSendingRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.project.BugRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;

@Component
public class BugRelayEmailNotificationActionImpl extends
		DefaultSendingRelayEmailNotificationAction implements
		BugRelayEmailNotificationAction {
	@Autowired
	private BugService bugService;
	@Autowired
	private AuditLogService auditLogService;

	private final BugFieldNameMapper mapper;

	public BugRelayEmailNotificationActionImpl() {
		mapper = new BugFieldNameMapper();
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int bugId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findById(bugId, 0);
		if (bug != null) {
			String subject = bug.getSummary();

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"[$bug.projectname]: Bug \"" + subject
							+ "\" has been created",
					"templates/email/project/bugCreatedNotifier.mt");

			templateGenerator.putVariable("bug", bug);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(bug));
			return templateGenerator;
		} else {
			return null;
		}

	}

	private Map<String, String> constructHyperLinks(SimpleBug bug) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				bug.getProjectid());

		hyperLinks.put("bugUrl",
				linkGenerator.generateBugPreviewFullLink(bug.getId()));
		hyperLinks.put("shortBugUrl",
				StringUtils.subString(bug.getSummary(), 150));
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("loggedUserUrl",
				linkGenerator.generateUserPreviewFullLink(bug.getLogby()));
		hyperLinks.put("assignUserUrl",
				linkGenerator.generateUserPreviewFullLink(bug.getAssignuser()));
		hyperLinks.put("milestoneUrl", linkGenerator
				.generateMilestonePreviewFullLink(bug.getMilestoneid()));
		return hyperLinks;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int bugId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findById(bugId, 0);

		String subject = StringUtils.subString(bug.getSummary(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: Bug \"" + subject
						+ "...\" has been updated",
				"templates/email/project/bugUpdatedNotifier.mt");
		templateGenerator.putVariable("bug", bug);
		templateGenerator.putVariable("hyperLinks", constructHyperLinks(bug));

		if (emailNotification.getExtratypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findById(
					emailNotification.getExtratypeid(), 0);
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	public TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int bugId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findById(bugId, 0);
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				bug.getProjectid());

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: "
						+ emailNotification.getChangeByUserFullName()
						+ " has commented on "
						+ StringUtils.subString(bug.getSummary(), 100) + "\"",
				"templates/email/project/bugCommentNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("bug", bug);
		templateGenerator.putVariable("hyperLinks", constructHyperLinks(bug));

		return templateGenerator;
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
