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
import com.esofthead.mycollab.core.utils.BeanUtility;
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
			System.out.println("Log of bug: "
					+ BeanUtility.printBeanObj(auditLog));
		}
		return templateGenerator;
	}

}
