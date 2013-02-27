package com.esofthead.mycollab.module.mail;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.mail.service.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.mail.service.SystemMailService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.web.AppContext;

public class SendingRelayEmailNotificationTemplate {
	private SendingRelayEmailNotificationAction sendingAction;

	private static ApplicationContext springContext;
	private RelayEmailNotificationService relayEmailNotificationService;

	static {
		springContext = AppContext.getSpringContext();
		if (springContext == null) {
			springContext = new ClassPathXmlApplicationContext(
					"META-INF/spring/datasource-context.xml",
					"META-INF/spring/core-context.xml",
					"META-INF/spring/common-context.xml",
					"META-INF/spring/crm-context.xml",
					"META-INF/spring/file-context.xml",
					"META-INF/spring/project-context.xml",
					"META-INF/spring/tracker-context.xml",
					"META-INF/spring/user-context.xml");
		}
	}

	public SendingRelayEmailNotificationTemplate(
			SendingRelayEmailNotificationAction sendingAction) {
		this.sendingAction = sendingAction;

		relayEmailNotificationService = springContext
				.getBean(RelayEmailNotificationService.class);
	}

	public void run() {
		List<SimpleRelayEmailNotification> relayEmaiNotifications = filterMailRelayNotification(MonitorTypeConstants.PRJ_TASK);
		sendRelayEmailNotifications(relayEmaiNotifications);
	}

	private List<SimpleRelayEmailNotification> filterMailRelayNotification(
			String type) {
		RelayEmailNotificationSearchCriteria criteria = new RelayEmailNotificationSearchCriteria();
		criteria.setType(new StringSearchField(type));
		List<SimpleRelayEmailNotification> relayEmaiNotifications = relayEmailNotificationService
				.findPagableListByCriteria(new SearchRequest<RelayEmailNotificationSearchCriteria>(
						criteria, 0, Integer.MAX_VALUE));
		relayEmailNotificationService.removeByCriteria(criteria);
		return relayEmaiNotifications;
	}

	private void sendRelayEmailNotifications(
			List<SimpleRelayEmailNotification> relayEmaiNotifications) {
		for (SimpleRelayEmailNotification emailNotification : relayEmaiNotifications) {
			try {
				List<SimpleUser> notifiers = emailNotification.getNotifyUsers();
				if (notifiers != null && !notifiers.isEmpty()) {
					TemplateGenerator templateGenerator = null;
					if (MonitorTypeConstants.CREATE_ACTION
							.equals(emailNotification.getAction())) {
						templateGenerator = sendingAction
								.sendRelayEmailNotificationForCreateAction(
										emailNotification, notifiers);

					} else if (MonitorTypeConstants.UPDATE_ACTION
							.equals(emailNotification.getAction())) {
					}

					if (templateGenerator != null) {
						SystemMailService mailService = springContext
								.getBean(SystemMailService.class);
						mailService.sendHTMLMail("mail@esofthead.com",
								emailNotification.getChangeByUserFullName(),
								notifiers,
								templateGenerator.generateSubjectContent(),
								templateGenerator.generateBodyContent());
					}
				}

			} catch (Exception e) {

			}
		}
	}

	public static void main(String[] args) {
		ClassPathXmlApplicationContext springContext = new ClassPathXmlApplicationContext(
				"META-INF/spring/datasource-context.xml",
				"META-INF/spring/core-context.xml",
				"META-INF/spring/common-context.xml",
				"META-INF/spring/crm-context.xml",
				"META-INF/spring/file-context.xml",
				"META-INF/spring/project-context.xml",
				"META-INF/spring/tracker-context.xml",
				"META-INF/spring/user-context.xml");
		System.out.println(springContext
				.getBean(RelayEmailNotificationService.class));
	}

}
