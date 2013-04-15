package com.esofthead.mycollab.schedule.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.mail.service.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.project.service.MessageNotificationService;
import com.esofthead.mycollab.module.project.service.ProjectTaskNotificationService;
import com.esofthead.mycollab.module.tracker.service.BugNotificationService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.ScheduleConfig;

@Service
public class ScheduleRelayEmailNotificationServiceImpl {
	private static Logger log = LoggerFactory
			.getLogger(ScheduleRelayEmailNotificationServiceImpl.class);

	@Autowired
	private RelayEmailNotificationService relayEmailNotificationService;

	@Autowired
	private ProjectTaskNotificationService projectTaskNotificationService;

	@Autowired
	private MessageNotificationService messageNotificationService;

	@Autowired
	private BugNotificationService bugNotificationService;

	@Autowired
	private ExtMailService extMailService;

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	public void runNotification() {
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		List<SimpleRelayEmailNotification> relayEmaiNotifications = relayEmailNotificationService
				.findPagableListByCriteria(new SearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		log.debug("Get " + relayEmaiNotifications.size()
				+ " relay email notifications");
		SendingRelayEmailNotificationAction emailNotificationAction = null;

		for (SimpleRelayEmailNotification notification : relayEmaiNotifications) {
			if (MonitorTypeConstants.PRJ_BUG.equals(notification.getType())) {
				emailNotificationAction = bugNotificationService;
			} else if (MonitorTypeConstants.PRJ_TASK.equals(notification
					.getType())) {
				emailNotificationAction = projectTaskNotificationService;
			} else if (MonitorTypeConstants.PRJ_MESSAGE.equals(notification
					.getType())) {
				emailNotificationAction = messageNotificationService;
			} else {
				log.error("Do not support monitor type "
						+ notification.getType());
			}

			try {
				if (MonitorTypeConstants.CREATE_ACTION.equals(notification
						.getAction())) {
					emailNotificationAction
							.sendNotificationForCreateAction(notification);
				} else if (MonitorTypeConstants.UPDATE_ACTION
						.equals(notification.getAction())) {
					emailNotificationAction
							.sendNotificationForUpdateAction(notification);
				} else if (MonitorTypeConstants.ADD_COMMENT_ACTION
						.equals(notification.getAction())) {
					emailNotificationAction
							.sendNotificationForCommentAction(notification);
				}

				relayEmailNotificationService.removeWithSession(
						notification.getId(), "");

			} catch (Exception e) {
				log.error("Error when sending notification email", e);
			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"META-INF/spring/*-context.xml");
		System.out.println(context.getBean(UserService.class));
	}
}
