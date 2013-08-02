package com.esofthead.mycollab.schedule.email;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;

public interface SendingRelayEmailNotificationAction {
	void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification);

	void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification);

	void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification);
}
