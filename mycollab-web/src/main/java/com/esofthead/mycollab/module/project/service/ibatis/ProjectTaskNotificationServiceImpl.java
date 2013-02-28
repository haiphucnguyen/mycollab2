package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bull.javamelody.MonitoredWithSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.SendingRelayEmailNotificationTemplate;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.project.domain.SimpleTask;
import com.esofthead.mycollab.module.project.service.ProjectTaskService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.INotificationSchedulable;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
public class ProjectTaskNotificationServiceImpl implements
		INotificationSchedulable, SendingRelayEmailNotificationAction {

	@Autowired
	private ProjectTaskService projectTaskService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	@MonitoredWithSpring
	@Override
	public void runNotification() {
		new SendingRelayEmailNotificationTemplate(this)
				.run(MonitorTypeConstants.PRJ_TASK);
	}

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		int taskId = emailNotification.getTypeid();
		SimpleTask task = projectTaskService.findTaskById(taskId);

		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put("taskUrl", "#");
		hyperLinks.put("projectUrl", "#");
		hyperLinks.put("assignUserUrl", "#");
		hyperLinks.put("taskListUrl", "#");

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$task.projectName]: Task $task.taskname created",
				"templates/email/project/taskCreatedNotifier.mt");
		templateGenerator.putVariable("task", task);
		templateGenerator.putVariable("hyperLinks", hyperLinks);
		return templateGenerator;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		// TODO Auto-generated method stub
		return null;
	}

}
