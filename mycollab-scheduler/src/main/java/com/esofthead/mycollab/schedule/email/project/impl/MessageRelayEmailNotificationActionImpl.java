package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.project.service.ProjectMemberService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction;

@Service
public class MessageRelayEmailNotificationActionImpl implements
		MessageRelayEmailNotificationAction {

	@Autowired
	private MessageService messageService;

	@Autowired
	private ProjectMemberService projectMemberService;

	@Autowired
	protected ExtMailService extMailService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		int messageId = notification.getTypeid();
		int projectId = notification.getExtratypeid();
		List<SimpleUser> usersInProject = projectMemberService
				.getActiveUsersInProject(projectId, 0);

		SimpleMessage message = messageService.findMessageById(messageId, 0);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $message.fullPostedUserName sent a message \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageCreatedNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		for (SimpleUser user : usersInProject) {
			String userName = (user.getUsername() != null) ? user.getUsername()
					: user.getFirstname() + " " + user.getLastname();
			templateGenerator.putVariable("userName", userName);

			MailRecipientField userMail = new MailRecipientField(
					user.getEmail(), user.getUsername());
			List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
			lst.add(userMail);

			extMailService.sendHTMLMail("mail@esofthead.com",
					notification.getChangeByUserFullName(), lst, null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);
		}

	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		int messageId = notification.getTypeid();
		int projectId = notification.getExtratypeid();
		List<SimpleUser> usersInProject = projectMemberService
				.getActiveUsersInProject(projectId, 0);

		SimpleMessage message = messageService.findMessageById(messageId, 0);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $message.fullPostedUserName posted a new message \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageUpdatedNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		for (SimpleUser user : usersInProject) {
			String userName = (user.getUsername() != null) ? user.getUsername()
					: user.getFirstname() + " " + user.getLastname();
			templateGenerator.putVariable("userName", userName);

			MailRecipientField userMail = new MailRecipientField(
					user.getEmail(), user.getUsername());
			List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
			lst.add(userMail);

			extMailService.sendHTMLMail("mail@esofthead.com",
					notification.getChangeByUserFullName(), lst, null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);
		}
	}

	private Map<String, String> constructHyperLinks(SimpleMessage message) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				message.getProjectid());
		hyperLinks.put("messageUrl",
				linkGenerator.generateMessagePreviewFullLink(message.getId()));
		hyperLinks.put("shortMessageUrl",
				StringUtils.subString(message.getTitle(), 150));
		hyperLinks.put("projectUrl", linkGenerator.generateProjectFullLink());
		hyperLinks.put("createdUserUrl", linkGenerator
				.generateUserPreviewFullLink(message.getPosteduser()));

		return hyperLinks;
	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		int messageId = notification.getTypeid();
		SimpleMessage message = messageService.findMessageById(messageId, 0);
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				message.getProjectid());

		Integer projectid = message.getProjectid();
		List<SimpleUser> usersInProject = projectMemberService
				.getActiveUsersInProject(projectid, 0);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $!message.fullPostedUserName has commented on \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageCommentNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("comment", notification);
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(notification.getChangeby()));
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		for (SimpleUser user : usersInProject) {
			String userName = (user.getUsername() != null) ? user.getUsername()
					: user.getFirstname() + " " + user.getLastname();
			templateGenerator.putVariable("userName", userName);

			MailRecipientField userMail = new MailRecipientField(
					user.getEmail(), user.getUsername());
			List<MailRecipientField> lst = new ArrayList<MailRecipientField>();
			lst.add(userMail);

			extMailService.sendHTMLMail("mail@esofthead.com",
					notification.getChangeByUserFullName(), lst, null, null,
					templateGenerator.generateSubjectContent(),
					templateGenerator.generateBodyContent(), null);
		}

	}

}
