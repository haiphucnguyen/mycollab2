package com.esofthead.mycollab.module.mail.service;

import java.util.List;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;

public interface SendingRelayEmailNotificationAction {
	TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers);

	TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification,
			List<SimpleUser> notifiers);
}
