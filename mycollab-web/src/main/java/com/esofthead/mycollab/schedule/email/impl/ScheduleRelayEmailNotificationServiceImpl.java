package com.esofthead.mycollab.schedule.email.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.schedule.email.ScheduleConfig;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Service
public class ScheduleRelayEmailNotificationServiceImpl {
	private static Logger log = LoggerFactory
			.getLogger(ScheduleRelayEmailNotificationServiceImpl.class);

	@Scheduled(fixedDelay = ScheduleConfig.RUN_EMAIL_NOTIFICATION_INTERVAL)
	public void runNotification() {
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		RelayEmailNotificationService relayEmailNotificationService = (RelayEmailNotificationService) getSpringBean(RelayEmailNotificationService.class);
		List<SimpleRelayEmailNotification> relayEmaiNotifications = relayEmailNotificationService
				.findPagableListByCriteria(new SearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		log.debug("Get " + relayEmaiNotifications.size()
				+ " relay email notifications");
		SendingRelayEmailNotificationAction emailNotificationAction = null;

		for (SimpleRelayEmailNotification notification : relayEmaiNotifications) {

			try {
				if (notification.getEmailhandlerbean() != null) {
					emailNotificationAction = (SendingRelayEmailNotificationAction) getSpringBean(Class
							.forName(notification.getEmailhandlerbean()));

					if (emailNotificationAction != null) {
						try {
							if (MonitorTypeConstants.CREATE_ACTION
									.equals(notification.getAction())) {
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
							log.error("Error when sending notification email",
									e);
						}
					}
				}

			} catch (ClassNotFoundException ex) {
				throw new MyCollabException("no class found toget spring bean "
						+ ex.getMessage());
			}

		}
	}

	@SuppressWarnings("unchecked")
	private static Object getSpringBean(Class clazz) {
		ApplicationContext context = ApplicationContextUtil
				.getApplicationContext();
		return context.getBean(clazz);
	}
}
