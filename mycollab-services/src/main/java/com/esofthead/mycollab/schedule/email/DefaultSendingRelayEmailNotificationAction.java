package com.esofthead.mycollab.schedule.email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.ProjectNotificationSetting;
import com.esofthead.mycollab.module.project.service.ProjectNotificationSettingService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class DefaultSendingRelayEmailNotificationAction implements
		SendingRelayEmailNotificationAction {

	@Autowired
	protected ExtMailService extMailService;

	protected List<SimpleUser> getNotifyUsers(
			SimpleRelayEmailNotification notification) {
		return notification.getNotifyUsers();
	}

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {

					if (NofiticationSendingEmailChecking.isNeedSendingEmail(
							user.getUsername(), notification.getExtratypeid(),
							user.getAccountId())) {
						templateGenerator.putVariable("userName",
								user.getDisplayName());

						MailRecipientField userMail = new MailRecipientField(
								user.getEmail(), user.getUsername());
						List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
						lst.add(userMail);

						extMailService.sendHTMLMail("noreply@esofthead.com",
								"noreply@esofthead.com", lst, null, null,
								templateGenerator.generateSubjectContent(),
								templateGenerator.generateBodyContent(), null);
					}
				}
			}
		}
	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForUpdateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					if (NofiticationSendingEmailChecking.isNeedSendingEmail(
							user.getUsername(), notification.getExtratypeid(),
							user.getAccountId())) {
						templateGenerator.putVariable("userName",
								user.getDisplayName());

						MailRecipientField userMail = new MailRecipientField(
								user.getEmail(), user.getUsername());
						List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
						lst.add(userMail);

						extMailService.sendHTMLMail("noreply@esofthead.com",
								"noreply@esofthead.com", lst, null, null,
								templateGenerator.generateSubjectContent(),
								templateGenerator.generateBodyContent(), null);
					}
				}
			}
		}

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCommentAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {

					if (NofiticationSendingEmailChecking.isNeedSendingEmail(
							user.getUsername(), notification.getExtratypeid(),
							user.getAccountId())) {
						templateGenerator.putVariable("userName",
								user.getDisplayName());

						MailRecipientField userMail = new MailRecipientField(
								user.getEmail(), user.getUsername());
						List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
						lst.add(userMail);

						extMailService.sendHTMLMail("noreply@esofthead.com",
								"noreply@esofthead.com", lst, null, null,
								templateGenerator.generateSubjectContent(),
								templateGenerator.generateBodyContent(), null);
					}
				}
			}
		}
	}

	protected abstract TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification);

	public static class NofiticationSendingEmailChecking {
		public static boolean isNeedSendingEmail(String username,
				Integer projectId, Integer sAccountId) {
			ProjectNotificationSettingService projectNotificationService = ApplicationContextUtil
					.getSpringBean(ProjectNotificationSettingService.class);

			ProjectNotificationSetting noficationSetting = projectNotificationService
					.findNotification(username, projectId, sAccountId);
			if (noficationSetting != null
					&& noficationSetting.getLevel().equals("None")) {
				return false;
			}
			return true;
		}
	}
}
