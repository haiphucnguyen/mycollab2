package com.esofthead.mycollab.schedule.email;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public abstract class DefaultSendingRelayEmailNotificationForProjectAction
		implements SendingRelayEmailNotificationAction {
	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	protected ProjectMemberService projectMemberService;

	/**
	 * @see Remember add extratypeID = projectID for action relay email for any
	 *      Project Action
	 * 
	 */
	protected List<SimpleUser> getNotifyUsers(
			SimpleRelayEmailNotification notification) {

		List<SimpleUser> usersInProject = projectMemberService
				.getUsersInProject(notification.getExtratypeid(), 0);
		return usersInProject;
	}

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getNotifyUsers(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = "";
					try {
						userName = user.getFirstname() + " "
								+ user.getLastname();
					} catch (Exception e) {
						userName = user.getUsername();
					}
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("mail@esofthead.com",
							notification.getChangeByUserFullName(), lst, null,
							null, templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
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
					String userName = "";
					try {
						userName = user.getFirstname() + " "
								+ user.getLastname();
					} catch (Exception e) {
						userName = user.getUsername();
					}
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("mail@esofthead.com",
							notification.getChangeByUserFullName(), lst, null,
							null, templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
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
					String userName = "";
					try {
						userName = user.getFirstname() + " "
								+ user.getLastname();
					} catch (Exception e) {
						userName = user.getUsername();
					}
					templateGenerator.putVariable("userName", userName);

					MailRecipientField userMail = new MailRecipientField(
							user.getEmail(), user.getUsername());
					List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
					lst.add(userMail);

					extMailService.sendHTMLMail("mail@esofthead.com",
							notification.getChangeByUserFullName(), lst, null,
							null, templateGenerator.generateSubjectContent(),
							templateGenerator.generateBodyContent(), null);
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
}
