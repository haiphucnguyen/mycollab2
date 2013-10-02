package com.esofthead.mycollab.schedule.email.crm.impl;

import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.schedule.email.crm.AccountRelayEmailNotificationAction;

@Component
public class AccountRelayEmailNotificationActionImpl implements
		AccountRelayEmailNotificationAction {

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub
		System.out.print("");
	}

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		// TODO Auto-generated method stub

	}

}
