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
import com.esofthead.mycollab.module.crm.domain.CrmNotificationSetting;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.user.domain.SimpleUser;
import com.esofthead.mycollab.schedule.email.crm.ContactRelayEmailNotificationAction;

@Component
public class ContactRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		ContactRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private ContactService contactService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final ContactFieldNameMapper mapper;

	public ContactRelayEmailNotificationActionImpl() {
		mapper = new ContactFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleContact simpleContact = contactService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleContact != null) {
			String subject = StringUtils.subString(
					simpleContact.getContactName(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Contact: \"" + subject + "\" has been created",
					"templates/email/crm/contactCreatedNotifier.mt");

			templateGenerator.putVariable("simpleContact", simpleContact);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleContact));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(SimpleContact simpleContact) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		// hyperLinks.put("accountURL", CrmLinkGenerator.generateCrmItemLink(
		// CrmTypeConstants.ACCOUNT, simpleContact.getId()));

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleContact simpleContact = contactService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(simpleContact.getContactName(),
				150);

		TemplateGenerator templateGenerator = new TemplateGenerator(
				"Contact: \"" + subject + "...\" has been updated",
				"templates/email/crm/contactUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleContact", simpleContact);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleContact));

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
		SimpleContact simpleContact = contactService.findById(accountRecordId,
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator("[Contact]"
				+ emailNotification.getChangeByUserFullName()
				+ " has commented on "
				+ StringUtils.subString(simpleContact.getContactName(), 100)
				+ "\"", "templates/email/crm/contactAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		// templateGenerator.putVariable("userComment", linkGenerator
		// .generateUserPreviewFullLink(emailNotification.getChangeby()));
		templateGenerator.putVariable("bug", simpleContact);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleContact));

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

	public class ContactFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ContactFieldNameMapper() {
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
