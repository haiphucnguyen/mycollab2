package com.esofthead.mycollab.schedule.jobs;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.BeanUtility;
import com.esofthead.mycollab.module.project.domain.ProjectRelayEmailNotification;
import com.esofthead.mycollab.module.project.service.ProjectService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

@Component
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class ProjectSendingRelayEmailNotificationJob extends QuartzJobBean {
	private static Logger log = LoggerFactory
			.getLogger(ProjectSendingRelayEmailNotificationJob.class);

	@Override
	protected void executeInternal(JobExecutionContext context) {
		ProjectService projectService = (ProjectService) ApplicationContextUtil
				.getSpringBean(ProjectService.class);
		List<ProjectRelayEmailNotification> relayEmaiNotifications = projectService
				.findProjectRelayEmailNotifications();

		RelayEmailNotificationService relayNotificationService = ApplicationContextUtil
				.getSpringBean(RelayEmailNotificationService.class);
		log.debug("Get " + relayEmaiNotifications.size()
				+ " relay email notifications");
		SendingRelayEmailNotificationAction emailNotificationAction = null;

		for (ProjectRelayEmailNotification notification : relayEmaiNotifications) {
			try {
				if (notification.getEmailhandlerbean() != null
						&& notification.getType().startsWith("Project")) {
					emailNotificationAction = (SendingRelayEmailNotificationAction) ApplicationContextUtil
							.getSpringBean(Class.forName(notification
									.getEmailhandlerbean()));

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

							log.debug("Finish process notification {}",
									BeanUtility.printBeanObj(notification));
							relayNotificationService.removeWithSession(
									notification.getId(), "",
									notification.getSaccountid());

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
}
