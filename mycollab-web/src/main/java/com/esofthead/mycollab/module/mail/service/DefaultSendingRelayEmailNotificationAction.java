package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public abstract class DefaultSendingRelayEmailNotificationAction implements
		SendingRelayEmailNotificationAction {
	@Autowired
	protected ExtMailService extMailService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = notification.getNotifyUsers();
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				extMailService.sendHTMLMail("mail@esofthead.com",
						notification.getChangeByUserFullName(), notifiers,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		}

	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = notification.getNotifyUsers();
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForUpdateAction(notification);
			if (templateGenerator != null) {
				extMailService.sendHTMLMail("mail@esofthead.com",
						notification.getChangeByUserFullName(), notifiers,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		}

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = notification.getNotifyUsers();
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCommentAction(notification);
			if (templateGenerator != null) {
				extMailService.sendHTMLMail("mail@esofthead.com",
						notification.getChangeByUserFullName(), notifiers,
						templateGenerator.generateSubjectContent(),
						templateGenerator.generateBodyContent(), null);
			}
		}

	}

	protected abstract TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification);
}
