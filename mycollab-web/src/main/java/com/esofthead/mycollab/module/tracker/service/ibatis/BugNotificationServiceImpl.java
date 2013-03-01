package com.esofthead.mycollab.module.tracker.service.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.module.mail.SendingRelayEmailNotificationTemplate;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.tracker.domain.SimpleBug;
import com.esofthead.mycollab.module.tracker.service.BugService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.INotificationSchedulable;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
public class BugNotificationServiceImpl implements INotificationSchedulable,
		SendingRelayEmailNotificationAction {
	@Autowired
	private BugService bugService;
	@Autowired
	private AuditLogService auditLogService;

	private final BugFieldNameMapper mapper;

	public BugNotificationServiceImpl() {
		mapper = new BugFieldNameMapper();
	}

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	@MonitoredWithSpring
	@Override
	public void runNotification() {
		new SendingRelayEmailNotificationTemplate(this)
				.run(MonitorTypeConstants.PRJ_BUG);
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		int taskId = emailNotification.getTypeid();
		SimpleBug bug = bugService.findBugById(taskId);

		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put("bugUrl", "#");
		hyperLinks.put("projectUrl", "#");
		hyperLinks.put("loggedUserUrl", "#");
		hyperLinks.put("assignUserUrl", "#");
		hyperLinks.put("milestoneUrl", "#");

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: New bug created",
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
		hyperLinks.put("bugUrl", "#");
		hyperLinks.put("projectUrl", "#");
		hyperLinks.put("loggedUserUrl", "#");
		hyperLinks.put("assignUserUrl", "#");
		hyperLinks.put("milestoneUrl", "#");

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$bug.projectname]: Bug updated",
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
