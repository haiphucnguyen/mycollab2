package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.UrlEncodeDecoder;
import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleContact;
import com.esofthead.mycollab.module.crm.service.ContactService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.module.project.ProjectLinkUtils;
import com.esofthead.mycollab.schedule.email.crm.ContactRelayEmailNotificationAction;
import com.esofthead.mycollab.schedule.email.crm.CrmLinkGenerator;

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
		super(CrmTypeConstants.CONTACT);
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
		CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
				simpleContact.getSaccountid());
		hyperLinks
				.put("contactURL",
						linkGenerator.getSiteUrl()
								+ CrmLinkGenerator.generateCrmItemLink(
										CrmTypeConstants.CONTACT,
										simpleContact.getId()));
		if (simpleContact.getAccountid() != null) {
			hyperLinks.put(
					"accountURL",
					linkGenerator.getSiteUrl()
							+ CrmLinkGenerator.generateCrmItemLink(
									CrmTypeConstants.ACCOUNT,
									simpleContact.getAccountid()));
		}

		if (simpleContact.getAssignuser() != null) {
			hyperLinks
					.put("assignUserURL", linkGenerator
							.generateUserPreviewFullLink(simpleContact
									.getAssignuser()));
		}
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
			CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
					simpleContact.getSaccountid());
			templateGenerator.putVariable("postedUserURL", linkGenerator
					.generateUserPreviewFullLink(auditLog.getPosteduser()));
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
		CrmLinkGenerator linkGenerator = new CrmLinkGenerator(
				simpleContact.getSaccountid());
		templateGenerator.putVariable("userComment", linkGenerator.getSiteUrl()
				+ ProjectLinkUtils.URL_PREFIX_PARAM + "account/user/preview/"
				+ UrlEncodeDecoder.encode(emailNotification.getChangeby()));
		templateGenerator.putVariable("simpleContact", simpleContact);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleContact));

		return templateGenerator;
	}

	public class ContactFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		ContactFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("firstname", "First Name");
			fieldNameMap.put("officephone", "Office Phone");
			fieldNameMap.put("lastname", "Last Name");
			fieldNameMap.put("mobile", "Mobile");
			fieldNameMap.put("accountName", "Account");
			fieldNameMap.put("homephone", "Home Phone");
			fieldNameMap.put("title", "Title");
			fieldNameMap.put("otherphone", "Other Phone");
			fieldNameMap.put("department", "Department");
			fieldNameMap.put("fax", "Fax");
			fieldNameMap.put("email", "Email");
			fieldNameMap.put("birthday", "Birthday");
			fieldNameMap.put("assistant", "Assistant");
			fieldNameMap.put("iscallable", "Callable");
			fieldNameMap.put("assistantphone", "Assistant Phone");
			fieldNameMap.put("assignuser", "Assignee");
			fieldNameMap.put("leadsource", "Leader Source");
			fieldNameMap.put("primaddress", "Address");
			fieldNameMap.put("primcity", "City");
			fieldNameMap.put("state", "State");
			fieldNameMap.put("othercity", "Other City");
			fieldNameMap.put("otherstate", "Other State");
			fieldNameMap.put("primpostalcode", "Postal Code");
			fieldNameMap.put("otherpostalcode", "Other Postal Code");
			fieldNameMap.put("primcountry", "Country");
			fieldNameMap.put("othercountry", "Other Country");
			fieldNameMap.put("description", "Description");

		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}
}
