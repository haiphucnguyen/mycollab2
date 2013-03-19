package com.esofthead.mycollab.module.mail;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.domain.criteria.RelayEmailNotificationSearchCriteria;
import com.esofthead.mycollab.common.service.RelayEmailNotificationService;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.mail.service.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.web.AppContext;

public class SendingRelayEmailNotificationTemplate {

	private static Logger log = LoggerFactory
			.getLogger(SendingRelayEmailNotificationTemplate.class);
	private static ApplicationContext springContext;
	private final SendingRelayEmailNotificationAction sendingAction;
	private final RelayEmailNotificationService relayEmailNotificationService;

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

	public void run(String type) {
		List<SimpleRelayEmailNotification> relayEmaiNotifications = filterMailRelayNotification(type);
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
								.templateGeneratorForCreateAction(
										emailNotification, notifiers);

					} else if (MonitorTypeConstants.UPDATE_ACTION
							.equals(emailNotification.getAction())) {
						templateGenerator = sendingAction
								.templateGeneratorForUpdateAction(
										emailNotification, notifiers);
					} else if (MonitorTypeConstants.ADD_COMMENT_ACTION
							.equals(emailNotification.getAction())) {
						templateGenerator = sendingAction
								.templateGeneratorForCommentAction(
										emailNotification, notifiers);
					}

					if (templateGenerator != null) {
						ExtMailService mailService = springContext
								.getBean(ExtMailService.class);
						mailService.sendHTMLMail("mail@esofthead.com",
								emailNotification.getChangeByUserFullName(),
								notifiers,
								templateGenerator.generateSubjectContent(),
								templateGenerator.generateBodyContent(), null);
					}
				}

			} catch (Exception e) {
				log.error("Error when sending notification email", e);
				relayEmailNotificationService.saveWithSession(
						emailNotification, "");
			}
		}
	}
}
