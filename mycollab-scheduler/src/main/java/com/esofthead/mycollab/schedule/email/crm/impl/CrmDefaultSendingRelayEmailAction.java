package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esofthead.mycollab.common.MonitorTypeConstants;
import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.esofthead.mycollab.core.arguments.NumberSearchField;
import com.esofthead.mycollab.core.arguments.SearchRequest;
import com.esofthead.mycollab.core.arguments.StringSearchField;
import com.esofthead.mycollab.core.utils.ValuedBean;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleNote;
import com.esofthead.mycollab.module.crm.domain.criteria.NoteSearchCriteria;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.crm.service.NoteService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.user.domain.BillingAccount;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.module.user.service.BillingAccountService;
import com.esofthead.mycollab.module.user.service.UserService;
import com.esofthead.mycollab.schedule.email.SendingRelayEmailNotificationAction;
import com.esofthead.mycollab.spring.ApplicationContextUtil;

public abstract class CrmDefaultSendingRelayEmailAction<B extends ValuedBean>
		implements SendingRelayEmailNotificationAction {

	@Autowired
	protected ExtMailService extMailService;

	@Autowired
	protected NoteService noteService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected CrmNotificationSettingService notificationService;

	protected String crmType;

	public CrmDefaultSendingRelayEmailAction(String crmType) {
		this.crmType = crmType;
	}

	@Override
	public void sendNotificationForCreateAction(
			SimpleRelayEmailNotification notification) {
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(
				notification, MonitorTypeConstants.CREATE_ACTION);
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
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(
				notification, MonitorTypeConstants.UPDATE_ACTION);
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
		List<SimpleUser> notifiers = getListNotififyUserWithFilter(
				notification, MonitorTypeConstants.ADD_COMMENT_ACTION);
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

	protected List<SimpleUser> getListNotififyUserWithFilter(
			SimpleRelayEmailNotification notification, String type) {
		List<CrmNotificationSetting> notificationSettings = notificationService
				.findNotifications(notification.getSaccountid());
		List<SimpleUser> inListUsers = notification.getNotifyUsers();
		// If an user has add notes to Item , So we must include him to list
		// "need sending notifications"
		// ---------------------------------------------------------------
		NoteSearchCriteria noteSearchCriteria = new NoteSearchCriteria();
		noteSearchCriteria.setType(new StringSearchField(crmType));
		noteSearchCriteria.setTypeid(new NumberSearchField(notification
				.getTypeid()));
		noteSearchCriteria.setSaccountid(new NumberSearchField(notification
				.getSaccountid()));
		List<SimpleNote> lstNote = noteService
				.findPagableListByCriteria(new SearchRequest<NoteSearchCriteria>(
						noteSearchCriteria, 0, Integer.MAX_VALUE));
		if (lstNote != null && lstNote.size() > 0) {
			for (SimpleNote note : lstNote) {
				if (note.getCreateduser() != null) {
					SimpleUser user = userService.findUserByUserNameInAccount(
							note.getCreateduser(), note.getSaccountid());
					if (user != null
							&& checkExistInList(inListUsers, user) == false) {
						inListUsers.add(user);
					}
				}
			}
		}

		// Now filter them with notification setting.
		for (int i = 0; i < inListUsers.size(); i++) {
			SimpleUser user = inListUsers.get(i);
			for (CrmNotificationSetting notificationSetting : notificationSettings) {
				if (user.getUsername() != null
						&& user.getUsername().equals(
								notificationSetting.getUsername())) {
					if (notificationSetting.getLevel().equals("None")) {
						inListUsers.remove(user);
						i--;
					} else if (notificationSetting.getLevel().equals("Minimal")
							&& type.equals(MonitorTypeConstants.ADD_COMMENT_ACTION)) {
						inListUsers.remove(user);
						i--;
					}
				}
				// else is Default sending ...
			}
		}
		return inListUsers;

	}

	private boolean checkExistInList(List<SimpleUser> lst, SimpleUser user) {
		for (SimpleUser simpleUser : lst) {
			if (simpleUser.getUsername() != null
					&& simpleUser.getUsername().equals(user.getUsername())
					|| simpleUser.getEmail().equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
