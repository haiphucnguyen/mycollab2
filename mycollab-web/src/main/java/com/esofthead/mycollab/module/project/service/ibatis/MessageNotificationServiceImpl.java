package com.esofthead.mycollab.module.project.service.ibatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.service.MessageNotificationService;
import com.esofthead.mycollab.module.project.service.MessageService;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

@Service
public class MessageNotificationServiceImpl implements
		MessageNotificationService {

	@Autowired
	private MessageService messageService;

	@Override
	public TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers) {
		// TODO Auto-generated method stub
		return null;
	}

}
