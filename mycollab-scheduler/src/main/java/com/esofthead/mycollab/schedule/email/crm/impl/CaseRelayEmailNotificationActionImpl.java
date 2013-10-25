package com.esofthead.mycollab.schedule.email.crm.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esofthead.mycollab.common.domain.SimpleAuditLog;
import com.esofthead.mycollab.common.domain.SimpleRelayEmailNotification;
import com.esofthead.mycollab.common.service.AuditLogService;
import com.esofthead.mycollab.core.utils.StringUtils;
import com.esofthead.mycollab.module.crm.CrmLinkGenerator;
import com.esofthead.mycollab.module.crm.CrmTypeConstants;
import com.esofthead.mycollab.module.crm.domain.SimpleCase;
import com.esofthead.mycollab.module.crm.service.CaseService;
import com.esofthead.mycollab.module.crm.service.CrmNotificationSettingService;
import com.esofthead.mycollab.module.mail.TemplateGenerator;
import com.esofthead.mycollab.schedule.email.crm.CaseRelayEmailNotificationAction;

@Component
public class CaseRelayEmailNotificationActionImpl extends
		CrmDefaultSendingRelayEmailAction implements
		CaseRelayEmailNotificationAction {

	@Autowired
	private AuditLogService auditLogService;
	@Autowired
	private CaseService caseService;

	@Autowired
	private CrmNotificationSettingService notificationService;

	private final CaseFieldNameMapper mapper;

	public CaseRelayEmailNotificationActionImpl() {
		super(CrmTypeConstants.CASE);
		mapper = new CaseFieldNameMapper();
	}

	@Override
	protected TemplateGenerator templateGeneratorForCreateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());
		if (simpleCase != null) {
			String subject = StringUtils
					.subString(simpleCase.getSubject(), 150);

			TemplateGenerator templateGenerator = new TemplateGenerator(
					"Case: \"" + subject + "\" has been created",
					"templates/email/crm/caseCreatedNotifier.mt");

			templateGenerator.putVariable("simpleCase", simpleCase);
			templateGenerator.putVariable("hyperLinks",
					constructHyperLinks(simpleCase));
			return templateGenerator;
		} else {
			return null;
		}
	}

	private Map<String, String> constructHyperLinks(SimpleCase simpleCase) {
		Map<String, String> hyperLinks = new HashMap<String, String>();
		hyperLinks.put(
				"caseURL",
				getSiteUrl(simpleCase.getSaccountid())
						+ CrmLinkGenerator.generateCrmItemLink(
								CrmTypeConstants.CASE, simpleCase.getId()));
		if (simpleCase.getAssignuser() != null) {
			hyperLinks.put("assignUserURL", CrmLinkGenerator
					.generatePreviewFullUserLink(
							getSiteUrl(simpleCase.getSaccountid()),
							simpleCase.getAssignuser()));
		}

		return hyperLinks;
	}

	@Override
	protected TemplateGenerator templateGeneratorForUpdateAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		String subject = StringUtils.subString(simpleCase.getSubject(), 150);

		TemplateGenerator templateGenerator = new TemplateGenerator("Case: \""
				+ subject + "...\" has been updated",
				"templates/email/crm/caseUpdatedNotifier.mt");
		templateGenerator.putVariable("simpleCase", simpleCase);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCase));

		if (emailNotification.getTypeid() != null) {
			SimpleAuditLog auditLog = auditLogService.findLatestLog(
					emailNotification.getTypeid(),
					emailNotification.getSaccountid());

			templateGenerator.putVariable("postedUserURL", CrmLinkGenerator
					.generatePreviewFullUserLink(
							getSiteUrl(simpleCase.getAccountid()),
							auditLog.getPosteduser()));
			templateGenerator.putVariable("historyLog", auditLog);

			templateGenerator.putVariable("mapper", mapper);
		}
		return templateGenerator;
	}

	@Override
	protected TemplateGenerator templateGeneratorForCommentAction(
			SimpleRelayEmailNotification emailNotification) {
		SimpleCase simpleCase = caseService.findById(
				emailNotification.getTypeid(),
				emailNotification.getSaccountid());

		TemplateGenerator templateGenerator = new TemplateGenerator("[Case]"
				+ emailNotification.getChangeByUserFullName()
				+ " has commented on "
				+ StringUtils.subString(simpleCase.getSubject(), 100) + "\"",
				"templates/email/crm/caseAddNoteNotifier.mt");
		templateGenerator.putVariable("comment", emailNotification);
		templateGenerator.putVariable("userComment", CrmLinkGenerator
				.generatePreviewFullUserLink(
						getSiteUrl(simpleCase.getSaccountid()),
						emailNotification.getChangeby()));
		templateGenerator.putVariable("simpleCase", simpleCase);
		templateGenerator.putVariable("hyperLinks",
				constructHyperLinks(simpleCase));

		return templateGenerator;
	}

	public class CaseFieldNameMapper {
		private final Map<String, String> fieldNameMap;

		CaseFieldNameMapper() {
			fieldNameMap = new HashMap<String, String>();

			fieldNameMap.put("priority", "Priority");
			fieldNameMap.put("type", "Type");
			fieldNameMap.put("status", "Status");
			fieldNameMap.put("reason", "Reason");
			fieldNameMap.put("accountName", "Account Name");
			fieldNameMap.put("subject", "Subject");
			fieldNameMap.put("phonenumber", "Phone Number");
			fieldNameMap.put("email", "Email");
			fieldNameMap.put("origin", "Origin");
			fieldNameMap.put("assignuser", "Assignee");
			fieldNameMap.put("description", "Description");
			fieldNameMap.put("resolution", "Resolution");
		}

		public boolean hasField(String fieldName) {
			return fieldNameMap.containsKey(fieldName);
		}

		public String getFieldLabel(String fieldName) {
			return fieldNameMap.get(fieldName);
		}
	}

}
