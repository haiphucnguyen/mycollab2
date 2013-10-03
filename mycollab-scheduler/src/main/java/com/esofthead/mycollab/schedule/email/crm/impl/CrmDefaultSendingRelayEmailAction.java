package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class CrmDefaultSendingRelayEmailAction implements
		SendingRelayEmailNotificationAction {

	@Autowired
	protected ExtMailService extMailService;

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCreateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getUsername();
					templateGenerator.putVariable("userName", userName);

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

	@Override
	public void sendNotificationForUpdateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForUpdateAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getUsername();
					templateGenerator.putVariable("userName", userName);

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

	@Override
	public void sendNotificationForCommentAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(notification);
		if ((notifiers != null) && !notifiers.isEmpty()) {
			TemplateGenerator templateGenerator = templateGeneratorForCommentAction(notification);
			if (templateGenerator != null) {
				for (SimpleUser user : notifiers) {
					String userName = user.getUsername();
					templateGenerator.putVariable("userName", userName);

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

	public static String getSiteUrl(int accountId) {
		String siteUrl = "";
		if (SiteConfiguration.getDeploymentMode() == DeploymentMode.SITE) {
			BillingAccountService billingAccountService = ApplicationContextUtil
					.getSpringBean(BillingAccountService.class);
			BillingAccount account = billingAccountService
					.getAccountById(accountId);
			if (account != null) {
				siteUrl = String.format(ApplicationProperties
						.getString(ApplicationProperties.APP_URL), account
						.getSubdomain());
			}
		} else {
			siteUrl = ApplicationProperties
					.getString(ApplicationProperties.APP_URL);
		}
		return siteUrl;

	}

	protected abstract TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification);

	protected abstract List<SimpleUser> getListNotififyUserWithFilter(
			SimpleRelayEmailNotification notification);

}
