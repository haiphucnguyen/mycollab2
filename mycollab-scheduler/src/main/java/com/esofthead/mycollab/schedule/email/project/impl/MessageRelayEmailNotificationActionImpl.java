package com.esofthead.mycollab.schedule.email.project.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.schedule.email.project.MailLinkGenerator;
import com.esofthead.mycollab.schedule.email.project.MessageRelayEmailNotificationAction;

@Service
public class MessageRelayEmailNotificationActionImpl extends
		SendMailToAllMembersAction implements
		MessageRelayEmailNotificationAction {

	@Autowired
	private MessageService messageService;

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
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int messageId = emailNotification.getTypeid();
		SimpleMessage message = messageService.findMessageById(messageId,
				emailNotification.getSaccountid());
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $message.fullPostedUserName sent a message \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageCreatedNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		int messageId = emailNotification.getTypeid();
		SimpleMessage message = messageService.findMessageById(messageId,
				emailNotification.getSaccountid());
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $message.fullPostedUserName posted a new message \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageUpdatedNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int messageId = emailNotification.getTypeid();
		SimpleMessage message = messageService.findMessageById(messageId,
				emailNotification.getSaccountid());
		MailLinkGenerator linkGenerator = new MailLinkGenerator(
				message.getProjectid());
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $!message.fullPostedUserName has commented on \""
						+ message.getTitle() + "\"",
				"templates/email/project/messageCommentNotifier.mt");
		templateGenerator.putVariable("message", message);
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", linkGenerator
				.generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(message));
		return templateGenerator;
	}

}
