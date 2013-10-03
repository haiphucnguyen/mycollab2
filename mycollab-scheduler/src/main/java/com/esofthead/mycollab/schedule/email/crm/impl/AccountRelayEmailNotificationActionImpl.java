package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleAccount;
import com.esofthead.mycollab.module.crm.service.AccountService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.crm.AccountRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmLinkGenerator;

@Component
public class AccountRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		AccountRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private AccountService accountService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final AccountFieldNameMapper mapper;

	public AccountRelayEmailNotificationActionImpl() {
		mapper = new AccountFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		int recordAccountId = emailNotification.getTypeid();
		SimpleAccount simpleAccount = accountService.findById(recordAccountId,
				emailNotification.getSaccountid());
		if (simpleAccount != null) {
			String subject = StringUtils.subString(
					simpleAccount.getAccountname(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Account: \"" + subject + "\" has been created",
					"templates/email/crm/accountCreatedNotifier.mt");

			templateGenerator.putVariable("simpleAccount", simpleAccount);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleAccount));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(SimpleAccount simpleAccount) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put("accountURL", CrmLinkGenerator.generateCrmItemLink(
				CrmTypeConstants.ACCOUNT, simpleAccount.getId()));

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleAccount simpleAccount = accountService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(simpleAccount.getAccountname(),
				150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"Account: \"" + subject + "...\" has been updated",
				"templates/email/crm/accountUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleAccount", simpleAccount);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleAccount));

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		int accountRecordId = emailNotification.getTypeid();
		SimpleAccount simpleAccount = accountService.findById(accountRecordId,
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator("[Account]"
				+ emailNotification.getChangeByUserFullName()
				+ " has commented on "
				+ StringUtils.subString(simpleAccount.getAccountname(), 100)
				+ "\"", "templates/email/crm/accountAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		// templateGenerator.putVariable("userComment", linkGenerator
		// .generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("bug", simpleAccount);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleAccount));

		return templateGenerator;
	}

	@Override
	protected List<SimpleUser> getListNotififyUserWithFilter(
			SimpleRelayEmailNotification notification) {
		List<CrmNotificationSetting> notificationSettings = notificationService
				.findNotifications(notification.getChangeby(),
						notification.getSaccountid());

		List<SimpleUser> inListUsers = notification.getNotifyUsers();

		// TODO: MORE CODE FILTER
		return inListUsers;
	}

	public class AccountFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		AccountFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("summary", "Bug Summary");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("assignuser", "Assigned to");
			fieldNameMap.put("resolution", "Resolution");
			fieldNameMap.put("severity", "Serverity");
			fieldNameMap.put("environment", "Environment");
			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("duedate", "Due Date");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
