package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.domain.SimpleMessage;
import com.esofthead.mycollab.module.project.service.MessageNotificationService;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

@Service
public class MessageNotificationServiceImpl implements
		MessageNotificationService {

	@Autowired
	private MessageService messageService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		int messageId = notification.getTypeid();
		SimpleMessage message = messageService.findMessageById(messageId);
		TemplateGenerator templateGenerator = new TemplateGenerator(
				"[$message.projectName]: $message.fullPostedUserName sent a message \""
						+ message.getTitle() + "...\"",
				"templates/email/project/taskCreatedNotifier.mt");
		templateGenerator.putVariable("message", message);

	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

}
